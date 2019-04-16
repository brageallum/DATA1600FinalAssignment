package fa.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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

  public static JobSeeker fromMap(Map<String, String> map) {
    if (!(
      map.get("id") != null && map.get("id").matches("-?\\d+")
      && map.get("firstName") != null
      && map.get("lastName") != null
      && map.get("emailAddress") != null
      && map.get("phoneNumber") != null
//      && map.get("data") != null
      && map.get("education") != null
      && map.get("workExperience") != null
      && map.get("wage") != null && map.get("wage").matches("-?\\d+")
      && map.get("references") != null
    )) {
      // TODO: Throw exception (bad input file)
    }

    return new JobSeeker(
      Integer.parseInt(map.get("id")),
      map.get("firstName"),
      map.get("lastName"),
      map.get("emailAddress"),
      map.get("phoneNumber"),
      null,
      map.get("education"),
      map.get("workExperience"),
      Integer.parseInt(map.get("wage")),
      map.get("references")
    );
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

  @Override
  public Map<String, String> toMap() {
    Map<String, String> map = super.toMap();
    map.put("id", Integer.toString(ID));
    map.put("education", getEducation().getValue());
    map.put("workExperience", getWorkExperience().getValue());
    map.put("wage", getWage().getValue().toString());
    map.put("references", getReferences().getValue());
    return map;
  }
}
