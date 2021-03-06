package fa.models;

import fa.DB;
import fa.utils.SearchMatcher;
import fa.utils.serialization.SerializableProperty;
import javafx.beans.property.ObjectProperty;

import java.io.Serializable;
import java.time.LocalDate;

public class Employer extends Person implements Serializable {
  private final SerializableProperty<DB.sectorOptions> sector;
  private final SerializableProperty<String> industry;

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
    super(firstName,lastName,emailAddress,phoneNumber,birthDate,address);
    this.sector = new SerializableProperty<>(sector);
    this.industry = new SerializableProperty<>(industry);
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
    super(ID, firstName,lastName,emailAddress,phoneNumber,birthDate,address);
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

  public ObjectProperty<DB.sectorOptions> sectorProperty() {
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
