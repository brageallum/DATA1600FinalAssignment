package fa.io.write;

import fa.models.DB;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

class SerializedWriter implements WriteStrategy {
  @Override
  public void writeToFile(File file) throws IOException {
    FileOutputStream fos = new FileOutputStream(file.getPath());
    ObjectOutputStream outputStream = new ObjectOutputStream(fos);

    outputStream.writeObject(DB.getInstance());

    fos.close();
    outputStream.close();
  }
}
