package fa.components;

import fa.models.DB;
import fa.models.JobSeeker;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.Date;

public class JobSeekerEditor {
  @FXML private Label title;
  @FXML private Editor<JobSeeker> editor;
  @FXML private TextField firstNameField;

  public void initialize() {
    System.out.format("[ %s ]: JobSeekerEditor initialized.\n", new Date());

    editor.setItemsList(DB.init().getJobSeekers());
    editor.onNewItem((observableValue, oldValue, newValue) -> title.setText("Editing: " + newValue.toString()));
  }

  @FXML
  public void firstnameHandler() {
    System.out.println("You typed: " + firstNameField.getText());
    DB.init().getJobSeekers().get(0).getFirstName().set(firstNameField.getText());
  }
}
