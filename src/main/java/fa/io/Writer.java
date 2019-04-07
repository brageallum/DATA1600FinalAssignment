package fa.io;

interface WriteStrategy {
  // TODO: Change void to data model type.
  void writeToFile();
}

class CSVWriter implements WriteStrategy {
  @Override
  public void writeToFile() {

  }
}

class SerializedWriter implements WriteStrategy {
  @Override
  public void writeToFile() {

  }
}

public class Writer {
  // Use either CSVWriter or SerializeWriter based on file type
}
