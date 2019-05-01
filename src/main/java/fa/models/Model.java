package fa.models;

import java.io.Serializable;

public abstract class Model implements Serializable {
  public abstract int getID();
  public abstract boolean matchesSearch(String pattern);
}
