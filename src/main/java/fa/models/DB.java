package fa.models;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class DB {

  private final ObservableList<JobSeeker> jobSeekers = FXCollections.observableArrayList(jobSeeker -> new Observable[] {
    jobSeeker.getFirstName(),
    jobSeeker.getLastName(),
    jobSeeker.getEmailAddress(),
    jobSeeker.getPhoneNumber(),
    jobSeeker.getEducation(),
    jobSeeker.getWorkExperience(),
    jobSeeker.getWage(),
    jobSeeker.getReferences()
  });

  private static DB instance;

  public static DB init() {
    if (instance == null) {
      instance = new DB();
    }
    return instance;
  }

  private DB() {}

  public void setJobSeekers(ArrayList<JobSeeker> jobSeekers) {
    this.jobSeekers.addAll(jobSeekers);
  }

  public ObservableList<JobSeeker> getJobSeekers() {
    return this.jobSeekers;
  }

  @Override
  public String toString() {
    StringBuilder returnData = new StringBuilder();
    for (JobSeeker seeker : this.jobSeekers) {
      returnData.append(seeker.toString());
    }
    return returnData.toString();
  }

}
