package fa.models;

import fa.utils.serialization.SerializableObservableList;
import javafx.beans.Observable;
import javafx.collections.ObservableList;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

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
    jobSeeker.referencesProperty(),
    jobSeeker.birthDateProperty()
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

  private final SerializableObservableList<Employer> employers = new SerializableObservableList<>(employer -> new Observable[]{
    employer.firstNameProperty(),
    employer.lastNameProperty(),
    employer.sectorProperty(),
    employer.addressProperty(),
    employer.industryProperty(),
    employer.phoneNumberProperty(),
    employer.emailAddressProperty(),
    employer.birthDateProperty()
  });

  private final int h = 1;


  private DB() {}

  public static DB getInstance() {
    System.out.println("DB INITIALIZED");
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
    instance.getEmployers().setAll(newDb.getEmployers());
  }

  public ObservableList<JobSeeker> getJobSeekers() {
    return jobSeekers.getObservableList();
  }

  public ObservableList<Workplace> getWorkplaces() {
    return workplaces.getObservableList();
  }

  public ObservableList<Employer> getEmployers() {
    return employers.getObservableList();
  }

  public JobSeeker getJobSeeker(int id) {
    List<JobSeeker> jsList = getJobSeekers()
      .stream()
      .filter(s -> (s.getID() == id))
      .collect(Collectors.toList());
    try {
      return jsList.get(0);
    } catch(IndexOutOfBoundsException e) {
      System.out.println(jsList);
      throw new Error("Index 0 does not exist");
    }
  }

  public Workplace getWorkplace(int id) {
    return new Workplace();
  }

  public Employer getEmployer(int id) {
    List<Employer> empList = getEmployers()
      .stream()
      .filter(s -> (s.getID() == id))
      .collect(Collectors.toList());
    try {
      return empList.get(0);
    } catch(IndexOutOfBoundsException e) {
      throw new Error("Index 0 does not exist");
    }
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
