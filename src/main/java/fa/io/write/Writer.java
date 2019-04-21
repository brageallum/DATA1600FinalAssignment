package fa.io.write;

import java.io.File;
import java.io.IOException;

public class Writer {
  public static void write(String ext, File file) throws IOException {
    switch (ext) {
      case "csv":
        new CSVWriter().writeToFile(file);
        break;
      case "jobj":
        new SerializedWriter().writeToFile(file);
        break;
    }
  }
}
