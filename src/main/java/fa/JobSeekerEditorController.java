package fa;

import fa.components.Editor;
import fa.components.EditorTextField;
import fa.models.DB;
import fa.models.JobSeeker;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.Date;

public class JobSeekerEditorController {
  @FXML private Label title;
  @FXML private Editor<JobSeeker> editor;
  @FXML private EditorTextField firstNameField;
  @FXML private EditorTextField lastNameField;

  private JobSeeker selectedItem;

  public void initialize() {
    System.out.format("[ %s ]: JobSeekerEditorController initialized.\n", new Date());

    editor.setItemsList(DB.getInstance().getJobSeekers());
    editor.onNewItem((observableValue, oldValue, newValue) -> {
      if (newValue != null) selectItem(newValue);
      else clearForm();
    });
  }

  private void selectItem(JobSeeker jobSeeker) {
    title.setText("Editing: " + jobSeeker.toString());
    selectedItem = jobSeeker;

    firstNameField.setFieldText(jobSeeker.getFirstName().getValue());
    lastNameField.setFieldText(jobSeeker.getLastName().getValue());
  }

  @FXML
  public void submit() {
    if (selectedItem == null) {
      createNewJobSeeker();
    } else {
      updateJobSeeker();
    }
    editor.clearSelection();
  }

  private void createNewJobSeeker() {
    DB.getInstance().getJobSeekers().add(new JobSeeker(
      firstNameField.getFieldText(),
      lastNameField.getFieldText(),
      "",
      "",
      new Date(),
      "",
      "",
      2,
      ""
    ));
  }

  private void updateJobSeeker() {
    selectedItem.getFirstName().set(firstNameField.getFieldText());
    selectedItem.getLastName().set(lastNameField.getFieldText());
  }

  private void clearForm() {
    title.setText("");
    selectedItem = null;

    firstNameField.setFieldText("");
    lastNameField.setFieldText("");
  }
}
