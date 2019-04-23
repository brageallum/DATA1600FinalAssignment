package fa.utils.serialization;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.io.Serializable;

/**
 * A simple wrapper class for JavaFX's ObjectProperty, which allows for serialization.
 */

public class SerializableProperty<T> implements Serializable {
  private T variable;
  private transient ObjectProperty<T> property;

  public SerializableProperty(T variable) {
    this.variable = variable;
  }

  public ObjectProperty<T> getProperty() {
    if (property == null) {
      initializeProperty();
    }
    return property;
  }

  private void initializeProperty() {
    property = new SimpleObjectProperty<>(variable);
    property.addListener((observableValue, oldValue, newValue) -> variable = property.getValue());
  }
}
