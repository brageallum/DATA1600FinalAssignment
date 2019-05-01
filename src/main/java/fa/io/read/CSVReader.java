package fa.io.read;

import fa.DB;
import fa.io.read.exceptions.CSVReaderInvalidFormatException;
import fa.io.read.exceptions.CSVReaderInvalidTypeException;
import fa.io.read.exceptions.ReaderException;
import fa.models.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class CSVReader implements ReadStrategy {
  private final Pattern substituteFields = getCSVRowPattern(
    "type", "id", "firstName", "lastName", "emailAddress", "phoneNumber",
    "birthDate", "education", "workExperience", "wage", "references", "address"
  );

  private final Pattern temporaryPositionFields = getCSVRowPattern(
    "type", "id", "sector", "temporaryPosition", "employer", "category", "duration", "workingHours",
    "position", "qualifications", "wage", "conditions", "phoneNumber", "emailAddress",
    "description"
  );

  private final Pattern employerFields = getCSVRowPattern(
  "type", "id", "firstName", "lastName", "sector", "address", "industry", "phoneNumber",
  "emailAddress", "birthDate"
  );

  private final Pattern employmentFields = getCSVRowPattern(
    "type", "id", "employer", "temporaryPosition"
    );

  private DB detachedDB;
  private BufferedReader reader;

  @Override
  public DB readFile(File file) throws IOException, ReaderException {
    /*
      Since JavaFX does not allow for changing the UI from another Thread then the main Thread,
      and making changes to the DB singleton instance automatically updates the UI, we store the newly read data
      in a DB instance which is detached from the global singleton instance until we return it to the main Thread.
     */
    detachedDB = DB.getDetachedInstance();
    reader = Files.newBufferedReader(Paths.get(file.getPath()));

    try {
      return parseLines();
    } finally {
      reader.close();
    }
  }

  private DB parseLines() throws IOException, ReaderException {
    Line line = new Line();
    while (line.nextLine(reader.readLine())) {
      if (line.isEmpty()) continue;

      parseLine(line);
    }

    return detachedDB;
  }

  private void parseLine(Line line) throws ReaderException {
    String type = getType(line);
    switch (type) {
      case "Substitute":
        detachedDB.getSubstitutes().add(parseJobSeeker(line));
        break;
      case "TemporaryPosition":
        detachedDB.getTemporaryPositions().add(parseTemporaryPosition(line));
        break;
      case "Employer":
        detachedDB.getEmployers().add(parseEmployer(line));
        break;
      case "Employment":
        detachedDB.getEmployments().add(parseEmployment(line));
        break;
      default:
        throw new CSVReaderInvalidTypeException(
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

  private Substitute parseJobSeeker(Line line) throws CSVReaderInvalidFormatException {
    Matcher data = substituteFields.matcher(line.getText());
    if (!data.find()) throw new CSVReaderInvalidFormatException(
      String.format("[on line %s]: Incorrect format for type Substitute.", line.getLineNumber())
    );

    try {
      return new Substitute(
        Integer.parseInt(data.group("id")),
        data.group("firstName"),
        data.group("lastName"),
        data.group("emailAddress"),
        data.group("phoneNumber"),
        LocalDate.parse(data.group("birthDate")),
        data.group("education"),
        data.group("workExperience"),
        Integer.parseInt(data.group("wage")),
        data.group("references"),
        data.group("address")
      );
    } catch (DateTimeParseException e) {
      e.printStackTrace();
      throw new CSVReaderInvalidFormatException(
        String.format("[on line %s]: Invalid date format (valid format is yyyy-MM-dd).", line.getLineNumber())
      );
    }
  }

  private TemporaryPosition parseTemporaryPosition(Line line) throws CSVReaderInvalidFormatException {
    Matcher data = temporaryPositionFields.matcher(line.getText());
    if (!data.find()) throw new CSVReaderInvalidFormatException(
      String.format("[on line %s]: Incorrect format for type TemporaryPosition.", line.getLineNumber())
    );

    return new TemporaryPosition(
      Integer.parseInt(data.group("id")),
      DB.sectorOptions.valueOf(data.group("sector")),
      data.group("temporaryPosition"),
      this.detachedDB.getEmployer(Integer.parseInt(data.group("employer"))),
      data.group("category"),
      data.group("duration"),
      data.group("workingHours"),
      data.group("position"),
      data.group("qualifications"),
      Integer.parseInt(data.group("wage")),
      data.group("conditions"),
      data.group("phoneNumber"),
      data.group("emailAddress"),
      data.group("description")
    );
  }

  private Employer parseEmployer(Line line) throws CSVReaderInvalidFormatException {
    Matcher data = employerFields.matcher(line.getText());
    if (!data.find()) throw new CSVReaderInvalidFormatException(
      String.format("[on line %s]: Incorrect format for type Employer.", line.getLineNumber())
    );

    return new Employer(
      Integer.parseInt(data.group("id")),
      data.group("firstName"),
      data.group("lastName"),
      DB.sectorOptions.valueOf(data.group("sector")),
      data.group("address"),
      data.group("industry"),
      data.group("phoneNumber"),
      data.group("emailAddress"),
      LocalDate.parse(data.group("birthDate"))
    );
  }

  private Employment parseEmployment(Line line) throws CSVReaderInvalidFormatException {
    Matcher data = this.employmentFields.matcher(line.getText());
    if (!data.find()) throw new CSVReaderInvalidFormatException(
      String.format("[on line %s]: Incorrect format for type Employment.", line.getLineNumber())
    );

    return new Employment(
      Integer.parseInt(data.group("id")),
      this.detachedDB.getSubstitute(Integer.parseInt(data.group("id"))),
      this.detachedDB.getTemporaryPosition(Integer.parseInt(data.group("id")))
    );
  }

  private Pattern getCSVRowPattern(String... fields) {
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
    return text.matches("^\\s*$");
  }
}
