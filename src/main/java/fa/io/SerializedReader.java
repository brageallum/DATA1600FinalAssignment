package fa.io;

import fa.models.DB;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

class SerializedReader implements ReadStrategy {
  @Override
  public void readFile(File file) throws IOException, ClassNotFoundException {
    FileInputStream fileIn =  new FileInputStream(file);
    ObjectInputStream ois = new ObjectInputStream(fileIn);

    DB.replaceInstance((DB) ois.readObject());

    ois.close();
    fileIn.close();
  }
}
