package fa.models;

import fa.utils.SearchMatcher;
import fa.utils.Searchable;
import fa.utils.serialization.SerializableProperty;
import javafx.beans.property.ObjectProperty;

import java.io.Serializable;
import java.time.LocalDate;

public class Employer extends Person implements Serializable, Searchable {

  public final int ID;
  private static int nextId = 100;
  private final SerializableProperty<Enum> sector;
  private final SerializableProperty<String> industry;

  public Employer() {
    this(null, null, null, null, null, null, null, null);
  }

  public Employer(
    String firstName,
    String lastName,
    Enum sector,
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
    Enum sector,
    String address,
    String industry,
    String phoneNumber,
    String emailAddress,
    LocalDate birthDate
  ) {
    super(firstName,lastName,emailAddress,phoneNumber,birthDate,address);

    if (ID > nextId) nextId = ID + 1;

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
    return ID;
  }

  public ObjectProperty<Enum> sectorProperty() {
    return sector.getProperty();
  }

  public ObjectProperty<String> industryProperty() {
    return industry.getProperty();
  }

}
