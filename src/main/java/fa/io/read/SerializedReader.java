package fa.io.read;

import fa.io.read.exceptions.ReadSerializedInvalidClassException;
import fa.models.DB;

import java.io.*;

class SerializedReader implements ReadStrategy {
  @Override
  public DB readFile(File file) throws IOException, ReadSerializedInvalidClassException {
    try {
      return this.parseObject(file);
    } catch (ClassNotFoundException | InvalidClassException e) {
      throw new ReadSerializedInvalidClassException(
        "The file could not be deserialized. A likely cause is that" +
        " the file contains outdated versions of some classes."
      );
    }
  }

  private DB parseObject(File file) throws IOException, ClassNotFoundException {
    FileInputStream fileIn = new FileInputStream(file);
    ObjectInputStream ois = new ObjectInputStream(fileIn);

    try {
      return (DB) ois.readObject();
    } finally {
      ois.close();
      fileIn.close();
    }
  }
}
