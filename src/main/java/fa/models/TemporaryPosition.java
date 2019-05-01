package fa.models;

import fa.DB;
import fa.utils.SearchMatcher;
import fa.utils.serialization.SerializableProperty;
import javafx.beans.property.ObjectProperty;

import java.io.Serializable;

public class TemporaryPosition extends Model implements Serializable {
  private static int nextId = 100;

  private final int ID;
  private final SerializableProperty<DB.sectorOptions> sector;
  private final SerializableProperty<String> workplace;
  private final SerializableProperty<Employer> employer;
  private final SerializableProperty<String> category;
  private final SerializableProperty<String> duration;
  private final SerializableProperty<String> workingHours;
  private final SerializableProperty<String> description;
  private final SerializableProperty<String> position;
  private final SerializableProperty<String> qualifications;
  private final SerializableProperty<Integer> wage;
  private final SerializableProperty<String> conditions;
  private final SerializableProperty<String> phoneNumber;
  private final SerializableProperty<String> emailAddress;

  public TemporaryPosition() {
    this(null, null, null, null, null, null, null, null, 0, null, null, null, null);
  }

  public TemporaryPosition(
    DB.sectorOptions sector,
    String workplace,
    Employer employer,
    String category,
    String duration,
    String workingHours,
    String position,
    String qualifications,
    int wage,
    String conditions,
    String phoneNumber,
    String emailAddress,
    String description
  ) {
    this(
      nextId,
      sector,
      workplace,
      employer,
      category,
      duration,
      workingHours,
      position,
      qualifications,
      wage,
      conditions,
      phoneNumber,
      emailAddress,
      description
    );
  }

  public TemporaryPosition(
    int ID,
    DB.sectorOptions sector,
    String workplace,
    Employer employer,
    String category,
    String duration,
    String workingHours,
    String position,
    String qualifications,
    int wage,
    String conditions,
    String phoneNumber,
    String emailAddress,
    String description
  ) {
    if (ID >= nextId) nextId = ID + 1;

    this.ID = ID;
    this.sector = new SerializableProperty<>(sector);
    this.workplace = new SerializableProperty<>(workplace);
    this.employer = new SerializableProperty<>(employer);
    this.category = new SerializableProperty<>(category);
    this.duration = new SerializableProperty<>(duration);
    this.workingHours = new SerializableProperty<>(workingHours);
    this.position = new SerializableProperty<>(position);
    this.qualifications = new SerializableProperty<>(qualifications);
    this.wage = new SerializableProperty<>(wage);
    this.conditions = new SerializableProperty<>(conditions);
    this.phoneNumber = new SerializableProperty<>(phoneNumber);
    this.emailAddress = new SerializableProperty<>(emailAddress);
    this.description = new SerializableProperty<>(description);
  }

  public int getID() {
    return ID;
  }

  public ObjectProperty<DB.sectorOptions> sectorProperty() {
    return sector.getProperty();
  }

  public ObjectProperty<String> workplaceProperty() {
    return workplace.getProperty();
  }

  public ObjectProperty<Employer> employerProperty() {
    return employer.getProperty();
  }

  public ObjectProperty<String> categoryProperty() {
    return category.getProperty();
  }

  public ObjectProperty<String> durationProperty() {
    return duration.getProperty();
  }

  public ObjectProperty<String> workingHoursProperty() {
    return workingHours.getProperty();
  }

  public ObjectProperty<String> positionProperty() {
    return position.getProperty();
  }

  public ObjectProperty<String> qualificationsProperty() {
    return qualifications.getProperty();
  }

  public ObjectProperty<Integer> wageProperty() {
    return wage.getProperty();
  }

  public ObjectProperty<String> conditionsProperty() {
    return conditions.getProperty();
  }

  public ObjectProperty<String> phoneNumberProperty() {
    return phoneNumber.getProperty();
  }

  public ObjectProperty<String> emailAddressProperty() {
    return emailAddress.getProperty();
  }

  public ObjectProperty<String> descriptionProperty() {
    return description.getProperty();
  }

  @Override
  public String toString() {
    return String.format("%s @%s", this.categoryProperty().getValue(), this.workplaceProperty().getValue());
  }

  @Override
  public boolean matchesSearch(String pattern) {
    return SearchMatcher.matches(
      pattern,
      Integer.toString(this.ID),
      this.sectorProperty().getValue().toString(),
      this.workplaceProperty().getValue(),
      this.employerProperty().getValue().firstNameProperty().getValue(),
      this.categoryProperty().getValue(),
      this.durationProperty().getValue(),
      this.workingHoursProperty().getValue(),
      this.positionProperty().getValue(),
      this.qualificationsProperty().getValue(),
      this.wageProperty().getValue().toString(),
      this.conditionsProperty().getValue(),
      this.phoneNumberProperty().getValue(),
      this.emailAddressProperty().getValue(),
      this.descriptionProperty().getValue()
    );
  }
}
