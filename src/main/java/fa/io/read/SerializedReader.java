package fa.io.read;

import fa.exceptions.SerializedReaderInvalidClassException;
import fa.DB;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;

class SerializedReader implements ReadStrategy {
  @Override
  public DB readFile(File file) throws IOException, SerializedReaderInvalidClassException {
    try {
      return this.parseObject(file);
    } catch (ClassNotFoundException | InvalidClassException e) {
      throw new SerializedReaderInvalidClassException(
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
