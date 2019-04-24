package fa.io.write;

import fa.models.DB;
import fa.models.JobSeeker;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

class CSVWriter implements WriteStrategy {
  @Override
  public void writeToFile(File file) throws IOException {
    PrintWriter writer = new PrintWriter(file);

    DB.getInstance().getJobSeekers().forEach(jobSeeker -> writeJobSeeker(writer, jobSeeker));

    writer.close();
  }

  private void writeJobSeeker(PrintWriter writer, JobSeeker jobSeeker) {
    writer.println(toCSVFormat(new String[]{
      "JobSeeker",
      Integer.toString(jobSeeker.ID),
      jobSeeker.firstNameProperty().getValue(),
      jobSeeker.lastNameProperty().getValue(),
      jobSeeker.emailAddressProperty().getValue(),
      jobSeeker.phoneNumberProperty().getValue(),
      jobSeeker.birthDateProperty().getValue().format(DateTimeFormatter.ISO_LOCAL_DATE),
      jobSeeker.educationProperty().getValue(),
      jobSeeker.workExperienceProperty().getValue(),
      Integer.toString(jobSeeker.wageProperty().getValue()),
      jobSeeker.referencesProperty().getValue(),
    }));
  }

  private String toCSVFormat(String[] data) {
    return Arrays.stream(data).reduce("", (acc, curr) -> acc + curr + ";");
  }
}
