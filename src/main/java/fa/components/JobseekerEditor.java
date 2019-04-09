package fa.components;

import fa.models.DB;
import fa.models.Jobseeker;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.Date;

public class JobseekerEditor {
  @FXML private Label title;
  @FXML private Editor<Jobseeker> editor;
  @FXML private TextField firstnameField;

  public void initialize() {
    System.out.format("[ %s ]: JobseekerEditor initialized.\n", new Date());

    editor.setItemsList(DB.init().getJobseekers());
    editor.onNewItem((observableValue, oldValue, newValue) -> title.setText("Editing: " + newValue.toString()));
  }

  @FXML
  public void firstnameHandler() {
    System.out.println("You typed: " + firstnameField.getText());
    DB.init().getJobseekers().get(0).getFirstName().set(firstnameField.getText());
  }
}
