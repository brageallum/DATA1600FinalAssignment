package fa;

import fa.components.Editor;
import fa.components.EditorDateField;
import fa.components.EditorTextField;
import fa.models.DB;
import fa.models.Employer;
import fa.utils.FetchData;
import fa.utils.validation.StringValidator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.util.Date;

public class EmployerEditorController extends PersonEditorController {
  @FXML private Editor<Employer> editor;
  @FXML private EditorTextField firstNameField;
  @FXML private EditorTextField lastNameField;
  @FXML private EditorTextField addressField;
  @FXML private EditorTextField emailAddressField;
  @FXML private EditorTextField phoneNumberField;
  @FXML private EditorDateField birthDateField;
  @FXML private EditorTextField sectorField;
  @FXML private EditorTextField industryField;
  @FXML private ListView<fa.models.Workplace> workplacesField;

  private Employer selectedItem;

  @FXML
  public void delete(ActionEvent event) {
    DB.getInstance().getEmployers().remove(selectedItem);
  }

  @Override
  public void initialize() {
    super.initialize();
    System.out.format("[ %s ]: EmployerEditorController initialized.\n", new Date());

    this.setFieldValidators();
    this.setTableColumns();

    this.editor.setTableItems(DB.getInstance().getEmployers());
    editor.onNewItem((observableValue, oldValue, newValue) -> {
      if (newValue != null) selectItem(newValue);
      else clearForm();
    });
    editor.onAddNew(e -> {
      this.selectedItem = new Employer();
      editor.setTitle("Create a new Employer");
      this.clearForm();
    });
  }

  protected void setTableColumns() {
    super.setTableColumns();
    this.editor.setTableColumn("Sector", "sector");
    this.editor.setTableColumn("Industry", "industry");
  }

  protected void setFieldValidators() {
    super.setFieldValidators();
    StringValidator requireNonEmpty = StringValidator.requireNonEmpty();
    StringValidator requireLettersAndSpaceOnly = StringValidator.requireLettersAndSpaceOnly();
    StringValidator requireValidSector = StringValidator.requireValidSector();

    this.sectorField.setValidators(requireNonEmpty, requireLettersAndSpaceOnly, requireValidSector);
    this.industryField.setValidators(requireNonEmpty, requireLettersAndSpaceOnly);
  }

  private void selectItem(Employer employer) {
    super.selectItem(employer);

    this.editor.setTitle(employer.toString());
    this.editor.setEditorID(employer.getID());

    this.selectedItem = employer;

    this.sectorField.setValue(employer.sectorProperty().get().toString());
    this.industryField.setValue(employer.industryProperty().getValue());

    try {
      this.workplacesField.setItems(DB.getInstance().getWorkplacesFromEmployer(
        this.selectedItem.getID()).workplacesObservable()
      );
    } catch(IndexOutOfBoundsException e) {
        this.workplacesField.setItems(null);
    }

  }

  protected boolean fieldsNotValid() {
    return super.fieldsNotValid() & !(
      this.sectorField.validate() &
      this.emailAddressField.validate()
    );
  }

  protected void createNewItem() {
    this.selectedItem.firstNameProperty().set(this.firstNameField.getValue());
    this.selectedItem.lastNameProperty().set(this.lastNameField.getValue());
    this.selectedItem.sectorProperty().set(DB.sectorChoice.valueOf(this.sectorField.getValue()));
    this.selectedItem.addressProperty().set(this.addressField.getValue());
    this.selectedItem.industryProperty().set(this.industryField.getValue());
    this.selectedItem.phoneNumberProperty().set(this.phoneNumberField.getValue());
    this.selectedItem.emailAddressProperty().set(this.emailAddressField.getValue());
    this.selectedItem.birthDateProperty().set(this.birthDateField.getValue());

    DB.getInstance().getEmployers().add(this.selectedItem);
  }

  protected void updateItem() {
    super.updateItem();
    this.selectedItem.sectorProperty().set(DB.sectorChoice.valueOf(sectorField.getValue()));
    this.selectedItem.industryProperty().set(industryField.getValue());
  }

  protected void clearForm() {
    super.clearForm();
    this.sectorField.clear();
    this.industryField.clear();
  }
}
