package fa.io.write;

import fa.models.DB;
import fa.models.JobSeeker;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
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
      jobSeeker.getFirstName().getValue(),
      jobSeeker.getLastName().getValue(),
      jobSeeker.getEmailAddress().getValue(),
      jobSeeker.getPhoneNumber().getValue(),
      new SimpleDateFormat("dd/MM/yyyy").format(jobSeeker.getBirthDate().getValue()),
      jobSeeker.getEducation().getValue(),
      jobSeeker.getWorkExperience().getValue(),
      Integer.toString(jobSeeker.getWage().getValue()),
      jobSeeker.getReferences().getValue(),
    }));
  }

  private String toCSVFormat(String[] data) {
    return Arrays.stream(data).reduce("", (acc, curr) -> acc + curr + ";");
  }
}
