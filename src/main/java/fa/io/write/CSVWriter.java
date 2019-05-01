package fa.io.write;

import fa.DB;
import fa.models.Substitute;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

class CSVWriter implements WriteStrategy {
  @Override
  public void writeToFile(File file) throws IOException {
    PrintWriter writer = new PrintWriter(file);

    DB.getInstance().getSubstitute().forEach(jobSeeker -> writeJobSeeker(writer, jobSeeker));

    writer.close();
  }

  private void writeJobSeeker(PrintWriter writer, Substitute substitute) {
    writer.println(toCSVFormat(new String[]{
      "Substitute",
      Integer.toString(substitute.ID),
      substitute.firstNameProperty().getValue(),
      substitute.lastNameProperty().getValue(),
      substitute.emailAddressProperty().getValue(),
      substitute.phoneNumberProperty().getValue(),
      substitute.birthDateProperty().getValue().format(DateTimeFormatter.ISO_LOCAL_DATE),
      substitute.educationProperty().getValue(),
      substitute.workExperienceProperty().getValue(),
      Integer.toString(substitute.wageProperty().getValue()),
      substitute.referencesProperty().getValue(),
    }));
  }

  private String toCSVFormat(String[] data) {
    return Arrays.stream(data).reduce("", (acc, curr) -> acc + curr + ";");
  }
}
