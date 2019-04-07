package fa.io;

import javafx.stage.FileChooser;
import java.io.File;

public class FileHandler {

  private static final FileChooser.ExtensionFilter allowedExt =
    new FileChooser.ExtensionFilter("All compatible types", "*.jobj", "*.csv");

  private static final FileChooser.ExtensionFilter csvExt =
    new FileChooser.ExtensionFilter("Comma separated values", "*.csv");

  private static final FileChooser.ExtensionFilter jobjExt =
    new FileChooser.ExtensionFilter("Serialized java objects", "*.jobj");

  private static final FileChooser.ExtensionFilter allExt =
    new FileChooser.ExtensionFilter("All", "*");

  public static void openFileChooser() {
    FileChooser fc = new FileChooser();
    fc.getExtensionFilters().addAll(allowedExt, csvExt, jobjExt, allExt);

    File selectedFile = fc.showOpenDialog(null);

    if (selectedFile != null) {
      // TODO: Implement file reading
      String extension = selectedFile.getName();
      extension = extension.substring(extension.indexOf('.') + 1);
      System.out.println(extension);

      System.out.println(Reader.read(extension, selectedFile));
    }
  }

}
