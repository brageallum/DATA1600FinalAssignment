package fa.io;

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

  @Override
  public void readFile(File file) throws IOException {
    BufferedReader reader = Files.newBufferedReader(Paths.get(file.toString()));

    String line;
    while((line = reader.readLine()) != null) {
      if (isEmpty(line)) continue;

      parseLine(line);
    }
  }

  private boolean isEmpty(String line) {
    return line.matches("^\\s$");
  }

  private void parseLine(String line) {
    switch (getType(line)) {
      case "JobSeeker":
        DB.init().getJobSeekers().add(parseJobSeeker(line));
        break;
      default:
        // TODO: Throw "CSVReaderInvalidTypeException" (or something like that)
        break;
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

  private JobSeeker parseJobSeeker(String line) {
    Matcher data = getCSVRowPattern(jobSeekerFields).matcher(line);
    if (!data.find()) return null;

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
