package fa;

import fa.components.Editor;
import fa.components.EditorChoiceField;
import fa.components.EditorTextField;
import fa.models.DB;
import fa.models.Workplace;
import fa.utils.validation.StringValidator;
import javafx.fxml.FXML;

import java.util.Date;

public class WorkplaceEditorController extends EditorController<Workplace> {
  @FXML private Editor<Workplace> editor;
  @FXML private EditorChoiceField sectorField;
  @FXML private EditorTextField workplaceField,
    employerField,
    categoryField,
    durationField,
    workingHoursField,
    descriptionField,
    positionField,
    qualificationsField,
    wageField,
    conditionsField,
    phoneNumberField,
    emailAddressField;

  @Override
  public void initialize() {
    super.initialize();
    System.out.format("[ %s ]: WorkplaceEditorController initialized.\n", new Date());
    sectorField.setOptions(DB.sectorOptions.values());
  }

  @Override
  protected void setTableColumns() {
    this.editor.setTableColumn("id", "ID");
    this.editor.setTableColumn("Sector", "sector");
    this.editor.setTableColumn("Workplace", "workplace");
    this.editor.setTableColumn("Employer", "employer");
    this.editor.setTableColumn("Category", "category");
    this.editor.setTableColumn("Duration", "duration");
    this.editor.setTableColumn("Working Hours", "workingHours");
    this.editor.setTableColumn("Description", "description");
    this.editor.setTableColumn("Position", "position");
    this.editor.setTableColumn("Qualifications", "qualifications");
    this.editor.setTableColumn("Wage", "wage");
    this.editor.setTableColumn("Conditions", "conditions");
    this.editor.setTableColumn("Phone Number", "phoneNumber");
    this.editor.setTableColumn("Email Address", "emailAddress");
  }

  @Override
  protected void setTableItems() {
    System.out.println("Set table items");
    this.editor.setTableItems(DB.getInstance().getWorkplaces());
  }

  @Override
  protected void setFieldValidators() {
    StringValidator requireNonEmpty = StringValidator.requireNonEmpty();
    StringValidator requireNumbersOnly = StringValidator.requireNumbersOnly();

    this.workplaceField.setValidators(requireNonEmpty);
    this.employerField.setValidators(requireNonEmpty);
    this.categoryField.setValidators(requireNonEmpty);
    this.durationField.setValidators(requireNonEmpty);
    this.workingHoursField.setValidators(requireNonEmpty);
    this.descriptionField.setValidators(requireNonEmpty);
    this.positionField.setValidators(requireNonEmpty);
    this.qualificationsField.setValidators(requireNonEmpty);
    this.wageField.setValidators(requireNonEmpty, requireNumbersOnly);
    this.conditionsField.setValidators(requireNonEmpty);
    this.phoneNumberField.setValidators(requireNonEmpty, requireNumbersOnly, StringValidator.requireLength(8));
    this.emailAddressField.setValidators(requireNonEmpty, StringValidator.requireValidEmail());
  }

  @Override
  protected boolean fieldsNotValid() {
    return !(workplaceField.validate() &
      employerField.validate() &
      categoryField.validate() &
      durationField.validate() &
      workingHoursField.validate() &
      descriptionField.validate() &
      positionField.validate() &
      qualificationsField.validate() &
      wageField.validate() &
      conditionsField.validate() &
      phoneNumberField.validate() &
      emailAddressField.validate());
  }

  @Override
  protected void selectItem(Workplace item) {
    super.selectItem(item);

    this.sectorField.setValue(item.sectorProperty().getValue().toString());
    this.workplaceField.setValue(item.workplaceProperty().getValue());
    this.employerField.setValue(item.employerProperty().getValue());
    this.categoryField.setValue(item.categoryProperty().getValue());
    this.durationField.setValue(item.durationProperty().getValue());
    this.workingHoursField.setValue(item.workingHoursProperty().getValue());
    this.descriptionField.setValue(item.descriptionProperty().getValue());
    this.positionField.setValue(item.positionProperty().getValue());
    this.qualificationsField.setValue(item.qualificationsProperty().getValue());
    this.wageField.setValue(item.wageProperty().getValue().toString());
    this.conditionsField.setValue(item.conditionsProperty().getValue());
    this.phoneNumberField.setValue(item.phoneNumberProperty().getValue());
    this.emailAddressField.setValue(item.emailAddressProperty().getValue());
  }

  @Override
  void createNewItem() {
    this.selectedItem = new Workplace();
    this.updateItem();
    DB.getInstance().getWorkplaces().add(this.selectedItem);
  }

  @Override
  protected void updateItem() {
    this.selectedItem.sectorProperty().set(DB.sectorOptions.valueOf(this.sectorField.getValue()));
    this.selectedItem.workplaceProperty().set(this.workplaceField.getValue());
    this.selectedItem.employerProperty().set(this.employerField.getValue());
    this.selectedItem.categoryProperty().set(this.categoryField.getValue());
    this.selectedItem.durationProperty().set(this.durationField.getValue());
    this.selectedItem.workingHoursProperty().set(this.workingHoursField.getValue());
    this.selectedItem.descriptionProperty().set(this.descriptionField.getValue());
    this.selectedItem.positionProperty().set(this.positionField.getValue());
    this.selectedItem.qualificationsProperty().set(this.qualificationsField.getValue());
    this.selectedItem.wageProperty().set(Integer.parseInt(this.wageField.getValue()));
    this.selectedItem.conditionsProperty().set(this.conditionsField.getValue());
    this.selectedItem.phoneNumberProperty().set(this.phoneNumberField.getValue());
    this.selectedItem.emailAddressProperty().set(this.emailAddressField.getValue());
  }

  @Override
  protected void deleteItem() {
    DB.getInstance().getWorkplaces().remove(this.selectedItem);
  }

  @Override
  protected void clearForm() {
    super.clearForm();
    this.sectorField.setToDefault();
    this.workplaceField.clear();
    this.employerField.clear();
    this.categoryField.clear();
    this.durationField.clear();
    this.workingHoursField.clear();
    this.descriptionField.clear();
    this.positionField.clear();
    this.qualificationsField.clear();
    this.wageField.clear();
    this.conditionsField.clear();
    this.phoneNumberField.clear();
    this.emailAddressField.clear();
  }
}
