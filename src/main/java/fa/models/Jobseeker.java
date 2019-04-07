package fa.models;

import java.util.ArrayList;
import java.util.Date;

public class Jobseeker extends Person {

  private String education;
  private ArrayList<Work> workExperience;
  private Integer wage;
  private ArrayList<Person> references;

  public Jobseeker(String first_name,String last_name, String email, String phone,
                    Date birthdate, String education, ArrayList<Work> work_experience,
                    Integer wage, ArrayList<Person> references) {

    setFirstName(first_name);
    setLastName(last_name);
    setEmailAddress(email);
    setPhoneNumber(phone);
    setBirthdate(birthdate);
    setEducation(education);
    setWorkExperience(work_experience);
    setWage(wage);
    setReferences(references);

  }

  public String getEducation() {
    return education;
  }

  public ArrayList<Work> getWorkExperience() {
    return workExperience;
  }

  public Integer getWage() {
    return wage;
  }

  public ArrayList<Person> getReferences() {
    return references;
  }

  public void setEducation(String education) {
    this.education = education;
  }

  public void setWorkExperience(ArrayList<Work> workExperience) {
    this.workExperience = workExperience;
  }

  public void setWage(Integer wage) {
    this.wage = wage;
  }

  public void setReferences(ArrayList<Person> references) {
    this.references = references;
  }
}
