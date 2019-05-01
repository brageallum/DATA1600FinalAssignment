package fa;

import fa.models.DB;
import fa.models.TemporaryPosition;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;

public class EmploymentController {

  @FXML private ComboBox<TemporaryPosition> workplacesDropDown;
  @FXML private VBox employeeInfo;

  public void initialize() {
    workplacesDropDown.setItems(DB.getInstance().getWorkplaces());

    this.onSelectWorkplace((observableValue, oldValue, newValue) -> {
      try {
        System.out.println(DB.getInstance().getJobSeekers());
      } catch(IndexOutOfBoundsException e) {
        System.out.println("No jobseekers match this workplace");
      }
    });
  }

  public void onSelectWorkplace(ChangeListener<TemporaryPosition> changeListener) {
    workplacesDropDown.getSelectionModel().selectedItemProperty().addListener(changeListener);
  }

}
