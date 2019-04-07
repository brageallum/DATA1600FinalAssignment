package fa.io;

import fa.models.DB;
import javafx.stage.FileChooser;
import java.io.File;
import java.nio.file.Paths;

public class FileHandler {

  private String InitialDirectory = Paths.get("./src/main/resources/fa/data").toAbsolutePath().normalize().toString();

  private static final FileChooser.ExtensionFilter allowedExt =
    new FileChooser.ExtensionFilter("All compatible types", "*.jobj", "*.csv");

  private static final FileChooser.ExtensionFilter csvExt =
    new FileChooser.ExtensionFilter("Comma separated values", "*.csv");

  private static final FileChooser.ExtensionFilter jobjExt =
    new FileChooser.ExtensionFilter("Serialized java objects", "*.jobj");

  public DB openFileChooser() {
    System.out.println(InitialDirectory);
    FileChooser fc = new FileChooser();
    fc.setInitialDirectory(new File(InitialDirectory));
    fc.getExtensionFilters().addAll(allowedExt, csvExt, jobjExt);

    File selectedFile = fc.showOpenDialog(null);

    if (selectedFile != null) {
      // TODO: Implement file reading
      String extension = selectedFile.getName();
      extension = extension.substring(extension.indexOf('.') + 1);
      System.out.println(extension);

      return Reader.read(extension, selectedFile);
    }

    return DB.init();
  }

}
