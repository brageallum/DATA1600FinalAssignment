package fa.io;

import fa.io.read.Reader;
import fa.io.write.Writer;
import fa.models.DB;
import javafx.concurrent.Task;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

import java.io.File;
import java.nio.file.Paths;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FileHandler {

  private String InitialDirectory = Paths.get("./src/main/resources/fa/data").toAbsolutePath().normalize().toString();

  private static final Lock ioLock = new ReentrantLock();

  private static final FileChooser.ExtensionFilter allowedExt = new FileChooser.ExtensionFilter(
    "All compatible types",
    "*.jobj", "*.csv"
  );

  private static final FileChooser.ExtensionFilter csvExt = new FileChooser.ExtensionFilter(
    "Comma separated values",
    "*.csv"
  );

  private static final FileChooser.ExtensionFilter jobjExt = new FileChooser.ExtensionFilter(
    "Serialized java objects",
    "*.jobj"
  );

  public void openImportDataDialog() {
    File file = getFileChooser().showOpenDialog(null);
    if (file != null) {
      importData(file);
    }
  }

  public void importData(File file) {
    String extension = getFileExtension(file);

    Task<DB> task = new Task<>() {
      @Override
      protected DB call() throws Exception {
        synchronized (ioLock) {
          return Reader.read(extension, file);
        }
      }
    };

    task.setOnSucceeded(eventHandler -> DB.replaceInstance(task.getValue()));

    task.setOnFailed(e -> {
      task.getException().printStackTrace();
      showErrorDialog(
        "Error while importing data from file: " + file.getName(),
        task.getException().getMessage()
      );
    });

    new Thread(task).start();
  }

  public void openExportDataDialog() {
    File file = getFileChooser().showSaveDialog(null);
    if (file != null) {
      exportData(file);
    }
  }

  public void exportData(File file) {
    String extension = getFileExtension(file);

    Task<Void> task = new Task<>() {
      @Override
      protected Void call() throws Exception {
        synchronized (ioLock) {
          Writer.write(extension, file);
          return null;
        }
      }
    };

    task.setOnFailed(e -> {
      task.getException().printStackTrace();
      showErrorDialog(
        "Error while exporting data to file: " + file.getName(),
        task.getException().getMessage()
      );
    });

    new Thread(task).start();
  }

  private void showErrorDialog(String header, String message) {
    Alert alert = new Alert(Alert.AlertType.ERROR, "This will be overwritten", ButtonType.OK);
    alert.setHeaderText(header);

    // The dialog does not wrap text by default. By adding the message as a Text node, we can enable text wrapping.
    Text cont = new Text(message);
    cont.setWrappingWidth(380);
    alert.getDialogPane().setContent(new VBox(cont)); // The VBox allows for adding padding

    alert.getDialogPane().getStylesheets().add(getClass().getResource("../styles.css").toExternalForm());
    alert.showAndWait();
  }

  private FileChooser getFileChooser() {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setInitialDirectory(new File(InitialDirectory));
    fileChooser.getExtensionFilters().addAll(allowedExt, csvExt, jobjExt);

    return fileChooser;
  }

  private String getFileExtension(File file) {
    String[] pieces = file.getName().split("\\.");
    return pieces[pieces.length - 1];
  }
}
