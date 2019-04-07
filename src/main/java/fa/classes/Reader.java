package fa.classes;

import java.io.File;

import fa.models.DB;

interface ReadStrategy {

  // TODO: Change void to data model type.
  DB readFile(File fileToRead);

}

class CSVReader implements ReadStrategy {

  @Override
  public DB readFile(File file) {
    // TODO: Add reader logic.
    return new DB();
  }

}

class SerializedReader implements ReadStrategy {

  @Override
  public DB readFile(File file) {
    // TODO: Add reader logic.
    return new DB();
  }

}

class Reader {

  private static DB returnData;

  static DB read(String ext, File file) {

    switch(ext) {
      case "csv":
        returnData = new CSVReader().readFile(file);
        break;
      case "jobj":
        returnData = new SerializedReader().readFile(file);
        break;
    }

    return returnData;
  }

}
