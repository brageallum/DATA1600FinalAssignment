package fa.classes;

interface ReadStrategy {

  // TODO: Change void to data model type.
  void readFile();

}

class CSVReader implements ReadStrategy {

  @Override
  public void readFile() {

  }
}

class SerialReader implements ReadStrategy {

  @Override
  public void readFile() {

  }

}

public class Reader {
  // Use either CSV Reader or SerialReader

}
