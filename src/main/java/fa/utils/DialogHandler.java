package fa.utils;

import fa.Store;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.Optional;

public class DialogHandler {
  public static void showErrorDialog(String header, String message) {
    Alert alert = new Alert(Alert.AlertType.ERROR, message, ButtonType.OK);
    alert.setHeaderText(header);
    alert.initOwner(Store.stage);
    DialogHandler.enableTextWrapping(alert);

    alert.showAndWait();
  }

  public static Optional<ButtonType> showWarningDialog(String header, String message, String okText) {
    ButtonType exit = new ButtonType(okText, ButtonBar.ButtonData.OK_DONE);
    ButtonType cancel = ButtonType.CANCEL;

    Alert alert = new Alert(Alert.AlertType.WARNING, message, exit, cancel);
    alert.setTitle("Warning!");
    alert.setHeaderText(header);
    alert.initOwner(Store.stage);
    DialogHandler.enableTextWrapping(alert);

    return alert.showAndWait();
  }

  private static void enableTextWrapping(Dialog dialog) {
    Text cont = new Text(dialog.getContentText());
    cont.setWrappingWidth(380);
    dialog.getDialogPane().setContent(new VBox(cont)); // The VBox allows for adding padding
  }
}
