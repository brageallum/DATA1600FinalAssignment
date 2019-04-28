package fa;

import fa.components.Editor;
import fa.components.EditorDateField;
import fa.components.EditorTextField;
import fa.models.DB;
import fa.models.JobSeeker;
import fa.utils.validation.StringValidator;
import javafx.fxml.FXML;

import java.util.Date;

public class JobSeekerEditorController extends PersonEditorController {
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

  private JobSeeker selectedItem;

  public void initialize() {
    System.out.format("[ %s ]: JobSeekerEditorController initialized.\n", new Date());

    this.setFieldValidators();
    this.setTableColumns();

    this.editor.setTableItems(DB.getInstance().getJobSeekers());
    this.editor.onNewItem((observableValue, oldValue, newValue) -> {
      if (newValue != null) this.selectItem(newValue);
      else this.clearForm();
    });
    editor.onAddNew(e -> {
      System.out.println("Add new!");
      this.selectedItem = new JobSeeker();
      this.clearForm();
    });
  }

  @Override
  protected void setTableColumns() {
    super.setTableColumns();
    this.editor.setTableColumn("Education", "education");
    this.editor.setTableColumn("Workplace experience", "workExperience");
    this.editor.setTableColumn("Wage", "wage");
    this.editor.setTableColumn("References", "references");
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

  private void selectItem(JobSeeker jobSeeker) {
    super.selectItem(jobSeeker);
    this.editor.setTitle(jobSeeker.toString());
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
    DB.getInstance().getJobSeekers().add(new JobSeeker(
      this.firstNameField.getValue(),
      this.lastNameField.getValue(),
      this.emailAddressField.getValue(),
      this.phoneNumberField.getValue(),
      this.birthDateField.getValue(),
      this.educationField.getValue(),
      this.workExperienceField.getValue(),
      Integer.parseInt(wageField.getValue()),
      this.referencesField.getValue(),
      null
    ));
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
  protected void clearForm() {
    super.clearForm();
    this.educationField.clear();
    this.workExperienceField.clear();
    this.wageField.clear();
    this.referencesField.clear();
  }
}
