package fa.io;

import fa.io.read.ReadTask;
import fa.io.write.WriteTask;
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
    new Thread(new ReadTask(extension, file)).start();
  }

  public void openExportDataDialog() {
    File file = getFileChooser().showSaveDialog(null);
    if (file != null) {
      exportData(file);
    }
  }

  public void exportData(File file) {
    String extension = getFileExtension(file);
    new Thread(new WriteTask(extension, file)).start();
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
