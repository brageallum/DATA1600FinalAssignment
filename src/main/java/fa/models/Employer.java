package fa.models;

import fa.utils.SearchMatcher;
import fa.utils.Searchable;
import fa.utils.serialization.SerializableProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class Employer extends Person implements Serializable, Searchable {

  private final int ID;
  private static int nextId = 100;
  private final SerializableProperty<Enum> sector;
  private final SerializableProperty<String> industry;
  private final SerializableProperty<List<Workplace>> workplaces;
  private final transient StringProperty workplacesNames = new SimpleStringProperty();

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

    this.workplaces.getProperty().addListener((observableValue, oldValue, newValue) -> this.setWorkplacesNames());
  }

  private void setWorkplacesNames() {
    if (this.workplaces.getProperty().getValue() == null) {
      this.workplacesNames.set("No workplace(s)");
      return;
    }
    this.workplacesNames.set(this.workplaces.getProperty().getValue()
      .stream()
      .map(s -> null == s.workplaceProperty().getValue() ? s.workplaceProperty().getValue() : "No name set")
      .collect(Collectors.joining(", ")));
  }

  public boolean matchesSearch(String regex) {
    return super.matchesSearch(regex) || SearchMatcher.matches(
      regex,
      sectorProperty().getValue().toString(),
      industryProperty().getValue()
    );
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

  @Override
  public String toString() {
    return String.format("[%s] %s %s", this.ID, this.firstNameProperty().getValue(), this.lastNameProperty().getValue());
  }

}
