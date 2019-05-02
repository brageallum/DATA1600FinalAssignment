package fa.controllers;

import fa.DB;
import fa.components.Editor;
import fa.components.EditorChoiceField;
import fa.models.Employment;
import fa.models.Substitute;
import fa.models.TemporaryPosition;
import javafx.fxml.FXML;

public class EmploymentEditorController extends EditorController<Employment> {
  @FXML private Editor<Employment> editor;
  @FXML private EditorChoiceField<Substitute> substituteField;
  @FXML private EditorChoiceField<TemporaryPosition> temporaryPositionField;

  @Override
  public void initialize() {
    super.initialize();
    this.editor.onShowEditor(this::setFieldData);
    this.temporaryPositionField.setOnAction(event -> this.setSubstituteDropdownOptions());
  }

  private void setFieldData() {
    this.temporaryPositionField.setOptions(
      DB.getInstance()
      .getTemporaryPositions()
      .filtered(DB::temporaryPositionIsAvailable));
    this.setSubstituteDropdownOptions();
  }

  private void setSubstituteDropdownOptions() {
    this.substituteField.setOptions(DB.getInstance().getSubstitutes().filtered(
      substitute -> DB.substituteHasAppliedToTemporaryPosition(substitute, this.temporaryPositionField.getValue())
    ));
  }

  @Override
  protected void setTableColumns() {
    this.editor.setTableColumn("id", "ID");
    this.editor.setTableColumn("Substitute", "substitute");
    this.editor.setTableColumn("Temporary position", "temporaryPosition");
  }

  @Override
  protected void setTableItems() {
    this.editor.setTableItems(DB.getInstance().getEmployments());
  }

  @Override
  protected void setFieldValidators() {}

  @Override
  protected boolean fieldsNotValid() {
    return this.substituteField.getValue() == null || this.temporaryPositionField.getValue() == null;
  }

  @Override
  void createNewItem() {
    System.out.println("Create employment");
    this.selectedItem = new Employment();
    this.updateItem();
    DB.getInstance().getEmployments().add(this.selectedItem);
  }

  @Override
  protected void updateItem() {
    this.selectedItem.substituteProperty().setValue(substituteField.getValue());
    this.selectedItem.temporaryPositionProperty().setValue(temporaryPositionField.getValue());
  }

  @Override
  protected void deleteItem() {
    DB.getInstance().getEmployments().remove(this.selectedItem);
  }
}
