package fa;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

import fa.models.DB;
import fa.models.Jobseeker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;

/**
 * This will be used as an abstraction for the data specific editors
 * Todo: Implement data specific editors
 */
public class EditorController {
  @FXML private ListView<String> itemsList;
  @FXML private Label displayData;

  public void initialize() {
    System.out.format("[ %s ]: EditorController initialized.\n", new Date());

    ArrayList<String> jobkeeperName = new ArrayList<>();
    DB.init().getJobseekers().forEach((k) -> {
      System.out.println(k.getFirstName());
      jobkeeperName.add(k.getFirstName());
    });

    ObservableList<String> items = FXCollections.observableArrayList(jobkeeperName);

    itemsList.setCellFactory(stringListView -> new DataListCell());
    itemsList.setItems(items);

    itemsList.getSelectionModel().selectedItemProperty().addListener(
      (observableValue, oldValue, newValue) -> displayData.setText(newValue)
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
