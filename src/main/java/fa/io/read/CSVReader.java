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
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    Line line = new Line();
    while (line.nextLine(reader.readLine())) {
      if (line.isEmpty()) continue;

      parseLine(line);
    }

    return detachedDB;
  }

  private void parseLine(Line line) throws ReadCSVException {
    String type = getType(line);
    switch (type) {
      case "JobSeeker":
        detachedDB.getJobSeekers().add(parseJobSeeker(line));
        break;
      default:
        throw new ReadCSVInvalidTypeException(
          String.format("[on line %s]: \"%s\" is not a valid data type.", line.getLineNumber(), type)
        );
    }
  }

  private String getType(Line line) {
    Matcher typeMatcher = Pattern.compile("^(?<type>[^;]+?);").matcher(line.getText());
    if (typeMatcher.find()) {
      return typeMatcher.group("type");
    } else {
      return "";
    }
  }

  private JobSeeker parseJobSeeker(Line line) throws ReadCSVInvalidFormatException {
    Matcher data = getCSVRowPattern(jobSeekerFields).matcher(line.getText());
    if (!data.find()) throw new ReadCSVInvalidFormatException(
      String.format("[on line %s]: Incorrect format for type JobSeeker.", line.getLineNumber())
    );

    try {
      return new JobSeeker(
        Integer.parseInt(data.group("id")),
        data.group("firstName"),
        data.group("lastName"),
        data.group("emailAddress"),
        data.group("phoneNumber"),
        new SimpleDateFormat("dd/MM/yyyy").parse(data.group("birthDate")),
        data.group("education"),
        data.group("workExperience"),
        Integer.parseInt(data.group("wage")),
        data.group("references")
      );
    } catch (ParseException e) {
      e.printStackTrace();
      throw new ReadCSVInvalidFormatException(
        String.format("[on line %s]: Invalid date format (valid format is dd/MM/yyyy).", line.getLineNumber())
      );
    }
  }

  private Pattern getCSVRowPattern(String[] fields) {
    String pattern = Arrays.stream(fields).reduce("^",
      (accumulator, current) -> accumulator + String.format("(?<%s>[^;]+);", current)
    );
    pattern += "?\\s*$";
    return Pattern.compile(pattern);
  }
}

class Line {
  private String text;
  private int lineNumber = 0;

  boolean nextLine(String nextLine) {
    if (nextLine == null) return false;

    text = nextLine;
    lineNumber++;
    return true;
  }

  String getText() {
    return text;
  }

  int getLineNumber() {
    return lineNumber;
  }

  boolean isEmpty() {
    return text.matches("^\\s$");
  }
}
