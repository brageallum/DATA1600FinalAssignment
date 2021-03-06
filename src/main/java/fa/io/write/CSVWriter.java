package fa.io.write;

import fa.DB;
import fa.models.*;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

class CSVWriter implements WriteStrategy {
  private PrintWriter writer;

  @Override
  public void writeToFile(File file) throws IOException {
    this.writer = new PrintWriter(file);

    DB.getInstance().getSubstitutes().forEach(this::writeSubstitute);
    this.writer.write("\n");
    DB.getInstance().getEmployers().forEach(this::writeEmployer);
    this.writer.write("\n");
    DB.getInstance().getTemporaryPositions().forEach(this::writeTemporaryPosition);
    this.writer.write("\n");
    DB.getInstance().getJobApplications().forEach(this::writeJobAplication);
    this.writer.write("\n");
    DB.getInstance().getEmployments().forEach(this::writeEmployment);

    this.writer.close();
  }

  private void writeSubstitute(Substitute substitute) {
    this.writer.println(toCSVFormat(new String[]{
      "Substitute",
      Integer.toString(substitute.getID()),
      substitute.firstNameProperty().getValue(),
      substitute.lastNameProperty().getValue(),
      substitute.emailAddressProperty().getValue(),
      substitute.phoneNumberProperty().getValue(),
      substitute.birthDateProperty().getValue().format(DateTimeFormatter.ISO_LOCAL_DATE),
      substitute.educationProperty().getValue(),
      substitute.workExperienceProperty().getValue(),
      Integer.toString(substitute.wageProperty().getValue()),
      substitute.referencesProperty().getValue(),
      substitute.addressProperty().getValue(),
    }));
  }

  private void writeEmployer(Employer employer) {
    this.writer.println(toCSVFormat(new String[]{
      "Employer",
      Integer.toString(employer.getID()),
      employer.firstNameProperty().getValue(),
      employer.lastNameProperty().getValue(),
      employer.sectorProperty().getValue().toString(),
      employer.addressProperty().getValue(),
      employer.industryProperty().getValue(),
      employer.phoneNumberProperty().getValue(),
      employer.emailAddressProperty().getValue(),
      employer.birthDateProperty().getValue().format(DateTimeFormatter.ISO_LOCAL_DATE),
    }));
  }

  private void writeTemporaryPosition(TemporaryPosition temporaryPosition) {
    this.writer.println(toCSVFormat(new String[]{
      "TemporaryPosition",
      Integer.toString(temporaryPosition.getID()),
      temporaryPosition.sectorProperty().getValue().toString(),
      temporaryPosition.workplaceProperty().getValue(),
      Integer.toString(temporaryPosition.employerProperty().getValue().getID()),
      temporaryPosition.categoryProperty().getValue(),
      temporaryPosition.durationProperty().getValue(),
      temporaryPosition.workingHoursProperty().getValue(),
      temporaryPosition.positionProperty().getValue(),
      temporaryPosition.qualificationsProperty().getValue(),
      Integer.toString(temporaryPosition.wageProperty().getValue()),
      temporaryPosition.conditionsProperty().getValue(),
      temporaryPosition.phoneNumberProperty().getValue(),
      temporaryPosition.emailAddressProperty().getValue(),
      temporaryPosition.descriptionProperty().getValue()
    }));
  }

  private void writeEmployment(Employment employment) {
    this.writer.println(toCSVFormat(new String[]{
      "Employment",
      Integer.toString(employment.getID()),
      Integer.toString(employment.substituteProperty().getValue().getID()),
      Integer.toString(employment.temporaryPositionProperty().getValue().getID())
    }));
  }

  private void writeJobAplication(JobApplication jobApplication) {
    this.writer.println(toCSVFormat(new String[]{
      "JobApplication",
      Integer.toString(jobApplication.getID()),
      Integer.toString(jobApplication.substituteProperty().getValue().getID()),
      Integer.toString(jobApplication.temporaryPositionProperty().getValue().getID())
    }));
  }

  private String toCSVFormat(String[] data) {
    return Arrays.stream(data).reduce("", (acc, curr) -> acc + curr + ";");
  }
}
