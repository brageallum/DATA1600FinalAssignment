package fa.components;

import fa.utils.Searchable;
import javafx.beans.value.ChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class Editor<T extends Searchable> extends SplitPane {
  @FXML private TextField searchBar;
  @FXML private Label editorTitle;
  @FXML private Label editorID;
  @FXML private TableView<T> itemsTable;
  @FXML private Pane editorFormContainer;
  @FXML private BorderPane editor;
  @FXML private Button addNewButton;
  @FXML private ScrollPane scrollBox;

  private ObservableList<T> items;

  public Editor() {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fa/components/Editor.fxml"));
    fxmlLoader.setRoot(this);
    fxmlLoader.setController(this);

    try {
      fxmlLoader.load();
    } catch (IOException e) {
      e.printStackTrace();
    }

    itemsTable.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> this.setEditorVisible(null == newValue));
  }

  @FXML
  public void goBack(ActionEvent e) {
    this.hideEditor();
    this.clearSelection();
  }

  @FXML
  public void search(ActionEvent e) {
    itemsTable.setItems(items.filtered(item -> item.matchesSearch(searchBar.getText())));
  }

  public void setTitle(String title) {
    editorTitle.setText(title);
  }

  public void setEditorID(int id) {
    editorID.setText(String.format("#%s", id));
  }

  public void setEditorVisible(boolean visible) {
    if (visible) {
      this.hideEditor();
    } else {
      this.showEditor();
    }
  }

  @FXML
  public void showEditor() {
    editor.setVisible(true);
    editor.setManaged(true);
    addNewButton.setVisible(false);
    addNewButton.setManaged(false);
    scrollBox.setFitToHeight(false);
  }

  public void hideEditor() {
    editor.setVisible(false);
    editor.setManaged(false);
    addNewButton.setVisible(true);
    addNewButton.setManaged(true);
    scrollBox.setFitToHeight(true);
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
    this.items = items;
    itemsTable.setItems(items);
  }

  public void onNewItem(ChangeListener<T> changeListener) {
    itemsTable.getSelectionModel().selectedItemProperty().addListener(changeListener);
  }

  public void clearSelection() {
    itemsTable.getSelectionModel().clearSelection();
  }
}
