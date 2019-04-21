package fa;

import fa.components.Editor;
import fa.components.EditorTextField;
import fa.models.DB;
import fa.models.JobSeeker;
import fa.utils.EditorFieldValidator;
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
    EditorFieldValidator requireNonEmpty = EditorFieldValidator.requireNonEmpty();
    EditorFieldValidator requireLettersAndSpaceOnly = EditorFieldValidator.requireLettersAndSpaceOnly();
    EditorFieldValidator requireNumbersOnly = EditorFieldValidator.requireNumbersOnly();

    firstNameField.setValidators(requireNonEmpty, requireLettersAndSpaceOnly);
    lastNameField.setValidators(requireNonEmpty, requireLettersAndSpaceOnly);
    emailAddressField.setValidators(requireNonEmpty, EditorFieldValidator.requireValidEmail());
    phoneNumberField.setValidators(requireNonEmpty, requireNumbersOnly, EditorFieldValidator.requireLength(8));
    educationField.setValidators(requireNonEmpty, EditorFieldValidator.requireLettersAndSpaceOnly());
    workExperienceField.setValidators(requireNonEmpty, requireLettersAndSpaceOnly);
    wageField.setValidators(requireNonEmpty, requireNumbersOnly);
    referencesField.setValidators(requireNonEmpty);
  }

  private void selectItem(JobSeeker jobSeeker) {
    title.setText("Editing: " + jobSeeker.toString());
    selectedItem = jobSeeker;

    firstNameField.setFieldText(jobSeeker.getFirstName().getValue());
    lastNameField.setFieldText(jobSeeker.getLastName().getValue());
    emailAddressField.setFieldText(jobSeeker.getEmailAddress().get());
    phoneNumberField.setFieldText(jobSeeker.getPhoneNumber().getValue());
    educationField.setFieldText(jobSeeker.getEducation().get());
    workExperienceField.setFieldText(jobSeeker.getWorkExperience().getValue());
    wageField.setFieldText(Integer.toString(jobSeeker.getWage().getValue()));
    referencesField.setFieldText(jobSeeker.getReferences().getValue());
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
    return !(firstNameField.validate() & lastNameField.validate());
  }

  private void createNewJobSeeker() {
    DB.getInstance().getJobSeekers().add(new JobSeeker(
      firstNameField.getFieldText(),
      lastNameField.getFieldText(),
      emailAddressField.getFieldText(),
      phoneNumberField.getFieldText(),
      new Date(),
      educationField.getFieldText(),
      workExperienceField.getFieldText(),
      Integer.parseInt(wageField.getFieldText()),
      referencesField.getFieldText()
    ));
  }

  private void updateJobSeeker() {
    selectedItem.getFirstName().set(firstNameField.getFieldText());
    selectedItem.getLastName().set(lastNameField.getFieldText());
    selectedItem.getEmailAddress().set(emailAddressField.getFieldText());
    selectedItem.getPhoneNumber().set(phoneNumberField.getFieldText());

    selectedItem.getEducation().set(educationField.getFieldText());
    selectedItem.getWorkExperience().set(workExperienceField.getFieldText());
    selectedItem.getWage().set(Integer.parseInt(wageField.getFieldText()));
    selectedItem.getReferences().set(referencesField.getFieldText());
  }

  private void clearForm() {
    title.setText("");
    selectedItem = null;

    firstNameField.setFieldText("");
    lastNameField.setFieldText("");
    emailAddressField.setFieldText("");
    phoneNumberField.setFieldText("");

    educationField.setFieldText("");
    workExperienceField.setFieldText("");
    wageField.setFieldText("");
    referencesField.setFieldText("");
  }
}
