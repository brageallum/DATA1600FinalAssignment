package fa.controllers;

import fa.components.Editor;
import fa.components.EditorDateField;
import fa.components.EditorTextField;
import fa.models.Person;
import fa.utils.validation.LocalDateValidator;
import fa.utils.validation.StringValidator;
import javafx.fxml.FXML;

public abstract class PersonEditorController<T extends Person> extends EditorController<T> {
  @FXML private Editor<Person> editor;
  @FXML private EditorTextField firstNameField,
    lastNameField,
    addressField,
    emailAddressField,
    phoneNumberField;
  @FXML private EditorDateField birthDateField;

  protected void setTableColumns() {
    this.editor.setTableColumn("id", "ID");
    this.editor.setTableColumn("First name", "firstName");
    this.editor.setTableColumn("Last name", "lastName");
    this.editor.setTableColumn("Address", "address");
    this.editor.setTableColumn("Email address", "emailAddress");
    this.editor.setTableColumn("Phone number", "phoneNumber");
    this.editor.setTableColumn("Birth date", "birthDate");
  }

  protected void setFieldValidators() {
    StringValidator requireNonEmpty = StringValidator.requireNonEmpty();
    StringValidator requireLettersAndSpaceOnly = StringValidator.requireLettersAndSpaceOnly();
    StringValidator requireNumbersOnly = StringValidator.requireNumbersOnly();

    this.firstNameField.setValidators(requireNonEmpty, requireLettersAndSpaceOnly);
    this.lastNameField.setValidators(requireNonEmpty, requireLettersAndSpaceOnly);
    this.addressField.setValidators(requireNonEmpty);
    this.emailAddressField.setValidators(requireNonEmpty, StringValidator.requireValidEmail());
    this.phoneNumberField.setValidators(requireNonEmpty, requireNumbersOnly, StringValidator.requireLength(8));
    this.birthDateField.setValidators(LocalDateValidator.requireNonEmpty(), LocalDateValidator.requireAge(18));
  }

  protected void selectItem(T person) {
    super.selectItem(person);

    this.firstNameField.setValue(person.firstNameProperty().getValue());
    this.lastNameField.setValue(person.lastNameProperty().getValue());
    this.addressField.setValue(person.addressProperty().getValue());
    this.emailAddressField.setValue(person.emailAddressProperty().get());
    this.phoneNumberField.setValue(person.phoneNumberProperty().getValue());
    this.birthDateField.setValue(person.birthDateProperty().getValue());
  }

  protected boolean fieldsNotValid() {
    return !(
      this.firstNameField.validate() &
      this.lastNameField.validate() &
      this.addressField.validate() &
      this.emailAddressField.validate() &
      this.phoneNumberField.validate() &
      this.birthDateField.validate()
    );
  }

  protected void updateItem() {
    this.selectedItem.firstNameProperty().set(firstNameField.getValue());
    this.selectedItem.lastNameProperty().set(lastNameField.getValue());
    this.selectedItem.addressProperty().set(addressField.getValue());
    this.selectedItem.emailAddressProperty().set(emailAddressField.getValue());
    this.selectedItem.phoneNumberProperty().set(phoneNumberField.getValue());
    this.selectedItem.birthDateProperty().set(birthDateField.getValue());
  }

  protected void clearForm() {
    super.clearForm();

    this.firstNameField.clear();
    this.lastNameField.clear();
    this.addressField.clear();
    this.emailAddressField.clear();
    this.phoneNumberField.clear();
    this.birthDateField.clear();
  }
}
