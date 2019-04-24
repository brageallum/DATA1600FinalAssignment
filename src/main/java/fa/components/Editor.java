package fa.components;

import javafx.beans.value.ChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class Editor<T> extends SplitPane {
  @FXML private TableView<T> itemsTable;
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

  public void setTableColumn(String label, String key) {
    TableColumn<T, String> tableColumn = new TableColumn<>(label);
    tableColumn.setCellValueFactory(new PropertyValueFactory<>(key));
    itemsTable.getColumns().add(tableColumn);
  }

  public void setTableItems(ObservableList<T> items) {
    itemsTable.setItems(items);
  }

  public void onNewItem(ChangeListener<T> changeListener) {
    itemsTable.getSelectionModel().selectedItemProperty().addListener(changeListener);
  }

  public void clearSelection() {
    itemsTable.getSelectionModel().clearSelection();
  }
}
