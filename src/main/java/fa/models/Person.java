package fa.models;

import java.util.Date;

abstract class Person {

  private String firstName;
  private String lastName;
  private String emailAddress;
  private String phoneNumber;
  private Date birthdate;

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getEmailAddress() {
    return emailAddress;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public Date getBirthdate() {
    return birthdate;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public void setEmailAddress(String emailAddress) {
    this.emailAddress = emailAddress;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public void setBirthdate(Date birthdate) {
    this.birthdate = birthdate;
  }
}
