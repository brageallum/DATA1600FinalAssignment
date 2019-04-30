package fa.models;

import fa.utils.Identifiable;
import fa.utils.SearchMatcher;
import fa.utils.Searchable;
import fa.utils.serialization.SerializableProperty;
import javafx.beans.property.ObjectProperty;
import java.io.Serializable;
import java.time.LocalDate;

public class Employer extends Person implements Serializable, Searchable, Identifiable {

  private static int nextId = 100;

  private final SerializableProperty<Enum> sector;
  private final SerializableProperty<String> industry;

  private final int ID;

  public Employer() {
    this(null, null, null, null, null, null, null, null);
  }

  public Employer(
    String firstName,
    String lastName,
    DB.sectorOptions sector,
    String address,
    String industry,
    String phoneNumber,
    String emailAddress,
    LocalDate birthDate
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
      birthDate
    );
  }

  public Employer(
    int ID,
    String firstName,
    String lastName,
    DB.sectorOptions sector,
    String address,
    String industry,
    String phoneNumber,
    String emailAddress,
    LocalDate birthDate
  ) {
    super(firstName,lastName,emailAddress,phoneNumber,birthDate,address);

    if (ID >= nextId) nextId = ID + 1;

    this.ID = ID;
    this.sector = new SerializableProperty<>(sector);
    this.industry = new SerializableProperty<>(industry);
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

  @Override
  public String toString() {
    return String.format("%s %s", this.firstNameProperty().getValue(), this.lastNameProperty().getValue());
  }

}
