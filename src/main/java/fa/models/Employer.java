package fa.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import fa.utils.serialization.SerializableProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Employer extends Person implements Serializable {

  private final int ID;
  private static int nextId = 100;
  private final SerializableProperty<Enum> sector;
  private final SerializableProperty<String> industry;
  private final SerializableProperty<List<Workplace>> workplaces;
  private transient StringProperty workplacesNames;

  public Employer() {
    this(null, null, null, null, null, null, null, null, null);
  }

  public Employer(
    String firstName,
    String lastName,
    Enum sector,
    String address,
    String industry,
    String phoneNumber,
    String emailAddress,
    LocalDate birthDate,
    List<Workplace> workplaces
  ) {
    this(
      nextId,
      firstName,
      lastName,
      sector,
      address,
      industry,
      phoneNumber,
      emailAddress,
      birthDate,
      workplaces
    );
  }

  public Employer(
    int ID,
    String firstName,
    String lastName,
    Enum sector,
    String address,
    String industry,
    String phoneNumber,
    String emailAddress,
    LocalDate birthDate,
    List<Workplace> workplaces
  ) {
    super(firstName,lastName,emailAddress,phoneNumber,birthDate,address);

    System.out.println("EMPLOYER INITIALIZED");

    if (ID > nextId) nextId = ID + 1;

    this.ID = ID;
    this.sector = new SerializableProperty<>(sector);
    this.industry = new SerializableProperty<>(industry);
    this.workplaces = new SerializableProperty<>(workplaces);
    this.setWorkplacesNames();

    this.workplaces.getProperty().addListener((observableValue, oldValue, newValue) -> {
      this.setWorkplacesNames();
    });
  }

  private void setWorkplacesNames() {
    this.workplacesNames = new SimpleStringProperty(this.workplaces.getProperty().getValue()
      .stream()
      .map(s -> s.workplaceProperty().getValue())
      .collect(Collectors.joining(", ")));
  }

  public int getID() {
    return this.ID;
  }

  public ObjectProperty<Enum> sectorProperty() {
    return this.sector.getProperty();
  }

  public ObjectProperty<String> industryProperty() {
    return this.industry.getProperty();
  }

  public ObjectProperty<List<Workplace>> workplacesProperty() {
    return this.workplaces.getProperty();
  }

  public StringProperty workplacesNamesProperty() {
    return this.workplacesNames;
  }

}