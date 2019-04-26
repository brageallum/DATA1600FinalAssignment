package fa;

import fa.components.Editor;
import fa.components.EditorDateField;
import fa.components.EditorTextField;
import fa.models.DB;
import fa.models.JobSeeker;
import fa.utils.validation.LocalDateValidator;
import fa.utils.validation.StringValidator;
import javafx.fxml.FXML;

import java.util.Date;

public class JobSeekerEditorController {
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
      this.selectedItem = new JobSeeker();
      this.clearForm();
    });
  }

  private void setTableColumns() {
    this.editor.setTableColumn("id", "ID");
    this.editor.setTableColumn("First name", "firstName");
    this.editor.setTableColumn("Last name", "lastName");
    this.editor.setTableColumn("Email address", "emailAddress");
    this.editor.setTableColumn("Phone number", "phoneNumber");
    this.editor.setTableColumn("Birth date", "birthDate");
    this.editor.setTableColumn("Education", "education");
    this.editor.setTableColumn("Workplace experience", "workExperience");
    this.editor.setTableColumn("Wage", "wage");
    this.editor.setTableColumn("References", "references");
    this.editor.setTableColumn("Address", "address");
  }

  private void setFieldValidators() {
    StringValidator requireNonEmpty = StringValidator.requireNonEmpty();
    StringValidator requireLettersAndSpaceOnly = StringValidator.requireLettersAndSpaceOnly();
    StringValidator requireNumbersOnly = StringValidator.requireNumbersOnly();

    this.firstNameField.setValidators(requireNonEmpty, requireLettersAndSpaceOnly);
    this.lastNameField.setValidators(requireNonEmpty, requireLettersAndSpaceOnly);
    this.emailAddressField.setValidators(requireNonEmpty, StringValidator.requireValidEmail());
    this.phoneNumberField.setValidators(requireNonEmpty, requireNumbersOnly, StringValidator.requireLength(8));
    this.birthDateField.setValidators(LocalDateValidator.requireNonEmpty(), LocalDateValidator.requireAge(18));
    this.educationField.setValidators(requireNonEmpty, requireLettersAndSpaceOnly);
    this.workExperienceField.setValidators(requireNonEmpty, requireLettersAndSpaceOnly);
    this.wageField.setValidators(requireNonEmpty, requireNumbersOnly);
    this.referencesField.setValidators(requireNonEmpty);
  }

  private void selectItem(JobSeeker jobSeeker) {
    this.editor.setTitle("Editing: " + jobSeeker.toString());
    this.selectedItem = jobSeeker;

    this.firstNameField.setValue(jobSeeker.firstNameProperty().getValue());
    this.lastNameField.setValue(jobSeeker.lastNameProperty().getValue());
    this.emailAddressField.setValue(jobSeeker.emailAddressProperty().get());
    this.phoneNumberField.setValue(jobSeeker.phoneNumberProperty().getValue());
    this.birthDateField.setValue(jobSeeker.birthDateProperty().getValue());
    this.educationField.setValue(jobSeeker.educationProperty().get());
    this.workExperienceField.setValue(jobSeeker.workExperienceProperty().getValue());
    this.wageField.setValue(Integer.toString(jobSeeker.wageProperty().getValue()));
    this.referencesField.setValue(jobSeeker.referencesProperty().getValue());
  }

  @FXML
  public void submit() {
    if (fieldsNotValid()) return;
    if (selectedItem == null) {
      createNewJobSeeker();
    } else {
      updateJobSeeker();
    }
    editor.clearSelection();
  }

  private boolean fieldsNotValid() {
    return !(
      this.firstNameField.validate() &
      this.lastNameField.validate() &
      this.emailAddressField.validate() &
      this.phoneNumberField.validate() &
      this.birthDateField.validate() &
      this.educationField.validate() &
      this.workExperienceField.validate() &
      this.wageField.validate() &
      this.referencesField.validate()
    );
  }

  private void createNewJobSeeker() {
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

  private void updateJobSeeker() {
    this.selectedItem.firstNameProperty().set(firstNameField.getValue());
    this.selectedItem.lastNameProperty().set(lastNameField.getValue());
    this.selectedItem.emailAddressProperty().set(emailAddressField.getValue());
    this.selectedItem.phoneNumberProperty().set(phoneNumberField.getValue());
    this.selectedItem.birthDateProperty().set(birthDateField.getValue());
    this.selectedItem.educationProperty().set(educationField.getValue());
    this.selectedItem.workExperienceProperty().set(workExperienceField.getValue());
    this.selectedItem.wageProperty().set(Integer.parseInt(wageField.getValue()));
    this.selectedItem.referencesProperty().set(referencesField.getValue());
    this.selectedItem.addressProperty().set(null);
  }

  private void clearForm() {
    this.editor.setTitle(null);
    this.selectedItem = null;

    this.firstNameField.clear();
    this.lastNameField.clear();
    this.emailAddressField.clear();
    this.phoneNumberField.clear();
    this.birthDateField.clear();
    this.educationField.clear();
    this.workExperienceField.clear();
    this.wageField.clear();
    this.referencesField.clear();
  }
}
