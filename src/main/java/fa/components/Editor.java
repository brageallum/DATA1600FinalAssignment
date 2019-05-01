package fa.components;

import fa.models.Model;
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

public class Editor<T extends Model> extends SplitPane {
  @FXML private TextField searchBar;
  @FXML private Label editorTitle;
  @FXML private Label editorID;
  @FXML private TableView<T> itemsTable;
  @FXML private Pane editorFormContainer;
  @FXML private BorderPane editor;
  @FXML private Button addNewButton;
  @FXML private ScrollPane scrollBox;

  private ObservableList<T> items;

  private Runnable onShowEditorListener;

  public Editor() {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fa/components/Editor.fxml"));
    fxmlLoader.setRoot(this);
    fxmlLoader.setController(this);

    try {
      fxmlLoader.load();
    } catch (IOException e) {
      e.printStackTrace();
    }

    this.itemsTable.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> this.setEditorVisible(null == newValue));
  }

  @FXML
  public void goBack(ActionEvent e) {
    this.hideEditor();
    this.clearSelection();
  }

  @FXML
  public void search(ActionEvent e) {
    this.itemsTable.setItems(this.items.filtered(item -> item.matchesSearch(this.searchBar.getText())));
  }

  public void setTitle(String title) {
    this.editorTitle.setText(title);
  }

  public void setEditorID(int id) {
    this.editorID.setText("#" + id);
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
    this.editor.setVisible(true);
    this.editor.setManaged(true);
    this.addNewButton.setVisible(false);
    this.addNewButton.setManaged(false);
    this.scrollBox.setFitToHeight(false);
    if (this.onShowEditorListener != null) {
      this.onShowEditorListener.run();
    }
  }

  public void hideEditor() {
    this.editor.setVisible(false);
    this.editor.setManaged(false);
    this.addNewButton.setVisible(true);
    this.addNewButton.setManaged(true);
    this.scrollBox.setFitToHeight(true);
  }

  public ObservableList<Node> getForm() {
    return this.editorFormContainer.getChildren();
  }

  public void setTableColumn(String label, String key) {
    TableColumn<T, String> tableColumn = new TableColumn<>(label);
    tableColumn.setCellValueFactory(new PropertyValueFactory<>(key));
    this.itemsTable.getColumns().add(tableColumn);
  }

  public void setTableItems(ObservableList<T> items) {
    this.items = items;
    this.itemsTable.setItems(items);
  }

  public void onShowEditor(Runnable runnable) {
    this.onShowEditorListener = runnable;
  }

  public void onNewItem(ChangeListener<T> changeListener) {
    this.itemsTable.getSelectionModel().selectedItemProperty().addListener(changeListener);
  }

  public void clearSelection() {
    this.itemsTable.getSelectionModel().clearSelection();
  }
}
