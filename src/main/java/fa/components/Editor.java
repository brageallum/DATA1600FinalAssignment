package fa.components;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import fa.models.DB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class Editor extends SplitPane {
  @FXML private ListView<String> itemsList;
  @FXML private Pane editorFormContainer;

  public Editor() {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fa/components/Editor.fxml"));
    fxmlLoader.setRoot(this);
    fxmlLoader.setController(this);

    try {
      fxmlLoader.load();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public ObservableList<Node> getForm() {
    return editorFormContainer.getChildren();
  }

  public void initialize() {
    System.out.format("[ %s ]: Editor initialized.\n", new Date());

    ArrayList<String> jobkeeperName = new ArrayList<>();
    DB.init().getJobseekers().forEach((k) -> {
      System.out.println(k.getFirstName());
      jobkeeperName.add(k.getFirstName());
    });

    ObservableList<String> items = FXCollections.observableArrayList(jobkeeperName);

    itemsList.setCellFactory(stringListView -> new DataListCell());
    itemsList.setItems(items);

    // TODO: This needs to be changed to pass the new value to the editor form
    itemsList.getSelectionModel().selectedItemProperty().addListener(
      (observableValue, oldValue, newValue) -> ((Label)editorFormContainer.getChildren().get(0)).setText(newValue)
    );
  }
}

class DataListCell extends ListCell<String> {
  @Override
  public void updateItem(String item, boolean empty) {
    super.updateItem(item, empty);

    if (item != null) {
      HBox row = new HBox();
      row.getStyleClass().add("data-list");
      row.getChildren().addAll(new Label(item));

      setGraphic(row);
    }
  }
}
