package fa;

import fa.io.FileHandler;
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
        Node clickedLink = (Node) e.getSource();
        String pageUrl = (String) clickedLink.getUserData();

        try {
            if (!clickedLink.getId().equals(currentPage)) {
              currentPage = clickedLink.getId();
              loadView(pageUrl);
              unhighlightAllNavigationLinks();
              highlightNavigationLink(clickedLink);
            }
        } catch(IOException error) {
            error.printStackTrace();
        }
    }

    private void highlightNavigationLink(Node link) {
        link.getStyleClass().add("isActive");
    }

    private void unhighlightAllNavigationLinks() {
        for (Node link : navigationBar.getChildren()) {
            link.getStyleClass().remove("isActive");
        }
    }

    private void loadView(String pageUrl) throws IOException {
        FXMLLoader loaded = new FXMLLoader(getClass().getResource(pageUrl));
        rootContainer.setCenter(loaded.load());
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
