package fa.models;

import java.util.ArrayList;
import java.util.Date;

public class Jobseeker extends Person {

  private String education;
  private String workExperience;
  private Integer wage;
  private String references;

  public Jobseeker(String line) {

    String[] data = line.split(",");
    setFirstName(data[0]);
    setLastName(data[1]);
    setEmailAddress(data[2]);
    setPhoneNumber(data[3]);
    //setBirthdate(data[4]); // Handle Date
    setEducation(data[5]);
    setWorkExperience(data[6]);
    setWage(Integer.parseInt(data[7]));
    setReferences(data[8]);

  }

  public String getEducation() {
    return education;
  }

  public String getWorkExperience() {
    return workExperience;
  }

  public Integer getWage() {
    return wage;
  }

  public String getReferences() {
    return references;
  }

  public void setEducation(String education) {
    this.education = education;
  }

  public void setWorkExperience(String workExperience) {
    this.workExperience = workExperience;
  }

  public void setWage(Integer wage) {
    this.wage = wage;
  }

  public void setReferences(String references) {
    this.references = references;
  }

  @Override
  public String toString() {
    return String.format("Name: %s, Education: %s", getFirstName(), getLastName());
  }
}
