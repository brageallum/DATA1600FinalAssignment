package fa;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.stage.Stage;

public class Store {

  private static BooleanProperty loading = new SimpleBooleanProperty(false);
  private static StringProperty loadingText = new SimpleStringProperty("");
  public static Stage stage;

  public static BooleanProperty loadingProperty() {
    return Store.loading;
  }

  public static StringProperty loadingTextProperty() {
    return Store.loadingText;
  }

  public static void setLoading(String loadingText) {
    Store.loadingProperty().set(true);
    Store.loadingTextProperty().set(loadingText);
  }

  public static void unsetLoading() {
    Store.loadingProperty().set(false);
    Store.loadingTextProperty().set("");
  }
}
