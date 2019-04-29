package fa.models;

import fa.utils.SearchMatcher;
import fa.utils.Searchable;
import fa.utils.serialization.SerializableProperty;
import javafx.beans.property.ObjectProperty;
import java.io.Serializable;
import java.time.LocalDate;

public class JobSeeker extends Person implements Serializable, Searchable {
  private static int nextId = 100;

  private final SerializableProperty<String> education;
  private final SerializableProperty<String> workExperience;
  private final SerializableProperty<Integer> wage;
  private final SerializableProperty<String> references;

  public final int ID;

  public JobSeeker() {
    this(null, null, null, null, null, null, null, 0, null, null);
  }

  public JobSeeker(
    String firstName,
    String lastName,
    String emailAddress,
    String phoneNumber,
    LocalDate birthDate,
    String education,
    String workExperience,
    int wage,
    String references,
    String address
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
      references,
      address
    );
  }

  public JobSeeker(
    int ID,
    String firstName,
    String lastName,
    String emailAddress,
    String phoneNumber,
    LocalDate birthDate,
    String education,
    String workExperience,
    int wage,
    String references,
    String address
  ) {

    super(firstName, lastName, emailAddress, phoneNumber, birthDate, address);

    if (ID > nextId) nextId = ID + 1;

    this.ID = ID;
    this.education = new SerializableProperty<>(education);
    this.workExperience = new SerializableProperty<>(workExperience);
    this.wage = new SerializableProperty<>(wage);
    this.references = new SerializableProperty<>(references);
  }

  public int getID() {
    return this.ID;
  }

  public ObjectProperty<String> educationProperty() {
    return this.education.getProperty();
  }

  public ObjectProperty<String> workExperienceProperty() {
    return this.workExperience.getProperty();
  }

  public ObjectProperty<Integer> wageProperty() {
    return this.wage.getProperty();
  }

  public ObjectProperty<String> referencesProperty() {
    return this.references.getProperty();
  }

  @Override
  public String toString() {
    return String.format("%s %s", this.firstNameProperty().getValue(), this.lastNameProperty().getValue());
  }

  @Override
  public boolean matchesSearch(String regex) {
    return super.matchesSearch(regex) || SearchMatcher.matches(
        regex,
        Integer.toString(this.ID),
        this.educationProperty().getValue(),
        this.workExperienceProperty().getValue(),
        this.wageProperty().getValue().toString(),
        this.referencesProperty().getValue()
      );
  }
}
