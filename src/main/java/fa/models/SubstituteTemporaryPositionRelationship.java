package fa.models;

import fa.utils.SearchMatcher;
import fa.utils.serialization.SerializableProperty;
import javafx.beans.property.ObjectProperty;

import java.io.Serializable;

public abstract class SubstituteTemporaryPositionRelationship extends Model implements Serializable {
  private final SerializableProperty<Substitute> substitute;
  private final SerializableProperty<TemporaryPosition> temporaryPosition;

  public SubstituteTemporaryPositionRelationship(Substitute substitute, TemporaryPosition temporaryPosition) {
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
  public boolean matchesSearch(String pattern) {
    return SearchMatcher.matches(pattern, this.substitute.toString(), this.temporaryPosition.toString());
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
