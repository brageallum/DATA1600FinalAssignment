package fa.io;

import fa.models.DB;
import javafx.concurrent.Task;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FileHandler {

  private String InitialDirectory = Paths.get("./src/main/resources/fa/data").toAbsolutePath().normalize().toString();

  private static final FileChooser.ExtensionFilter allowedExt =
    new FileChooser.ExtensionFilter("All compatible types", "*.jobj", "*.csv");

  private static final FileChooser.ExtensionFilter csvExt =
    new FileChooser.ExtensionFilter("Comma separated values", "*.csv");

  private static final FileChooser.ExtensionFilter jobjExt =
    new FileChooser.ExtensionFilter("Serialized java objects", "*.jobj");

  public void openImportDataDialog() {
    File file = getFileChooser().showOpenDialog(null);
    if (file != null) {
      importData(file);
    }
  }

  private static final Lock ioLock = new ReentrantLock();

  public void importData(File file) {
    String extension = getFileExtension(file);

    Task<DB> task = new Task<>() {
      @Override
      protected DB call() throws Exception {
        synchronized (ioLock) {
          try {
            return Reader.read(extension, file);
          } catch (IOException e) {
            // TODO: Error handling.
            e.printStackTrace();
          } catch (ClassNotFoundException e) {
            e.printStackTrace();
          }
        }
        return null;
      }
    };

    task.setOnSucceeded(eventHandler -> DB.replaceInstance(task.getValue()));

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

    try {
      Writer.write(extension, file);
    } catch (IOException e) {
      // TODO: Error handling.
      e.printStackTrace();
    }
  }

  private FileChooser getFileChooser() {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setInitialDirectory(new File(InitialDirectory));
    fileChooser.getExtensionFilters().addAll(allowedExt, csvExt, jobjExt);

    return fileChooser;
  }

  private String getFileExtension(File file) {
    return file.getName().split("\\.")[1];
  }
}
