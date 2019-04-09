package fa.models;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Date;

abstract class Person {

  private final StringProperty firstName;
  private final StringProperty lastName;
  private final StringProperty emailAddress;
  private final StringProperty phoneNumber;
  private final ObjectProperty<Date> birthDate;

  Person(String firstName, String lastName, String emailAddress, String phoneNumber, Date birthDate) {
    this.firstName = new SimpleStringProperty(firstName);
    this.lastName = new SimpleStringProperty(lastName);
    this.emailAddress = new SimpleStringProperty(emailAddress);
    this.phoneNumber = new SimpleStringProperty(phoneNumber);
    this.birthDate = new SimpleObjectProperty<>(birthDate);
  }

  public StringProperty getFirstName() {
    return firstName;
  }

  public StringProperty getLastName() {
    return lastName;
  }

  public StringProperty getEmailAddress() {
    return emailAddress;
  }
  public StringProperty getPhoneNumber() {
    return phoneNumber;
  }
}
