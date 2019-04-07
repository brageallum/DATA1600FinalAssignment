package fa;

import fa.classes.FileHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.util.Date;

public class AppController {

    private static String currentPage = "home";

    @FXML private BorderPane rootContainer;
    @FXML private HBox navigationBar;
    @FXML private ImageView logoImage;
    @FXML private HBox topBar;

    @FXML
    private void navigateApp(ActionEvent e) {

        String pageUrl = (String) ((Node)e.getSource()).getUserData();

        try {

            Node button = (Node) e.getSource();

            if (button.getId().equals(currentPage)) {
                return;
            }
            currentPage = button.getId();

            for (Node btn : navigationBar.getChildren()) {
                btn.getStyleClass().remove("isActive");
            }
            button.getStyleClass().add("isActive");

            FXMLLoader loaded = new FXMLLoader(getClass().getResource(pageUrl));
            rootContainer.setCenter(loaded.load());
        } catch(IOException error) {
            error.printStackTrace();
        }
    }

    public void initialize() {
        System.out.format("[ %s ]: AppController initialized.\n", new Date());
        topBar.setAlignment(Pos.CENTER_LEFT);
        logoImage.setFitHeight(25.0);
        logoImage.setFitWidth(25.0);
        logoImage.setImage(
          new Image(
            String.valueOf(getClass().getResource("images/window.png"))
          )
        );
    }

    @FXML
    public void importData(ActionEvent e) {
        new FileHandler().openFileChooser();
    }

    @FXML
    public void exportData(ActionEvent e) {
        new FileHandler().openFileChooser();
    }
}
