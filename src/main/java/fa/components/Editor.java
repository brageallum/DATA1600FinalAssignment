package fa.components;

import javafx.beans.value.ChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.ArrayList;

public class Editor<T> extends SplitPane {
  @FXML private Label editorTitle;
  @FXML private TableView<T> itemsTable;
  @FXML private Pane editorFormContainer;
  @FXML private BorderPane editor;
  @FXML private Button addNewButton;
  @FXML private ScrollPane scrollBox;

  private final ArrayList<EventHandler<ActionEvent>> onAddNewActions = new ArrayList<>();

  public Editor() {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fa/components/Editor.fxml"));
    fxmlLoader.setRoot(this);
    fxmlLoader.setController(this);

    try {
      fxmlLoader.load();
    } catch (IOException e) {
      e.printStackTrace();
    }

    itemsTable.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
      if (null == newValue) {
        this.hideEditor();
      } else {
        this.showEditor();
      }
    });

    this.onAddNew(e -> {
      this.clearSelection();
      this.showEditor();
    });
  }

  @FXML
  public void goBack(ActionEvent e) {
    this.hideEditor();
    this.clearSelection();
  }

  public void setTitle(String title) {
    editorTitle.setText(title);
  }

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
    itemsTable.setItems(items);
  }

  public void onNewItem(ChangeListener<T> changeListener) {
    itemsTable.getSelectionModel().selectedItemProperty().addListener(changeListener);
  }

  @FXML
  public void addNewButtonActions(ActionEvent e) {
    for (EventHandler<ActionEvent> eh : onAddNewActions) {
      eh.handle(e);
    }
  }

  public void onAddNew(EventHandler<ActionEvent> e) {
    this.onAddNewActions.add(e);
  }

  public void clearSelection() {
    itemsTable.getSelectionModel().clearSelection();
  }
}
