package fa.models;

import fa.utils.SearchMatcher;

import java.io.Serializable;

public abstract class Model implements Serializable {
  private static int nextId = 100;

  private final int ID;

  public boolean matchesSearch(String pattern) {
    return SearchMatcher.matches(pattern, Integer.toString(this.ID));
  }

  public int getID() {
    return this.ID;
  }

  public Model() {
    this(nextId);
  }

  public Model(int ID) {
    if (ID >= nextId) nextId = ID + 1;
    this.ID = ID;
  }
}
