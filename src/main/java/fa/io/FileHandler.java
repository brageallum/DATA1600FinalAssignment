package fa.io;

import javafx.stage.FileChooser;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

public class FileHandler {

  private String InitialDirectory = Paths.get("./src/main/resources/fa/data").toAbsolutePath().normalize().toString();

  private static final FileChooser.ExtensionFilter allowedExt =
    new FileChooser.ExtensionFilter("All compatible types", "*.jobj", "*.csv");

  private static final FileChooser.ExtensionFilter csvExt =
    new FileChooser.ExtensionFilter("Comma separated values", "*.csv");

  private static final FileChooser.ExtensionFilter jobjExt =
    new FileChooser.ExtensionFilter("Serialized java objects", "*.jobj");

  public void importFile() {
    File file = getFileChooser().showOpenDialog(null);
    if (file != null) {
      String extension = getFileExtension(file);

      try {
        Reader.read(extension, file);
      } catch (IOException e) {
        // TODO: Error handling.
        e.printStackTrace();
      }
    }
  }

  public void exportFile() {
    File file = getFileChooser().showSaveDialog(null);
    if (file != null) {
      String extension = getFileExtension(file);

      try {
        Writer.write(extension, file);
      } catch (IOException e) {
        // TODO: Error handling.
        e.printStackTrace();
      }
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
