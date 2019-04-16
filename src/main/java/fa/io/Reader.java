package fa.io;

import fa.models.DB;

import java.io.File;
import java.io.IOException;

public class Reader {
  public static void read(String ext, File file) throws IOException, ClassNotFoundException {
    DB.init().clearAll();
    switch(ext) {
      case "csv":
          new CSVReader().readFile(file);
        break;
      case "jobj":
        new SerializedReader().readFile(file);
        break;
    }
  }

}
