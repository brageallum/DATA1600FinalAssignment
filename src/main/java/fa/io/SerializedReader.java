package fa.io;

import fa.models.DB;

import java.io.*;
import java.util.List;
import java.util.Map;

class SerializedReader implements ReadStrategy {
  @Override
  public void readFile(File file) throws IOException, ClassNotFoundException {
    FileInputStream fileIn =  new FileInputStream(file);
    ObjectInputStream in = new ObjectInputStream(fileIn);

    Map<String, List<Map<String, String>>> map = (Map<String, List<Map<String, String>>>) in.readObject();
    DB.init().fromMap(map);

    in.close();
    fileIn.close();
  }
}
