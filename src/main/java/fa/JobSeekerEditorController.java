package fa;

import fa.components.Editor;
import fa.models.DB;
import fa.models.JobSeeker;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.Date;

public class JobSeekerEditorController {
  @FXML private Label title;
  @FXML private Editor<JobSeeker> editor;
  @FXML private TextField firstNameField;
  @FXML private TextField lastNameField;

  private JobSeeker selectedItem;

  public void initialize() {
    System.out.format("[ %s ]: JobSeekerEditorController initialized.\n", new Date());

    editor.setItemsList(DB.init().getJobSeekers());
    editor.onNewItem((observableValue, oldValue, newValue) -> {
      if (newValue != null) selectItem(newValue);
      else clearForm();
    });
  }

  private void selectItem(JobSeeker jobSeeker) {
    title.setText("Editing: " + jobSeeker.toString());
    selectedItem = jobSeeker;

    firstNameField.setText(jobSeeker.getFirstName().getValue());
    lastNameField.setText(jobSeeker.getLastName().getValue());
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
    DB.init().getJobSeekers().add(new JobSeeker(
      firstNameField.getText(),
      lastNameField.getText(),
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
    selectedItem.getFirstName().set(firstNameField.getText());
    selectedItem.getLastName().set(lastNameField.getText());
  }

  private void clearForm() {
    title.setText("");
    selectedItem = null;

    firstNameField.setText("");
    lastNameField.setText("");
  }
}
