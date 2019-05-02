package fa.io.read;

import fa.exceptions.ReaderException;
import fa.DB;

import java.io.File;
import java.io.IOException;

interface ReadStrategy {
  DB readFile(File fileToRead) throws IOException, ReaderException;
}
