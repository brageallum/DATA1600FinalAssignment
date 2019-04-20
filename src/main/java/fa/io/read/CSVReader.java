package fa.io.read;

import fa.io.read.exceptions.ReadCSVException;
import fa.io.read.exceptions.ReadCSVInvalidFormatException;
import fa.io.read.exceptions.ReadCSVInvalidTypeException;
import fa.models.DB;
import fa.models.JobSeeker;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class CSVReader implements ReadStrategy {
  private final String[] jobSeekerFields = {
    "type", "id", "firstName", "lastName", "emailAddress", "phoneNumber",
    "birthDate", "education", "workExperience", "wage", "references"
  };

  private DB detachedDB;

  @Override
  public DB readFile(File file) throws IOException, ReadCSVException {
    /*
      Since JavaFX does not allow for changing the UI from another Thread then the main Thread,
      and making changes to the DB singleton instance automatically updates the UI, we store the newly read data
      in a DB instance which is detached from the global singleton instance until we return it to the main Thread.
     */
    detachedDB = DB.getDetachedInstance();
    BufferedReader reader = Files.newBufferedReader(Paths.get(file.getPath()));

    String line;
    int lineNr = 1;
    while((line = reader.readLine()) != null) {
      if (isEmpty(line)) continue;

      parseLine(line, lineNr);
      lineNr++;
    }

    return detachedDB;
  }

  private boolean isEmpty(String line) {
    return line.matches("^\\s$");
  }

  private void parseLine(String line, int lineNr) throws ReadCSVException {
    String type = getType(line);
    switch (type) {
      case "JobSeeker":
        detachedDB.getJobSeekers().add(parseJobSeeker(line, lineNr));
        break;
      default:
        throw new ReadCSVInvalidTypeException(String.format("\"%s\" is not a valid data type (on line %s)", type, lineNr));
    }
  }

  private String getType(String line) {
    Matcher typeMatcher = Pattern.compile("^(?<type>[^;]+?);").matcher(line);
    if (typeMatcher.find()) {
      return typeMatcher.group("type");
    } else {
      return "";
    }
  }

  private JobSeeker parseJobSeeker(String line, int lineNr) throws ReadCSVInvalidFormatException {
    Matcher data = getCSVRowPattern(jobSeekerFields).matcher(line);
    if (!data.find()) throw new ReadCSVInvalidFormatException(String.format("Incorrect format for type JobSeeker (on line %s)", lineNr));

    return new JobSeeker(
      Integer.parseInt(data.group("id")),
      data.group("firstName"),
      data.group("lastName"),
      data.group("emailAddress"),
      data.group("phoneNumber"),
      null,
      data.group("education"),
      data.group("workExperience"),
      Integer.parseInt(data.group("wage")),
      data.group("references")
    );
  }

  private Pattern getCSVRowPattern(String[] fields) {
    String pattern = Arrays.stream(fields).reduce("^",
      (accumulator, current) -> accumulator + String.format("(?<%s>[^;]+);", current)
    );
    pattern += "?\\s*$";
    return Pattern.compile(pattern);
  }
}
