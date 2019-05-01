package fa.controllers;

import fa.io.FileHandler;
import fa.Store;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.util.Date;

public class AppController {

  private static String currentPage = "home";

  @FXML private BorderPane rootContainer;
  @FXML private HBox topBar;
  @FXML private Label globalSnackbar;

  @FXML
  private void navigateApp(ActionEvent e) {
    Node clickedLink = (Node) e.getSource();

    try {
      if (isNotCurrentPage(clickedLink)) {
        loadPage(clickedLink);
      }
    } catch (IOException error) {
      error.printStackTrace();
    }
  }

  private boolean isNotCurrentPage(Node clickedLink) {
    return !clickedLink.getId().equals(currentPage);
  }

  private void loadPage(Node link) throws IOException {
    String pageUrl = (String) link.getUserData();

    loadView(pageUrl);
    currentPage = link.getId();

    highlightOnly(link);
  }

  private void loadView(String pageUrl) throws IOException {
    FXMLLoader view = new FXMLLoader(getClass().getResource("../" + pageUrl));
    rootContainer.setCenter(view.load());
  }

  private void highlightOnly(Node link) {
    unhighlightAllNavigationLinks();
    highlightNavigationLink(link);
  }

  private void unhighlightAllNavigationLinks() {
    for (Node link : topBar.getChildren()) {
      link.getStyleClass().remove("isActive");
    }
  }

  private void highlightNavigationLink(Node link) {
    link.getStyleClass().add("isActive");
  }

  public void initialize() {
    System.out.format("[ %s ]: AppController initialized.\n", new Date());
    topBar.setAlignment(Pos.CENTER_LEFT);

    Store.loadingProperty().addListener((observableValue, oldValue, newValue) -> globalSnackbar.setVisible(newValue));
    Store.loadingTextProperty().addListener((observableValue, oldValue, newValue) -> globalSnackbar.setText(newValue));
  }

  @FXML
  public void importData(ActionEvent e) {
    new FileHandler().openImportDataDialog();
  }

  @FXML
  public void exportData(ActionEvent e) {
    new FileHandler().openExportDataDialog();
  }
}
