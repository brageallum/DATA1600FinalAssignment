package fa.io;

import java.io.File;
import java.io.IOException;

interface ReadStrategy {
  void readFile(File fileToRead) throws IOException;
}
