package fa.models;

import fa.utils.SerializableObservableList;
import javafx.beans.Observable;
import javafx.collections.ObservableList;

import java.io.Serializable;

public class DB implements Serializable {
  private static DB instance;

  private final SerializableObservableList<JobSeeker> jobSeekers = new SerializableObservableList<>(jobSeeker -> new Observable[] {
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

  public static DB getInstance() {
    if (instance == null) {
      instance = new DB();
    }
    return instance;
  }

  public static DB getDetachedInstance() {
    return new DB();
  }

  public static void replaceInstance(DB newDb) {
    if (instance == null) {
      instance = new DB();
    }
    instance.getJobSeekers().setAll(newDb.getJobSeekers());
  }

  public ObservableList<JobSeeker> getJobSeekers() {
    return jobSeekers.getObservableList();
  }

  public void clearAll() {
    this.jobSeekers.getObservableList().removeAll();
  }

  @Override
  public String toString() {
    StringBuilder returnData = new StringBuilder();
    for (JobSeeker seeker : this.jobSeekers.getObservableList()) {
      returnData.append(seeker.toString());
    }
    return returnData.toString();
  }
}
