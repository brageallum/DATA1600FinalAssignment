package fa;

import fa.components.Editor;
import fa.utils.Searchable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public abstract class EditorController<T extends Searchable> {
  @FXML private Editor<T> editor;
  @FXML private Button submitButton;
  @FXML private Button deleteButton;

  protected T selectedItem;

  @FXML
  public void submit() {
    if (this.fieldsNotValid()) return;
    if (null == selectedItem) {
      this.createNewItem();
    } else {
      this.updateItem();
    }
    this.editor.clearSelection();
  }

  @FXML
  public void delete(ActionEvent event) {
    this.deleteItem();
    editor.clearSelection();
  }

  public void initialize() {
    this.setFieldValidators();
    this.setTableColumns();
    this.setTableItems();
    this.editor.onNewItem((observableValue, oldValue, newValue) -> {
      if (null == newValue) {
        this.showCreationForm();
      } else {
        this.showEditForm(newValue);
      }
    });
  }

  protected void showCreationForm() {
    this.submitButton.setText("Create");
    this.deleteButton.setVisible(false);
    this.editor.setTitle("Add new");
    this.editor.setEditorID(0);
    this.clearForm();
  }

  protected void showEditForm(T item) {
    this.selectItem(item);
    this.submitButton.setText("Update");
    this.deleteButton.setVisible(true);
  }

  protected abstract void setTableColumns();

  protected abstract void setTableItems();

  protected abstract void setFieldValidators();

  protected void selectItem(T item) {
    this.editor.setTitle(item.toString());
    this.selectedItem = item;
  }

  protected abstract boolean fieldsNotValid();

  abstract void createNewItem();

  protected abstract void updateItem();

  protected abstract void deleteItem();

  protected void clearForm() {
    this.selectedItem = null;
  }
}
