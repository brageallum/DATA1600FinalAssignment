package fa;

import fa.components.Editor;
import fa.components.EditorDateField;
import fa.components.EditorTextField;
import fa.models.DB;
import fa.models.Employer;
import fa.utils.validation.LocalDateValidator;
import fa.utils.validation.StringValidator;
import javafx.fxml.FXML;

import java.util.Date;

public class EmployerEditorController {
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

  public void initialize() {
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

  private void setTableColumns() {
    this.editor.setTableColumn("id", "ID");
    this.editor.setTableColumn("First Name", "firstName");
    this.editor.setTableColumn("Last Name", "lastName");
    this.editor.setTableColumn("Birth date", "birthDate");
    this.editor.setTableColumn("Sector", "sector");
    this.editor.setTableColumn("Address", "address");
    this.editor.setTableColumn("Industry", "industry");
    this.editor.setTableColumn("Phone Number", "phoneNumber");
    this.editor.setTableColumn("Email Address", "emailAddress");
  }

  private void setFieldValidators() {
    StringValidator requireNonEmpty = StringValidator.requireNonEmpty();
    StringValidator requireLettersAndSpaceOnly = StringValidator.requireLettersAndSpaceOnly();
    StringValidator requireNumbersOnly = StringValidator.requireNumbersOnly();

    this.firstNameField.setValidators(requireNonEmpty, requireLettersAndSpaceOnly);
    this.lastNameField.setValidators(requireNonEmpty, requireLettersAndSpaceOnly);
    this.addressField.setValidators(requireNonEmpty);
    this.emailAddressField.setValidators(requireNonEmpty, StringValidator.requireValidEmail());
    this.phoneNumberField.setValidators(requireNonEmpty, requireNumbersOnly, StringValidator.requireLength(8));
    this.birthDateField.setValidators(LocalDateValidator.requireNonEmpty(), LocalDateValidator.requireAge(18));
    this.sectorField.setValidators(requireNonEmpty, requireLettersAndSpaceOnly);
    this.industryField.setValidators(requireNonEmpty, requireLettersAndSpaceOnly);
  }

  private void selectItem(Employer employer) {
    this.editor.setTitle("Editing: " + employer.toString());
    this.selectedItem = employer;

    this.firstNameField.setValue(employer.firstNameProperty().getValue());
    this.lastNameField.setValue(employer.lastNameProperty().getValue());
    this.addressField.setValue(employer.addressProperty().getValue());
    this.emailAddressField.setValue(employer.emailAddressProperty().get());
    this.phoneNumberField.setValue(employer.phoneNumberProperty().getValue());
    this.birthDateField.setValue(employer.birthDateProperty().getValue());
//    this.sectorField.setValue(employer.sectorProperty().get().toString());
//    this.industryField.setValue(employer.industryProperty().getValue());
  }

  @FXML
  public void submit() {
    if (fieldsNotValid()) return;
    if (selectedItem == null) {
      createNewEmployer();
    } else {
      updateEmployer();
    }
    editor.clearSelection();
  }

  private boolean fieldsNotValid() {
    return !(
      this.firstNameField.validate() &
      this.lastNameField.validate() &
      this.emailAddressField.validate() &
      this.phoneNumberField.validate() &
      this.birthDateField.validate() &
      this.addressField.validate() &
      this.sectorField.validate() &
      this.emailAddressField.validate()
    );
  }

//  int ID,
//    String firstName,
//    String lastName,
//    Enum sector,
//    String address,
//    String industry,
//    String phoneNumber,
//    String emailAddress,
//    LocalDate birthDate,
//    List<Workplace> workplaces

  private void createNewEmployer() {
    DB.getInstance().getEmployers().add(new Employer(
      this.firstNameField.getValue(),
      this.lastNameField.getValue(),
      null,
      this.addressField.getValue(),
      this.industryField.getValue(),
      this.phoneNumberField.getValue(),
      this.emailAddressField.getValue(),
      this.birthDateField.getValue(),
      null
    ));
  }

  private void updateEmployer() {
    this.selectedItem.firstNameProperty().set(firstNameField.getValue());
    this.selectedItem.lastNameProperty().set(lastNameField.getValue());
    this.selectedItem.emailAddressProperty().set(emailAddressField.getValue());
    this.selectedItem.phoneNumberProperty().set(phoneNumberField.getValue());
    this.selectedItem.birthDateProperty().set(birthDateField.getValue());
//    this.selectedItem.sectorProperty().set(sectorField.getValue());
    this.selectedItem.industryProperty().set(industryField.getValue());
    this.selectedItem.addressProperty().set(null);
  }

  private void clearForm() {
    this.editor.setTitle(null);
    this.selectedItem = null;

    this.firstNameField.clear();
    this.lastNameField.clear();
    this.emailAddressField.clear();
    this.phoneNumberField.clear();
    this.birthDateField.clear();
    this.sectorField.clear();
    this.industryField.clear();
    this.addressField.clear();
  }
}
