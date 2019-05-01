package fa.models;

import fa.utils.SearchMatcher;
import fa.utils.serialization.SerializableProperty;
import javafx.beans.property.ObjectProperty;

import java.io.Serializable;

public class Employment extends Model implements Serializable {
  private static int nextId = 100;

  private final SerializableProperty<Substitute> substitute;
  private final SerializableProperty<TemporaryPosition> temporaryPosition;

  private final int ID;

  public Employment() {
    this(null, null);
  }

  public Employment(Substitute substitute, TemporaryPosition temporaryPosition) {
    this(nextId, substitute, temporaryPosition);
  }

  public Employment(int ID, Substitute substitute, TemporaryPosition temporaryPosition) {
    if (ID >= nextId) nextId = ID + 1;
    this.ID = ID;
    this.substitute = new SerializableProperty<>(substitute);
    this.temporaryPosition = new SerializableProperty<>(temporaryPosition);
  }

  public ObjectProperty<Substitute> substituteProperty() {
    return this.substitute.getProperty();
  }

  public ObjectProperty<TemporaryPosition> temporaryPositionProperty() {
    return this.temporaryPosition.getProperty();
  }

  @Override
  public int getID() {
    return this.ID;
  }

  @Override
  public boolean matchesSearch(String pattern) {
    return SearchMatcher.matches(pattern, Integer.toString(this.ID), this.substitute.toString(), this.temporaryPosition.toString());
  }

  @Override
  public String toString() {
    return String.format(
      "%s | %s",
      this.substituteProperty().getValue().toString(),
      this.temporaryPositionProperty().getValue().toString()
    );
  }
}
