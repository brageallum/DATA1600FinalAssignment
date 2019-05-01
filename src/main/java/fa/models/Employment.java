package fa.models;

import fa.utils.serialization.SerializableProperty;
import javafx.beans.property.ObjectProperty;

public class Employment {

  private final SerializableProperty<JobSeeker> jobSeeker;
  private final SerializableProperty<TemporaryPosition> workPlace;

  public Employment() {
    this(null, null);
  }

  public Employment(JobSeeker jobseeker, TemporaryPosition temporaryPosition) {
    this.jobSeeker = new SerializableProperty<>(jobseeker);
    this.workPlace = new SerializableProperty<>(temporaryPosition);
  }

  public ObjectProperty<JobSeeker> jobSeekerProperty() {
    return this.jobSeeker.getProperty();
  }

  public ObjectProperty<TemporaryPosition> workplaceProperty() {
    return this.workPlace.getProperty();
  }

}
