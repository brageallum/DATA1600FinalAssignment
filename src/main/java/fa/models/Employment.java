package fa.models;

import fa.utils.SearchMatcher;

import java.io.Serializable;

public class Employment extends SubstituteTemporaryPositionRelationship implements Serializable {
  private static int nextId = 100;
  private final int ID;

  public Employment() {
    this(null, null);
  }

  public Employment(Substitute substitute, TemporaryPosition temporaryPosition) {
    this(nextId, substitute, temporaryPosition);
  }

  public Employment(int ID, Substitute substitute, TemporaryPosition temporaryPosition) {
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
}
