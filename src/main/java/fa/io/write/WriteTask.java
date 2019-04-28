package fa.io.write;

import fa.io.InvalidExtensionException;
import fa.models.Store;
import fa.utils.DialogHandler;
import javafx.application.Platform;
import javafx.concurrent.Task;

import java.io.File;
import java.io.IOException;

public class WriteTask extends Task<Void> {
  private String extension;
  private File file;

  public WriteTask(String extension, File file) {
    this.extension = extension;
    this.file = file;
    this.mainThreadSetup();
  }

  private void write() throws IOException, InvalidExtensionException {
    switch (this.extension) {
      case "csv":
        new CSVWriter().writeToFile(this.file);
        break;
      case "jobj":
        new SerializedWriter().writeToFile(this.file);
        break;
      default:
        throw new InvalidExtensionException(String.format("Invalid file extension \".%s\"", this.extension));
    }
  }

  private void mainThreadSetup() {
    Store.setLoading("Exporting data...");
  }

  @Override
  protected Void call() throws Exception {
    this.write();
    return null;
  }

  @Override
  protected void succeeded() {
    Platform.runLater(Store::unsetLoading);
  }

  @Override
  protected void failed() {
    Platform.runLater(() -> {
      Store.unsetLoading();
      this.getException().printStackTrace();
      DialogHandler.showErrorDialog(
        "Error while exporting data to file: " + this.file.getName(),
        this.getException().getMessage()
      );
    });
  }
}