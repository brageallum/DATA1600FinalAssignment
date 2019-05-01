package fa.models;

import fa.utils.SearchMatcher;
import fa.utils.serialization.SerializableProperty;
import javafx.beans.property.ObjectProperty;

import java.io.Serializable;
import java.time.LocalDate;

public abstract class Person extends Model implements Serializable {
  private final SerializableProperty<String> firstName;
  private final SerializableProperty<String> lastName;
  private final SerializableProperty<String> emailAddress;
  private final SerializableProperty<String> phoneNumber;
  private final SerializableProperty<LocalDate> birthDate;
  private final SerializableProperty<String> address;

  public Person(String firstName, String lastName, String emailAddress, String phoneNumber, LocalDate birthDate, String address) {
    this.firstName = new SerializableProperty<>(firstName);
    this.lastName = new SerializableProperty<>(lastName);
    this.emailAddress = new SerializableProperty<>(emailAddress);
    this.phoneNumber = new SerializableProperty<>(phoneNumber);
    this.birthDate = new SerializableProperty<>(birthDate);
    this.address = new SerializableProperty<>(address);
  }

  public boolean matchesSearch(String regex) {
    return SearchMatcher.matches(
      regex,
      this.firstNameProperty().getValue(),
      this.lastNameProperty().getValue(),
      this.emailAddressProperty().getValue(),
      this.phoneNumberProperty().getValue(),
      this.birthDateProperty().getValue().toString(),
      this.addressProperty().getValue()
    );
  }

  public ObjectProperty<String> firstNameProperty() {
    return firstName.getProperty();
  }

  public ObjectProperty<String> lastNameProperty() {
    return lastName.getProperty();
  }

  public ObjectProperty<String> emailAddressProperty() {
    return emailAddress.getProperty();
  }

  public ObjectProperty<String> phoneNumberProperty() {
    return phoneNumber.getProperty();
  }

  public ObjectProperty<LocalDate> birthDateProperty() {
    return birthDate.getProperty();
  }

  public ObjectProperty<String> addressProperty() {
    return address.getProperty();
  }
}
