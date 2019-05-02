package fa.models;

import fa.utils.SearchMatcher;

import java.io.Serializable;

public class JobApplication extends ManyToMany implements Serializable {
  private static int nextId = 100;
  private final int ID;

  public JobApplication() {
    this(null, null);
  }

  public JobApplication(Substitute substitute, TemporaryPosition temporaryPosition) {
    this(nextId, substitute, temporaryPosition);
  }

  public JobApplication(int ID, Substitute substitute, TemporaryPosition temporaryPosition) {
    super(substitute, temporaryPosition);
    if (ID >= nextId) nextId = ID + 1;
    this.ID = ID;
  }

  @Override
  public boolean matchesSearch(String pattern) {
    return SearchMatcher.matches(pattern, Integer.toString(this.ID)) || super.matchesSearch(pattern);
  }

  @Override
  public int getID() {
    return this.ID;
  }

  public boolean has(Substitute substitute) {
    return this.substituteProperty().getValue().equals(substitute);
  }

  public boolean has(TemporaryPosition temporaryPosition) {
    return this.temporaryPositionProperty().getValue().equals(temporaryPosition);
  }
}
