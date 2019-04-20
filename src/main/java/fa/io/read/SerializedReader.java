package fa.io.read;

import fa.models.DB;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

class SerializedReader implements ReadStrategy {
  @Override
  public DB readFile(File file) throws IOException, ClassNotFoundException {
    FileInputStream fileIn =  new FileInputStream(file);
    ObjectInputStream ois = new ObjectInputStream(fileIn);

    DB db = (DB) ois.readObject();

    ois.close();
    fileIn.close();

    return db;
  }
}
