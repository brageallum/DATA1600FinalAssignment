package fa.models;

import fa.utils.serialization.SerializableProperty;
import javafx.beans.property.ObjectProperty;

public class Employment {

  private final SerializableProperty<JobSeeker> jobSeeker;
  private final SerializableProperty<Workplace> workPlace;

  public Employment() {
    this(null, null);
  }

  public Employment(JobSeeker jobseeker, Workplace workplace) {
    this.jobSeeker = new SerializableProperty<>(jobseeker);
    this.workPlace = new SerializableProperty<>(workplace);
  }

  public ObjectProperty<JobSeeker> jobSeekerProperty() {
    return this.jobSeeker.getProperty();
  }

  public ObjectProperty<Workplace> workplaceProperty() {
    return this.workPlace.getProperty();
  }

}
