package fa.controllers;

import fa.DB;
import fa.components.Editor;
import fa.components.EditorChoiceField;
import fa.components.EditorTextField;
import fa.models.Employer;
import fa.models.TemporaryPosition;
import fa.utils.validation.StringValidator;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class EmployerEditorController extends PersonEditorController<Employer> {
  @FXML private Editor<Employer> editor;
  @FXML private EditorTextField emailAddressField, industryField;
  @FXML private EditorChoiceField<DB.sectorOptions> sectorField;
  @FXML private ListView<TemporaryPosition> temporaryPositionsField;
  @FXML private Label temporaryPositionsLabel;

  @Override
  public void initialize() {
    super.initialize();
    temporaryPositionsField.setFocusTraversable(false);
    temporaryPositionsField.setMouseTransparent(true);
    sectorField.setOptions(DB.sectorOptions.values());
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
    this.industryField.setValidators(StringValidator.requireNonEmpty(), StringValidator.requireLettersAndSpaceOnly());
  }

  @Override
  protected void showCreationForm() {
    super.showCreationForm();
    this.temporaryPositionsField.setVisible(false);
    this.temporaryPositionsField.setManaged(false);
    this.temporaryPositionsLabel.setVisible(false);
    this.temporaryPositionsLabel.setManaged(false);
  }

  @Override
  protected void selectItem(Employer employer) {
    super.selectItem(employer);

    this.sectorField.setValue(DB.sectorOptions.valueOf(employer.sectorProperty().get().toString()));
    this.industryField.setValue(employer.industryProperty().getValue());

    this.createTemporaryPositionsList();
  }

  private void createTemporaryPositionsList() {
    try {
      ObservableList<TemporaryPosition> list = DB.getInstance().getTemporaryPositionFromEmployer(this.selectedItem);
      this.temporaryPositionsField.setItems(list);

      int singleItemHeight = 24;

      if (0 < list.size()) {
        this.showTemporaryPositionsList();
        this.temporaryPositionsLabel.setText(String.format("Responsible for temporary positions (%s)", list.size()));
        this.temporaryPositionsField.setPrefHeight(list.size() * singleItemHeight);
      }
    } catch(IndexOutOfBoundsException e) {
        this.temporaryPositionsField.setItems(null);
    }
  }

  private void showTemporaryPositionsList() {
    this.temporaryPositionsField.setVisible(true);
    this.temporaryPositionsField.setManaged(true);
    this.temporaryPositionsLabel.setVisible(true);
    this.temporaryPositionsLabel.setManaged(true);
  }

  @Override
  protected boolean fieldsNotValid() {
    return super.fieldsNotValid() & !this.emailAddressField.validate();
  }

  @Override
  protected void createNewItem() {
    this.selectedItem = new Employer();
    this.updateItem();
    DB.getInstance().getEmployers().add(this.selectedItem);
  }

  @Override
  protected void updateItem() {
    super.updateItem();
    this.selectedItem.sectorProperty().set(this.sectorField.getValue());
    this.selectedItem.industryProperty().set(this.industryField.getValue());
  }

  @Override
  protected void deleteItem() {
    DB.getInstance().getEmployers().remove(this.selectedItem);
  }

  @Override
  protected void clearForm() {
    super.clearForm();
    this.sectorField.setToDefault();
    this.industryField.clear();
  }
}
