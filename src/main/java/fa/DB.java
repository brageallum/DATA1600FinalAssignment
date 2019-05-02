package fa;

import fa.models.*;
import fa.utils.serialization.SerializableObservableList;
import javafx.beans.Observable;
import javafx.collections.ObservableList;

import java.io.Serializable;
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

  private final SerializableObservableList<Substitute> substitute = new SerializableObservableList<>(substitute -> new Observable[]{
    substitute.firstNameProperty(),
    substitute.lastNameProperty(),
    substitute.emailAddressProperty(),
    substitute.phoneNumberProperty(),
    substitute.educationProperty(),
    substitute.workExperienceProperty(),
    substitute.wageProperty(),
    substitute.referencesProperty(),
    substitute.birthDateProperty()
  });

  private final SerializableObservableList<TemporaryPosition> temporaryPositions = new SerializableObservableList<>(temporaryPosition -> new Observable[]{
    temporaryPosition.workplaceProperty(),
    temporaryPosition.employerProperty(),
    temporaryPosition.categoryProperty(),
    temporaryPosition.durationProperty(),
    temporaryPosition.workingHoursProperty(),
    temporaryPosition.descriptionProperty(),
    temporaryPosition.positionProperty(),
    temporaryPosition.qualificationsProperty(),
    temporaryPosition.wageProperty(),
    temporaryPosition.conditionsProperty(),
    temporaryPosition.phoneNumberProperty(),
    temporaryPosition.emailAddressProperty()
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

  private final SerializableObservableList<Employment> employments = new SerializableObservableList<>(employment -> new Observable[]{
    employment.substituteProperty(),
    employment.temporaryPositionProperty()
  });

  private final SerializableObservableList<JobApplication> jobApplications = new SerializableObservableList<>(jobApplication -> new Observable[]{
    jobApplication.substituteProperty(),
    jobApplication.temporaryPositionProperty()
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

    instance.getSubstitutes().setAll(newDb.getSubstitutes());
    instance.getTemporaryPositions().setAll(newDb.getTemporaryPositions());
    instance.getEmployers().setAll(newDb.getEmployers());
    instance.getJobApplications().setAll(newDb.getJobApplications());
    instance.getEmployments().setAll(newDb.getEmployments());
  }

  public static boolean temporaryPositionIsAvailable(TemporaryPosition temporaryPosition) {
    return !DB.getInstance().getEmployments()
        .stream()
        .map(emp -> emp.temporaryPositionProperty().getValue())
        .collect(Collectors.toList())
        .contains(temporaryPosition);
  }

  public static boolean substituteHasAppliedToTemporaryPosition(Substitute substitute, TemporaryPosition temporaryPosition) {
    return DB.getInstance().getJobApplications().filtered(jobApplication ->
        jobApplication.has(substitute) && jobApplication.has(temporaryPosition)
      ).size() > 0;
  }

  public ObservableList<Substitute> getSubstitutes() {
    return substitute.getObservableList();
  }

  public ObservableList<TemporaryPosition> getTemporaryPositions() {
    return temporaryPositions.getObservableList();
  }

  public ObservableList<Employer> getEmployers() {
    return employers.getObservableList();
  }

  public ObservableList<Employment> getEmployments() {
    return employments.getObservableList();
  }

  public ObservableList<JobApplication> getJobApplications() {
    return jobApplications.getObservableList();
  }

  public TemporaryPosition getTemporaryPosition(int id) {
    try {
      return this.getTemporaryPositions()
        .filtered(s -> (s.getID() == id))
        .get(0);
    } catch(IndexOutOfBoundsException e) {
      // TODO: Add custom error
      throw new Error("No temporaryPosition found for this field.");
    }
  }

  public Substitute getSubstitute(int id) {
    try {
      return this.getSubstitutes()
        .filtered(s -> (s.getID() == id))
        .get(0);
    } catch(IndexOutOfBoundsException e) {
      // TODO: Add custom error
      throw new Error("No temporaryPosition found for this field.");
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

  public ObservableList<TemporaryPosition> getTemporaryPositionFromEmployer(Employer employer) throws IndexOutOfBoundsException {
    return this.getTemporaryPositions()
      .filtered(
        tp -> tp.employerProperty().getValue().equals(employer)
      );
  }
}
