package fa;

import fa.models.*;
import fa.utils.serialization.SerializableObservableList;
import javafx.beans.Observable;
import javafx.collections.ObservableList;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This is where all our data is stored. It uses the singleton pattern to make a single instance
 * accessible throughout the application, but can also be instantiated as a local variable, which allows
 * for storing data locally without effecting the application UI (needed for reading files).
 */
public class DB implements Serializable {
  private static DB instance;

  public enum sectorOptions {
    Private,
    Public
  }

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

  private final SerializableObservableList<TemporaryPosition> temporaryPositions = new SerializableObservableList<>(workplace -> new Observable[]{
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

  private final SerializableObservableList<EmployerWorkplace> employerWorkplaces = new SerializableObservableList<>(employerWorkplace -> new Observable[]{
    employerWorkplace.employerProperty(),
    employerWorkplace.workplacesProperty()
  });

  private final SerializableObservableList<Employment> employments = new SerializableObservableList<>(employment -> new Observable[]{
    employment.jobSeekerProperty(),
    employment.temporaryPositionProperty()
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
    instance.getTemporaryPositions().setAll(newDb.getTemporaryPositions());
    instance.getEmployers().setAll(newDb.getEmployers());
    instance.getEmployerWorkplaces().setAll(newDb.getEmployerWorkplaces());
    instance.getEmployments().setAll(newDb.getEmployments());
  }

  public ObservableList<JobSeeker> getJobSeekers() {
    return jobSeekers.getObservableList();
  }

  public ObservableList<TemporaryPosition> getTemporaryPositions() {
    return temporaryPositions.getObservableList();
  }

  public ObservableList<Employer> getEmployers() {
    return employers.getObservableList();
  }

  public ObservableList<EmployerWorkplace> getEmployerWorkplaces() {
    return employerWorkplaces.getObservableList();
  }

  public ObservableList<Employment> getEmployments() {
    return employments.getObservableList();
  }

  public TemporaryPosition getTemporaryPosition(int id) {
    try {
      return this.getTemporaryPositions()
        .filtered(s -> (s.getID() == id))
        .get(0);
    } catch(IndexOutOfBoundsException e) {
      // TODO: Add custom error
      throw new Error("No workplace found for this field.");
    }
  }

  public Employer getEmployer(int id) {
    try {
      return this.getEmployers()
        .filtered(s -> (s.getID() == id))
        .get(0);
    } catch(IndexOutOfBoundsException e) {
      // TODO: Add custom error
      throw new Error("No employer found for this field.");
    }

  }

  public JobSeeker getJobSeeker(int id) {
    try {
      return this.getJobSeekers()
        .filtered(s -> (s.getID() == id))
        .get(0);
    } catch(IndexOutOfBoundsException e) {
      // TODO: Add custom error
      throw new Error("No job seeker found for this field.");
    }
  }

  public List<JobSeeker> getJobSeekersFromTemporaryPosition(int id) throws IndexOutOfBoundsException {
    // TODO: ADD LOGIC
    return new ArrayList<JobSeeker>();
  }

  public EmployerWorkplace getWorkplacesFromEmployer(int id) throws IndexOutOfBoundsException {
    return this.getEmployerWorkplaces()
      .filtered(s -> (s.employerProperty().getValue().getID() == id))
      .get(0);
  }

  public List<TemporaryPosition> getTemporaryPositions(String data) {
    return Arrays.stream(data.split(","))
      .map(s -> this.getTemporaryPosition(Integer.parseInt(s)))
      .collect(Collectors.toList());
  }
}
