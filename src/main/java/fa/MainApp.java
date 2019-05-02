package fa;

import fa.io.FileHandler;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.util.Optional;

public class MainApp extends Application {

  @Override
  public void start(Stage stage) throws Exception {

    File DBInit = new File(getClass().getResource("data/db-init.csv").toURI());
    new FileHandler().importData(DBInit);

    Parent root = FXMLLoader.load(getClass().getResource("view/App.fxml"));

    Scene scene = new Scene(root);
    scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());

    stage.setTitle("Final Assignment");
    stage.setMinWidth(600);
    stage.setMinHeight(400);

    stage.getIcons().add(new Image(
      getClass().getResource("code.png").toString()
    ));

    stage.setScene(scene);
    stage.show();

    stage.setOnCloseRequest((event) -> {
      Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      alert.setTitle("Warning!");
      alert.setHeaderText("Make sure to export your data!");
      alert.setContentText("All changes will be lost unless you export your changes through File > Export data to file. Click 'OK' to exit the program.");
      alert.initOwner(stage);

      Optional<ButtonType> result = alert.showAndWait();
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
