package fa.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Date;

public class JobSeeker extends Person {
  private static int nextId = 100;

  private final StringProperty education;
  private final StringProperty workExperience;
  private final IntegerProperty wage;
  private final StringProperty references;

  private final int ID;

  public JobSeeker(
    String firstName,
    String lastName,
    String emailAddress,
    String phoneNumber,
    Date birthDate,
    String education,
    String workExperience,
    int wage,
    String references
  ) {
    this(
      nextId,
      firstName,
      lastName,
      emailAddress,
      phoneNumber,
      birthDate,
      education,
      workExperience,
      wage,
      references
    );
    nextId += 1;
  }

  public JobSeeker(
    int ID,
    String firstName,
    String lastName,
    String emailAddress,
    String phoneNumber,
    Date birthDate,
    String education,
    String workExperience,
    int wage,
    String references
  ) {

    super(firstName, lastName, emailAddress, phoneNumber, birthDate);

    this.ID = ID;
    this.education = new SimpleStringProperty(education);
    this.workExperience = new SimpleStringProperty(workExperience);
    this.wage = new SimpleIntegerProperty(wage);
    this.references = new SimpleStringProperty(references);
  }

  public StringProperty getEducation() {
    return education;
  }

  public StringProperty getWorkExperience() {
    return workExperience;
  }

  public IntegerProperty getWage() {
    return wage;
  }

  public StringProperty getReferences() {
    return references;
  }

  @Override
  public String toString() {
    return String.format("[%s] %s %s", ID, getFirstName().getValue(), getLastName().getValue());
  }
}
