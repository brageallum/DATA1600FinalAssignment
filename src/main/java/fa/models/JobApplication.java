package fa.models;

import java.io.Serializable;

public class JobApplication extends SubstituteTemporaryPositionRelationship implements Serializable {
  public JobApplication() {
    this(null, null);
  }

  public JobApplication(Substitute substitute, TemporaryPosition temporaryPosition) {
    super(substitute, temporaryPosition);
  }

  public JobApplication(int ID, Substitute substitute, TemporaryPosition temporaryPosition) {
    super(ID, substitute, temporaryPosition);
  }

  public boolean has(Substitute substitute) {
    return this.substituteProperty().getValue().equals(substitute);
  }

  public boolean has(TemporaryPosition temporaryPosition) {
    return this.temporaryPositionProperty().getValue().equals(temporaryPosition);
  }
}
