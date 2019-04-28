package fa.io.read;

import fa.io.read.exceptions.ReaderException;
import fa.models.DB;

import java.io.File;
import java.io.IOException;

interface ReadStrategy {
  DB readFile(File fileToRead) throws IOException, ReaderException;
}
