package fa.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class DialogHandler {
  public static void showErrorDialog(String header, String message) {
    Alert alert = new Alert(Alert.AlertType.ERROR, "This will be overwritten", ButtonType.OK);
    alert.setHeaderText(header);

    // The dialog does not wrap text by default. By adding the message as a Text node, we can enable text wrapping.
    Text cont = new Text(message);
    cont.setWrappingWidth(380);
    alert.getDialogPane().setContent(new VBox(cont)); // The VBox allows for adding padding

    alert.getDialogPane().getStylesheets().add(DialogHandler.class.getResource("../styles.css").toExternalForm());
    alert.showAndWait();
  }
}
