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

    editor.setItemsList(DB.getInstance().getJobSeekers());
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

    firstNameField.setValue(jobSeeker.getFirstName().getValue());
    lastNameField.setValue(jobSeeker.getLastName().getValue());
    emailAddressField.setValue(jobSeeker.getEmailAddress().get());
    phoneNumberField.setValue(jobSeeker.getPhoneNumber().getValue());
    birthDateField.setValue(jobSeeker.getBirthDate().getValue());
    educationField.setValue(jobSeeker.getEducation().get());
    workExperienceField.setValue(jobSeeker.getWorkExperience().getValue());
    wageField.setValue(Integer.toString(jobSeeker.getWage().getValue()));
    referencesField.setValue(jobSeeker.getReferences().getValue());
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
    selectedItem.getFirstName().set(firstNameField.getValue());
    selectedItem.getLastName().set(lastNameField.getValue());
    selectedItem.getEmailAddress().set(emailAddressField.getValue());
    selectedItem.getPhoneNumber().set(phoneNumberField.getValue());
    selectedItem.getBirthDate().set(birthDateField.getValue());
    selectedItem.getEducation().set(educationField.getValue());
    selectedItem.getWorkExperience().set(workExperienceField.getValue());
    selectedItem.getWage().set(Integer.parseInt(wageField.getValue()));
    selectedItem.getReferences().set(referencesField.getValue());
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
