package fa;

import fa.components.Editor;
import fa.components.EditorChoiceField;
import fa.models.DB;
import fa.models.Employment;
import fa.models.JobSeeker;
import fa.models.TemporaryPosition;
import javafx.fxml.FXML;

import java.util.stream.Collectors;

public class EmploymentEditorController extends EditorController<Employment> {
  @FXML private Editor<Employment> editor;
  @FXML private EditorChoiceField<JobSeeker> jobSeekerField;
  @FXML private EditorChoiceField<TemporaryPosition> temporaryPositionField;

  @Override
  public void initialize() {
    super.initialize();
    this.editor.onShowEditor(() -> {
      this.jobSeekerField.setOptions(DB.getInstance().getJobSeekers());
      this.temporaryPositionField.setOptions(DB.getInstance().getTemporaryPositions().filtered(
        tp -> !DB.getInstance().getEmployments()
        .stream()
        .map(emp -> emp.temporaryPositionProperty().getValue())
        .collect(Collectors.toList())
        .contains(tp)
      ));
    });
  }

  @Override
  protected void setTableColumns() {
    this.editor.setTableColumn("id", "ID");
    this.editor.setTableColumn("Employee", "jobSeeker");
    this.editor.setTableColumn("Temporary position", "temporaryPosition");
  }

  @Override
  protected void setTableItems() {
    this.editor.setTableItems(DB.getInstance().getEmployments());
  }

  @Override
  protected void setFieldValidators() {}

  @Override
  protected boolean fieldsNotValid() {
    return false;
  }

  @Override
  void createNewItem() {
    System.out.println("Create employment");
    this.selectedItem = new Employment();
    this.updateItem();
    DB.getInstance().getEmployments().add(this.selectedItem);
  }

  @Override
  protected void updateItem() {
    this.selectedItem.jobSeekerProperty().setValue(jobSeekerField.getValue());
    this.selectedItem.temporaryPositionProperty().setValue(temporaryPositionField.getValue());
  }

  @Override
  protected void deleteItem() {
    DB.getInstance().getEmployments().remove(this.selectedItem);
  }
}
