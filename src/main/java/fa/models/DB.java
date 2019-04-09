package fa.models;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class DB {

  private final ObservableList<Jobseeker> jobseekers = FXCollections.observableArrayList(jobseeker -> new Observable[] {
    jobseeker.getFirstName(),
    jobseeker.getLastName(),
    jobseeker.getEmailAddress(),
    jobseeker.getPhoneNumber(),
    jobseeker.getEducation(),
    jobseeker.getWorkExperience(),
    jobseeker.getWage(),
    jobseeker.getReferences()
  });

  private static DB instance;

  public static DB init() {
    if (instance == null) {
      instance = new DB();
    }
    return instance;
  }

  private DB() {}

  public void setJobseekers(ArrayList<Jobseeker> jobseekers) {
    this.jobseekers.addAll(jobseekers);
  }

  public ObservableList<Jobseeker> getJobseekers() {
    return this.jobseekers;
  }

  @Override
  public String toString() {
    StringBuilder returnData = new StringBuilder();
    for (Jobseeker seeker : this.jobseekers) {
      returnData.append(seeker.toString());
    }
    return returnData.toString();
  }

}
