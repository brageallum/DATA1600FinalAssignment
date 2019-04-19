package fa.io;

import fa.models.DB;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

interface WriteStrategy {
  void writeToFile(File file) throws IOException;
}

class CSVWriter implements WriteStrategy {
  @Override
  public void writeToFile(File file) throws IOException  {

  }
}

class SerializedWriter implements WriteStrategy {
  @Override
  public void writeToFile(File file) throws IOException {
    FileOutputStream fos = new FileOutputStream(file.getPath());
    ObjectOutputStream outputStream = new ObjectOutputStream(fos);

    // TODO: implement writing the DB to a serialized file
    outputStream.writeObject(DB.init());

    fos.close();
    outputStream.close();
  }
}

public class Writer {
  public static void write(String ext, File file) throws IOException {
    switch(ext) {
      case "csv":
        new CSVWriter().writeToFile(file);
        break;
      case "jobj":
        new SerializedWriter().writeToFile(file);
        break;
    }
  }
}
