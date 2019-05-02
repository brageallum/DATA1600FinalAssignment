package fa.controllers;

import fa.DB;
import fa.components.Editor;
import fa.components.EditorChoiceField;
import fa.models.JobApplication;
import fa.models.Substitute;
import fa.models.TemporaryPosition;
import javafx.fxml.FXML;

public class JobApplicationEditorController extends EditorController<JobApplication> {
  @FXML private Editor<JobApplication> editor;
  @FXML private EditorChoiceField<Substitute> substituteField;
  @FXML private EditorChoiceField<TemporaryPosition> temporaryPositionField;

  @Override
  public void initialize() {
    super.initialize();
    this.editor.onShowEditor(this::setFieldData);
  }

  private void setFieldData() {
    this.substituteField.setOptions(DB.getInstance().getSubstitutes());
    this.temporaryPositionField.setOptions(
      DB.getInstance()
      .getTemporaryPositions()
      .filtered(DB::temporaryPositionIsAvailable));
  }

  @Override
  protected void setTableColumns() {
    this.editor.setTableColumn("id", "ID");
    this.editor.setTableColumn("Substitute", "substitute");
    this.editor.setTableColumn("Temporary position", "temporaryPosition");
  }

  @Override
  protected void setTableItems() {
    this.editor.setTableItems(DB.getInstance().getJobApplications());
  }

  @Override
  protected void setFieldValidators() {}

  @Override
  protected boolean fieldsNotValid() {
    return false;
  }

  @Override
  void createNewItem() {
    this.selectedItem = new JobApplication();
    this.updateItem();
    DB.getInstance().getJobApplications().add(this.selectedItem);
  }

  @Override
  protected void updateItem() {
    this.selectedItem.substituteProperty().setValue(substituteField.getValue());
    this.selectedItem.temporaryPositionProperty().setValue(temporaryPositionField.getValue());
  }

  @Override
  protected void deleteItem() {
    DB.getInstance().getJobApplications().remove(this.selectedItem);
  }
}
