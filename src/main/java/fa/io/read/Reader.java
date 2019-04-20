package fa.io.read;

import fa.models.DB;

import java.io.File;
import java.io.IOException;

public class Reader {
  public static DB read(String ext, File file) throws IOException, ClassNotFoundException {
    switch(ext) {
      case "csv":
        return new CSVReader().readFile(file);
      case "jobj":
        return new SerializedReader().readFile(file);
    }
    return null;
  }
}
