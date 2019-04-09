package fa.components;

import javafx.beans.value.ChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class Editor<T> extends SplitPane {
  @FXML private ListView<T> itemsList;
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

  public void setItemsList(ObservableList<T> items) {
    itemsList.setItems(items);
  }

  public void onNewItem(ChangeListener<T> changeListener) {
    itemsList.getSelectionModel().selectedItemProperty().addListener(changeListener);
  }

  public void clearSelection() {
    itemsList.getSelectionModel().clearSelection();
  }
}
