package fa.models;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DB {
  private static DB instance;

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

  private DB() {}

  public static DB init() {
    if (instance == null) {
      instance = new DB();
    }
    return instance;
  }


  public ObservableList<JobSeeker> getJobSeekers() {
    return this.jobSeekers;
  }

  public void clearAll() {
    this.jobSeekers.removeAll();
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
