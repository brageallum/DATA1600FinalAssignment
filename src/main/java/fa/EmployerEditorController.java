package fa;

import fa.components.Editor;
import fa.components.EditorDateField;
import fa.components.EditorTextField;
import fa.models.DB;
import fa.models.Employer;
import fa.models.Workplace;
import fa.utils.validation.StringValidator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.util.ArrayList;
import java.util.Arrays;
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
      selectedItem = new Employer();
      clearForm();
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

    this.sectorField.setValidators(requireNonEmpty, requireLettersAndSpaceOnly);
    this.industryField.setValidators(requireNonEmpty, requireLettersAndSpaceOnly);
  }

  private void selectItem(Employer employer) {
    super.selectItem(employer);

    this.editor.setTitle(employer.toString());
    this.selectedItem = employer;

//    this.sectorField.setValue(employer.sectorProperty().get().toString());
//    this.industryField.setValue(employer.industryProperty().getValue());
  }

  protected boolean fieldsNotValid() {
    return super.fieldsNotValid() & !(
      this.sectorField.validate() &
      this.emailAddressField.validate()
    );
  }

  protected void createNewItem() {
    DB.getInstance().getEmployers().add(new Employer(
      this.firstNameField.getValue(),
      this.lastNameField.getValue(),
      null,
      this.addressField.getValue(),
      this.industryField.getValue(),
      this.phoneNumberField.getValue(),
      this.emailAddressField.getValue(),
      this.birthDateField.getValue(),
      new ArrayList<>(Arrays.asList(new Workplace()))
    ));
  }

  protected void updateItem() {
    super.updateItem();
//    this.selectedItem.sectorProperty().set(sectorField.getValue());
    this.selectedItem.industryProperty().set(industryField.getValue());
  }

  protected void clearForm() {
    super.clearForm();
    this.sectorField.clear();
    this.industryField.clear();
  }
}
