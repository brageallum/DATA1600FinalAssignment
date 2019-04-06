package fa.classes;

interface WriteStrategy {
  // TODO: Change void to data model type.
  void writeToFile();
}

class CSVWriter implements WriteStrategy {
  @Override
  public void writeToFile() {

  }
}

class SerializeWriter implements WriteStrategy {
  @Override
  public void writeToFile() {

  }
}

public class Writer {
  // Use either CSVWriter or SerializeWriter based on file type
}
