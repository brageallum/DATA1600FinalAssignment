package fa.models;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Store {

  private static BooleanProperty loading = new SimpleBooleanProperty(false);
  private static StringProperty loadingText = new SimpleStringProperty("");

  public static BooleanProperty loadingProperty() {
    return Store.loading;
  }

  public static StringProperty loadingTextProperty() {
    return Store.loadingText;
  }

}
