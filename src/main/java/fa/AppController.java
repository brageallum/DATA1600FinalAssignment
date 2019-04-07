package fa;

import fa.classes.FileHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.util.Date;

public class AppController {

    @FXML private BorderPane rootContainer;
    @FXML private HBox navigationBar;
    @FXML private ImageView logoImage;

    @FXML
    private void navigateApp(ActionEvent e) {

        String pageUrl = (String) ((Node)e.getSource()).getUserData();

        try {
            for (Node btn : navigationBar.getChildren()) {
                btn.getStyleClass().remove("isActive");
            }
            ((Node) e.getSource()).getStyleClass().add("isActive");

            FXMLLoader loaded = new FXMLLoader(getClass().getResource(pageUrl));
            rootContainer.setCenter(loaded.load());
        } catch(IOException error) {
            error.printStackTrace();
        }
    }

    public void initialize() {
        System.out.format("[ %s ]: AppController initialized.\n", new Date());
        logoImage.setImage(
          new Image(
            String.valueOf(getClass().getResource("images/e.png"))
          )
        );
    }

    @FXML
    public void importData(ActionEvent e) {
        FileHandler.openFileChooser();
    }

    @FXML
    public void exportData(ActionEvent e) {
        FileHandler.openFileChooser();
    }
}
