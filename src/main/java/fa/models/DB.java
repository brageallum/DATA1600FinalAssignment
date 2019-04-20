package fa.models;

import fa.utils.SerializableObservableList;
import javafx.beans.Observable;
import javafx.collections.ObservableList;

import java.io.Serializable;

/**
 * This is where all our data is stored. It uses the singleton pattern to make a single instance
 * accessible throughout the application, but can also be instantiated as a local variable, which allows
 * for storing data locally without effecting the application UI (needed for reading files).
 */
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

  @Override
  public String toString() {
    StringBuilder returnData = new StringBuilder();
    for (JobSeeker seeker : this.jobSeekers.getObservableList()) {
      returnData.append(seeker.toString());
    }
    return returnData.toString();
  }
}
