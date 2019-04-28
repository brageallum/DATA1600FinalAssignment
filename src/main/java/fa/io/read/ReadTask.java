package fa.io.read;

import fa.io.InvalidExtensionException;
import fa.io.read.exceptions.ReaderException;
import fa.models.DB;
import fa.models.Store;
import fa.utils.DialogHandler;
import javafx.concurrent.Task;

import java.io.File;
import java.io.IOException;

public class ReadTask extends Task<DB> {
  private String extension;
  private File file;

  public ReadTask(String ext, File file) {
    this.extension = ext;
    this.file = file;
    this.mainThreadSetup();
  }

  private DB read() throws IOException, ReaderException, InvalidExtensionException {
    switch (this.extension) {
      case "csv":
        return new CSVReader().readFile(this.file);
      case "jobj":
        return new SerializedReader().readFile(this.file);
      default:
        throw new InvalidExtensionException(String.format("Invalid file extension \".%s\"", this.extension));
    }
  }

  private void mainThreadSetup() {
    Store.setLoading("Importing data...");
  }

  @Override
  protected DB call() throws Exception {
    return this.read();
  }

  @Override
  protected void succeeded() {
    DB.replaceInstance(this.getValue());
    Store.unsetLoading();
  }

  @Override
  protected void failed() {
    Store.unsetLoading();
    this.getException().printStackTrace();
    DialogHandler.showErrorDialog(
      "Error while importing data from file: " + this.file.getName(),
      this.getException().getMessage()
    );
  }
}
