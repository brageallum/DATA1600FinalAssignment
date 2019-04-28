package fa.models;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Store {

  private static BooleanProperty loading = new SimpleBooleanProperty(false);
  private static StringProperty loadingText = new SimpleStringProperty("");
  private static BooleanProperty dbInitialized = new SimpleBooleanProperty(false);

  public static BooleanProperty loadingProperty() {
    return Store.loading;
  }

  public static StringProperty loadingTextProperty() {
    return Store.loadingText;
  }

  public static BooleanProperty dbInitializedProperty() {
    return Store.dbInitialized;
  }
}
