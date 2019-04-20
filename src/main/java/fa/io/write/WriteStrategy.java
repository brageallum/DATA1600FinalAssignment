package fa.io.write;

import java.io.File;
import java.io.IOException;

interface WriteStrategy {
  void writeToFile(File file) throws IOException;
}
