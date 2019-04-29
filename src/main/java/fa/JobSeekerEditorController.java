package fa;

import fa.components.Editor;
import fa.components.EditorDateField;
import fa.components.EditorTextField;
import fa.models.DB;
import fa.models.JobSeeker;
import fa.utils.validation.StringValidator;
import javafx.fxml.FXML;

public class JobSeekerEditorController extends PersonEditorController<JobSeeker> {
  @FXML private Editor<JobSeeker> editor;
  @FXML private EditorTextField firstNameField;
  @FXML private EditorTextField lastNameField;
  @FXML private EditorTextField emailAddressField;
  @FXML private EditorTextField phoneNumberField;
  @FXML private EditorDateField birthDateField;
  @FXML private EditorTextField educationField;
  @FXML private EditorTextField workExperienceField;
  @FXML private EditorTextField wageField;
  @FXML private EditorTextField referencesField;

  @Override
  protected void setTableColumns() {
    super.setTableColumns();
    this.editor.setTableColumn("Education", "education");
    this.editor.setTableColumn("Workplace experience", "workExperience");
    this.editor.setTableColumn("Wage", "wage");
    this.editor.setTableColumn("References", "references");
  }

  @Override
  protected void setTableItems() {
    this.editor.setTableItems(DB.getInstance().getJobSeekers());
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
  protected void selectItem(JobSeeker jobSeeker) {
    super.selectItem(jobSeeker);
    this.editor.setTitle(jobSeeker.toString());
    this.editor.setEditorID(jobSeeker.getID());

    this.selectedItem = jobSeeker;

    this.educationField.setValue(jobSeeker.educationProperty().get());
    this.workExperienceField.setValue(jobSeeker.workExperienceProperty().getValue());
    this.wageField.setValue(Integer.toString(jobSeeker.wageProperty().getValue()));
    this.referencesField.setValue(jobSeeker.referencesProperty().getValue());
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
    this.selectedItem.firstNameProperty().set(this.firstNameField.getValue());
    this.selectedItem.lastNameProperty().set(this.lastNameField.getValue());
    this.selectedItem.emailAddressProperty().set(this.emailAddressField.getValue());
    this.selectedItem.phoneNumberProperty().set(this.phoneNumberField.getValue());
    this.selectedItem.birthDateProperty().set(this.birthDateField.getValue());
    // this.selectedItem.addressProperty().set(this.addressField.getValue());
    this.selectedItem.workExperienceProperty().set(this.workExperienceField.getValue());
    this.selectedItem.referencesProperty().set(this.referencesField.getValue());
    this.selectedItem.wageProperty().set(Integer.parseInt(this.wageField.getValue()));

    DB.getInstance().getJobSeekers().add(this.selectedItem);
  }

  @Override
  protected void updateItem() {
    super.updateItem();
    this.selectedItem.educationProperty().set(educationField.getValue());
    this.selectedItem.workExperienceProperty().set(workExperienceField.getValue());
    this.selectedItem.wageProperty().set(Integer.parseInt(wageField.getValue()));
    this.selectedItem.referencesProperty().set(referencesField.getValue());
    this.selectedItem.addressProperty().set(null);
  }

  @Override
  protected void deleteItem() {
    DB.getInstance().getJobSeekers().remove(this.selectedItem);
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
