package fa.models;

import java.io.Serializable;

public class Employment extends SubstituteTemporaryPositionRelationship implements Serializable {
  public Employment() {
    this(null, null);
  }

  public Employment(Substitute substitute, TemporaryPosition temporaryPosition) {
    super(substitute, temporaryPosition);
  }

  public Employment(int ID, Substitute substitute, TemporaryPosition temporaryPosition) {
    super(ID, substitute, temporaryPosition);
  }
}
