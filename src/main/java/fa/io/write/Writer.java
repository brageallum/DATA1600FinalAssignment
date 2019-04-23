package fa.io.write;

import fa.io.InvalidExtensionException;

import java.io.File;
import java.io.IOException;

public class Writer {
  public static void write(String ext, File file) throws IOException, InvalidExtensionException {
    switch (ext) {
      case "csv":
        new CSVWriter().writeToFile(file);
        break;
      case "jobj":
        new SerializedWriter().writeToFile(file);
        break;
      default:
        throw new InvalidExtensionException(String.format("Invalid file extension \".%s\"", ext));
    }
  }
}
