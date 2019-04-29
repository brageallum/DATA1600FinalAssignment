package fa;

import fa.components.Editor;
import fa.components.EditorDateField;
import fa.components.EditorTextField;
import fa.models.DB;
import fa.models.Employer;
import fa.models.Workplace;
import fa.utils.validation.StringValidator;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class EmployerEditorController extends PersonEditorController<Employer> {
  @FXML private Editor<Employer> editor;
  @FXML private EditorTextField firstNameField,
    lastNameField,
    addressField,
    emailAddressField,
    phoneNumberField,
    sectorField,
    industryField;
  @FXML private EditorDateField birthDateField;
  @FXML private ListView<fa.models.Workplace> workplacesField;
  @FXML private Label workplacesLabel;

  @Override
  public void initialize() {
    super.initialize();
    workplacesField.setFocusTraversable(false);
    workplacesField.setMouseTransparent(true);
  }

  @Override
  protected void setTableColumns() {
    super.setTableColumns();
    this.editor.setTableColumn("Sector", "sector");
    this.editor.setTableColumn("Industry", "industry");
  }

  @Override
  protected void setTableItems() {
    this.editor.setTableItems(DB.getInstance().getEmployers());
  }

  @Override
  protected void setFieldValidators() {
    super.setFieldValidators();
    StringValidator requireNonEmpty = StringValidator.requireNonEmpty();
    StringValidator requireLettersAndSpaceOnly = StringValidator.requireLettersAndSpaceOnly();
    StringValidator requireValidSector = StringValidator.requireValidSector();

    this.sectorField.setValidators(requireNonEmpty, requireLettersAndSpaceOnly, requireValidSector);
    this.industryField.setValidators(requireNonEmpty, requireLettersAndSpaceOnly);
  }

  @Override
  protected void showCreationForm() {
    super.showCreationForm();
    this.workplacesField.setVisible(false);
    this.workplacesField.setManaged(false);
    this.workplacesLabel.setVisible(false);
    this.workplacesLabel.setManaged(false);
  }

  @Override
  protected void selectItem(Employer employer) {
    super.selectItem(employer);

    this.editor.setTitle(employer.toString());
    this.editor.setEditorID(employer.getID());

    this.selectedItem = employer;

    this.sectorField.setValue(employer.sectorProperty().get().toString());
    this.industryField.setValue(employer.industryProperty().getValue());

    try {
      ObservableList<Workplace> list = DB.getInstance().getWorkplacesFromEmployer(this.selectedItem.getID()).workplacesObservable();
      this.workplacesField.setItems(list);

      int colSize = 24;
      int borderSize = 2;

      if (0 < list.size()) {
        this.workplacesField.setVisible(true);
        this.workplacesField.setManaged(true);
        this.workplacesLabel.setVisible(true);
        this.workplacesLabel.setManaged(true);
        this.workplacesLabel.setText(String.format("WORKPLACES (%s)", list.size()));
        this.workplacesField.setPrefHeight(list.size() * colSize);
      }
    } catch(IndexOutOfBoundsException e) {
        this.workplacesField.setItems(null);
    }

  }

  @Override
  protected boolean fieldsNotValid() {
    return super.fieldsNotValid() & !(
      this.sectorField.validate() &
      this.emailAddressField.validate()
    );
  }

  @Override
  protected void createNewItem() {
    this.selectedItem = new Employer();

    this.selectedItem.firstNameProperty().set(this.firstNameField.getValue());
    this.selectedItem.lastNameProperty().set(this.lastNameField.getValue());
    this.selectedItem.sectorProperty().set(DB.sectorOptions.valueOf(this.sectorField.getValue()));
    this.selectedItem.addressProperty().set(this.addressField.getValue());
    this.selectedItem.industryProperty().set(this.industryField.getValue());
    this.selectedItem.phoneNumberProperty().set(this.phoneNumberField.getValue());
    this.selectedItem.emailAddressProperty().set(this.emailAddressField.getValue());
    this.selectedItem.birthDateProperty().set(this.birthDateField.getValue());

    DB.getInstance().getEmployers().add(this.selectedItem);
  }

  @Override
  protected void updateItem() {
    super.updateItem();
    this.selectedItem.sectorProperty().set(DB.sectorOptions.valueOf(this.sectorField.getValue()));
    this.selectedItem.industryProperty().set(this.industryField.getValue());
  }

  @Override
  protected void deleteItem() {
    DB.getInstance().getEmployers().remove(this.selectedItem);
  }

  @Override
  protected void clearForm() {
    super.clearForm();
    this.sectorField.clear();
    this.industryField.clear();
  }
}
