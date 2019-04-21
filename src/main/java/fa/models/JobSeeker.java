package fa.models;

import fa.utils.SerializableProperty;
import javafx.beans.property.ObjectProperty;

import java.io.Serializable;
import java.util.Date;

public class JobSeeker extends Person implements Serializable {
  private static int nextId = 100;

  private final SerializableProperty<String> education;
  private final SerializableProperty<String> workExperience;
  private final SerializableProperty<Integer> wage;
  private final SerializableProperty<String> references;

  public final int ID;

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

    if (ID > nextId) nextId = ID + 1;

    this.ID = ID;
    this.education = new SerializableProperty<>(education);
    this.workExperience = new SerializableProperty<>(workExperience);
    this.wage = new SerializableProperty<>(wage);
    this.references = new SerializableProperty<>(references);
  }

  public ObjectProperty<String> getEducation() {
    return education.getProperty();
  }

  public ObjectProperty<String> getWorkExperience() {
    return workExperience.getProperty();
  }

  public ObjectProperty<Integer> getWage() {
    return wage.getProperty();
  }

  public ObjectProperty<String> getReferences() {
    return references.getProperty();
  }

  @Override
  public String toString() {
    return String.format("[%s] %s %s", ID, getFirstName().getValue(), getLastName().getValue());
  }
}
