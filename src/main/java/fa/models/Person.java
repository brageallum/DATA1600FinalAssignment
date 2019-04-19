package fa.models;

import fa.utils.SerializableProperty;
import javafx.beans.property.ObjectProperty;

import java.io.Serializable;
import java.util.Date;

abstract class Person implements Serializable {

  private final SerializableProperty<String> firstName;
  private final SerializableProperty<String> lastName;
  private final SerializableProperty<String> emailAddress;
  private final SerializableProperty<String> phoneNumber;
  private final SerializableProperty<Date> birthDate; // TODO: implement birthDate

  Person(String firstName, String lastName, String emailAddress, String phoneNumber, Date birthDate) {
    this.firstName = new SerializableProperty<>(firstName);
    this.lastName = new SerializableProperty<>(lastName);
    this.emailAddress = new SerializableProperty<>(emailAddress);
    this.phoneNumber = new SerializableProperty<>(phoneNumber);
    this.birthDate = new SerializableProperty<>(birthDate);
  }

  public ObjectProperty<String> getFirstName() {
    return firstName.getProperty();
  }

  public ObjectProperty<String> getLastName() {
    return lastName.getProperty();
  }

  public ObjectProperty<String> getEmailAddress() {
    return emailAddress.getProperty();
  }

  public ObjectProperty<String> getPhoneNumber() {
    return phoneNumber.getProperty();
  }

  public ObjectProperty<Date> getBirthDate() {
    return birthDate.getProperty();
  }
}
