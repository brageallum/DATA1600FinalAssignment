package fa;

import fa.io.FileHandler;
import fa.utils.DialogHandler;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Optional;

public class MainApp extends Application {

  private Stage stage;
  private Scene scene;

  @Override
  public void start(Stage stage) throws Exception {
    this.stage = stage;
    Store.stage = stage;
    this.loadData();
    this.decorateStage();
    this.loadFXML();
    this.setIcon();
    this.showStage();
    this.handleCloseEvent();
  }

  private void loadData() throws URISyntaxException {
    File DBInit = new File(getClass().getResource("data/db-init.csv").toURI());
    new FileHandler().importData(DBInit);
  }

  private void decorateStage() {
    this.stage.setTitle("Manage Substitutes");
    this.stage.setMinWidth(600);
    this.stage.setMinHeight(400);
  }

  private void loadFXML() throws IOException {
    Parent root = FXMLLoader.load(getClass().getResource("view/App.fxml"));
    this.scene = new Scene(root);
    this.scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
  }

  private void setIcon() {
    this.stage.getIcons().add(new Image(
      getClass().getResource("icon.png").toString()
    ));
  }

  private void showStage() {
    this.stage.setScene(this.scene);
    this.stage.show();
  }

  private void handleCloseEvent() {
    stage.setOnCloseRequest((event) -> {
      Optional<ButtonType> result = DialogHandler.showWarningDialog(
        "Make sure to export your data!",
        "All changes will be lost unless you export your changes through File > Export data " +
        "to file. Click 'OK' to exit the program.",
        "Exit"
      );
      if (result.isPresent() && result.get() == ButtonType.CANCEL){
        event.consume();
      }
    });
  }

  /**
   * The main() method is ignored in correctly deployed JavaFX application.
   * main() serves only as fallback in case the application can not be
   * launched through deployment artifacts, e.g., in IDEs with limited FX
   * support. NetBeans ignores main().
   *
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    launch(args);
  }

}
