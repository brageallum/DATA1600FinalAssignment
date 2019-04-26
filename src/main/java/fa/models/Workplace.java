package fa.models;

import fa.utils.serialization.SerializableProperty;
import javafx.beans.property.ObjectProperty;

import java.io.Serializable;
import java.time.LocalDate;

public class Workplace implements Serializable {
  private static int nextId = 100;

  public final int ID;
  private final SerializableProperty<Enum> sector;
  private final SerializableProperty<String> workplace;
  private final SerializableProperty<String> employer;
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

  public Workplace() {
    this(null, null, null, null, null, null, null, null, 0, null, null, null, null);
  }

  public Workplace(
    Enum sector,
    String workplace,
    String employer,
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

  public Workplace(
    int ID,
    Enum sector,
    String workplace,
    String employer,
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
    if (ID > nextId) nextId = ID + 1;

    this.ID = ID;
    this.sector = new SerializableProperty<>(sector);
    this.workplace= new SerializableProperty<>(workplace);
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

  public ObjectProperty<Enum> sectorProperty() {
    return sector.getProperty();
  }

  public ObjectProperty<String> workplaceProperty() {
    return workplace.getProperty();
  }

  public ObjectProperty<String> employerProperty() {
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
    return String.format("[%s] %s %s", ID, workplaceProperty().getValue(), employerProperty().getValue());
  }
}
