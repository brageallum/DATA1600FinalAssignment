package fa.models;

import fa.utils.serialization.SerializableObservableList;
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

  private final SerializableObservableList<JobSeeker> jobSeekers = new SerializableObservableList<>(jobSeeker -> new Observable[]{
    jobSeeker.firstNameProperty(),
    jobSeeker.lastNameProperty(),
    jobSeeker.emailAddressProperty(),
    jobSeeker.phoneNumberProperty(),
    jobSeeker.educationProperty(),
    jobSeeker.workExperienceProperty(),
    jobSeeker.wageProperty(),
    jobSeeker.referencesProperty()
  });

  private final SerializableObservableList<Workplace> workplaces = new SerializableObservableList<>(workplace -> new Observable[]{
    workplace.workplaceProperty(),
    workplace.employerProperty(),
    workplace.categoryProperty(),
    workplace.durationProperty(),
    workplace.workingHoursProperty(),
    workplace.descriptionProperty(),
    workplace.positionProperty(),
    workplace.qualificationsProperty(),
    workplace.wageProperty(),
    workplace.conditionsProperty(),
    workplace.phoneNumberProperty(),
    workplace.emailAddressProperty()
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
    instance.getWorkplaces().setAll(newDb.getWorkplaces());
  }

  public ObservableList<JobSeeker> getJobSeekers() {
    return jobSeekers.getObservableList();
  }

  public ObservableList<Workplace> getWorkplaces() {
    return workplaces.getObservableList();
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
