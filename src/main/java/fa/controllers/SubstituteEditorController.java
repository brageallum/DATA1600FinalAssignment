package fa.controllers;

import fa.DB;
import fa.components.Editor;
import fa.components.EditorTextField;
import fa.models.Substitute;
import fa.utils.validation.StringValidator;
import javafx.fxml.FXML;

public class SubstituteEditorController extends PersonEditorController<Substitute> {
  @FXML private Editor<Substitute> editor;
  @FXML private EditorTextField educationField,
    workExperienceField,
    wageField,
    referencesField;

  @Override
  protected void setTableColumns() {
    super.setTableColumns();
    this.editor.setTableColumn("Education", "education");
    this.editor.setTableColumn("Work experience", "workExperience");
    this.editor.setTableColumn("Wage", "wage");
    this.editor.setTableColumn("References", "references");
  }

  @Override
  protected void setTableItems() {
    this.editor.setTableItems(DB.getInstance().getSubstitutes());
  }

  @Override
  protected void setFieldValidators() {
    super.setFieldValidators();
    StringValidator requireNonEmpty = StringValidator.requireNonEmpty();
    StringValidator requireLettersAndSpaceOnly = StringValidator.requireLettersAndSpaceOnly();

    this.educationField.setValidators(requireNonEmpty, requireLettersAndSpaceOnly);
    this.workExperienceField.setValidators(requireNonEmpty, requireLettersAndSpaceOnly);
    this.wageField.setValidators(requireNonEmpty, StringValidator.requireNumbersOnly());
    this.referencesField.setValidators(requireNonEmpty);
  }

  @Override
  protected void selectItem(Substitute substitute) {
    super.selectItem(substitute);

    this.educationField.setValue(substitute.educationProperty().get());
    this.workExperienceField.setValue(substitute.workExperienceProperty().getValue());
    this.wageField.setValue(Integer.toString(substitute.wageProperty().getValue()));
    this.referencesField.setValue(substitute.referencesProperty().getValue());
  }

  @Override
  protected boolean fieldsNotValid() {
    return super.fieldsNotValid() & !(
      this.educationField.validate() &
      this.workExperienceField.validate() &
      this.wageField.validate() &
      this.referencesField.validate()
    );
  }

  @Override
  void createNewItem() {
    this.selectedItem = new Substitute();
    this.updateItem();
    DB.getInstance().getSubstitutes().add(this.selectedItem);
  }

  @Override
  protected void updateItem() {
    super.updateItem();
    this.selectedItem.educationProperty().set(educationField.getValue());
    this.selectedItem.workExperienceProperty().set(workExperienceField.getValue());
    this.selectedItem.wageProperty().set(Integer.parseInt(wageField.getValue()));
    this.selectedItem.referencesProperty().set(referencesField.getValue());
  }

  @Override
  protected void deleteItem() {
    DB.getInstance().getSubstitutes().remove(this.selectedItem);
  }

  @Override
  protected void clearForm() {
    super.clearForm();
    this.educationField.clear();
    this.workExperienceField.clear();
    this.wageField.clear();
    this.referencesField.clear();
  }
}
