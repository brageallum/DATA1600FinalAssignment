package fa;

import fa.io.FileHandler;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.nio.file.Paths;

public class MainApp extends Application {

  @Override
  public void start(Stage stage) throws Exception {

    File DBInit = new File(getClass().getResource("data/db-init.csv").getFile());
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
