package fa;

import fa.components.Editor;
import fa.components.EditorDateField;
import fa.components.EditorTextField;
import fa.models.DB;
import fa.models.JobSeeker;
import fa.utils.validation.LocalDateValidator;
import fa.utils.validation.StringValidator;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.Date;

public class JobSeekerEditorController {
  @FXML private Label title;
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

    setFieldValidators();

    editor.setTableColumn("First name", "firstName");
    editor.setTableColumn("Last name", "lastName");
    editor.setTableColumn("Email address", "emailAddress");
    editor.setTableColumn("Phone number", "phoneNumber");
    editor.setTableColumn("Birth date", "birthDate");
    editor.setTableColumn("Education", "education");
    editor.setTableColumn("Work experience", "workExperience");
    editor.setTableColumn("Wage", "wage");
    editor.setTableColumn("References", "references");

    editor.setTableItems(DB.getInstance().getJobSeekers());
    editor.onNewItem((observableValue, oldValue, newValue) -> {
      if (newValue != null) selectItem(newValue);
      else clearForm();
    });
  }

  private void setFieldValidators() {
    StringValidator requireNonEmpty = StringValidator.requireNonEmpty();
    StringValidator requireLettersAndSpaceOnly = StringValidator.requireLettersAndSpaceOnly();
    StringValidator requireNumbersOnly = StringValidator.requireNumbersOnly();

    firstNameField.setValidators(requireNonEmpty, requireLettersAndSpaceOnly);
    lastNameField.setValidators(requireNonEmpty, requireLettersAndSpaceOnly);
    emailAddressField.setValidators(requireNonEmpty, StringValidator.requireValidEmail());
    phoneNumberField.setValidators(requireNonEmpty, requireNumbersOnly, StringValidator.requireLength(8));
    birthDateField.setValidators(LocalDateValidator.requireNonEmpty(), LocalDateValidator.requireAge(18));
    educationField.setValidators(requireNonEmpty, requireLettersAndSpaceOnly);
    workExperienceField.setValidators(requireNonEmpty, requireLettersAndSpaceOnly);
    wageField.setValidators(requireNonEmpty, requireNumbersOnly);
    referencesField.setValidators(requireNonEmpty);
  }

  private void selectItem(JobSeeker jobSeeker) {
    title.setText("Editing: " + jobSeeker.toString());
    selectedItem = jobSeeker;

    firstNameField.setValue(jobSeeker.firstNameProperty().getValue());
    lastNameField.setValue(jobSeeker.lastNameProperty().getValue());
    emailAddressField.setValue(jobSeeker.emailAddressProperty().get());
    phoneNumberField.setValue(jobSeeker.phoneNumberProperty().getValue());
    birthDateField.setValue(jobSeeker.birthDateProperty().getValue());
    educationField.setValue(jobSeeker.educationProperty().get());
    workExperienceField.setValue(jobSeeker.workExperienceProperty().getValue());
    wageField.setValue(Integer.toString(jobSeeker.wageProperty().getValue()));
    referencesField.setValue(jobSeeker.referencesProperty().getValue());
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
      firstNameField.validate() &
      lastNameField.validate() &
      emailAddressField.validate() &
      phoneNumberField.validate() &
      birthDateField.validate() &
      educationField.validate() &
      workExperienceField.validate() &
      wageField.validate() &
      referencesField.validate()
    );
  }

  private void createNewJobSeeker() {
    DB.getInstance().getJobSeekers().add(new JobSeeker(
      firstNameField.getValue(),
      lastNameField.getValue(),
      emailAddressField.getValue(),
      phoneNumberField.getValue(),
      birthDateField.getValue(),
      educationField.getValue(),
      workExperienceField.getValue(),
      Integer.parseInt(wageField.getValue()),
      referencesField.getValue()
    ));
  }

  private void updateJobSeeker() {
    selectedItem.firstNameProperty().set(firstNameField.getValue());
    selectedItem.lastNameProperty().set(lastNameField.getValue());
    selectedItem.emailAddressProperty().set(emailAddressField.getValue());
    selectedItem.phoneNumberProperty().set(phoneNumberField.getValue());
    selectedItem.birthDateProperty().set(birthDateField.getValue());
    selectedItem.educationProperty().set(educationField.getValue());
    selectedItem.workExperienceProperty().set(workExperienceField.getValue());
    selectedItem.wageProperty().set(Integer.parseInt(wageField.getValue()));
    selectedItem.referencesProperty().set(referencesField.getValue());
  }

  private void clearForm() {
    title.setText("");
    selectedItem = null;

    firstNameField.setValue("");
    lastNameField.setValue("");
    emailAddressField.setValue("");
    phoneNumberField.setValue("");
    birthDateField.setValue(null);
    educationField.setValue("");
    workExperienceField.setValue("");
    wageField.setValue("");
    referencesField.setValue("");
  }
}
