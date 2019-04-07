package fa;

import javafx.stage.FileChooser;

import java.io.File;

class FileHandler {

  private static final FileChooser.ExtensionFilter allowedExt =
    new FileChooser.ExtensionFilter("All compatible types", "*.jobj", "*.csv");

  private static final FileChooser.ExtensionFilter csvExt =
    new FileChooser.ExtensionFilter("Comma separated values", "*.csv");

  private static final FileChooser.ExtensionFilter jobjExt =
    new FileChooser.ExtensionFilter("Serialized java objects", "*.jobj");

  private static final FileChooser.ExtensionFilter allExt =
    new FileChooser.ExtensionFilter("All", "*");

  static void openFileChooser() {
    FileChooser fc = new FileChooser();
    fc.getExtensionFilters().addAll(allowedExt, csvExt, jobjExt, allExt);

    File selectedFile = fc.showOpenDialog(null);

    if (selectedFile != null) {
      // TODO: Implement file reading
      System.out.println(selectedFile.getName());
    }
  }

}
