package fa.components;

import fa.utils.validation.LocalDateValidator;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.time.LocalDate;

public class EditorDateField extends VBox {
  @FXML private Label label;
  @FXML private Label errorMsg;
  @FXML private DatePicker field;

  private LocalDateValidator[] validators = new LocalDateValidator[0];
  private ChangeListener changeListener = (observableValue, oldValue, newValue) -> validate();

  public EditorDateField() {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fa/components/EditorDateField.fxml"));
    fxmlLoader.setRoot(this);
    fxmlLoader.setController(this);

    try {
      fxmlLoader.load();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void initialize() {
    field.valueProperty().addListener(changeListener);
  }

  public boolean validate() {
    for (LocalDateValidator validator : validators) {
      if (!validator.validate(field.getValue())) {
        errorMsg.setText(validator.getErrorText());
        return false;
      }
    }
    errorMsg.setText(null);
    return true;
  }

  public void setValidators(LocalDateValidator... validators) {
    this.validators = validators;
  }

  public void clear() {
    field.valueProperty().removeListener(changeListener);
    field.setValue(null);
    errorMsg.setText(null);
    field.valueProperty().addListener(changeListener);
  }

  public void setValue(LocalDate d) {
    field.setValue(d);
  }

  public LocalDate getValue() {
    return field.getValue();
  }

  public void setLabelText(String s) {
    label.setText(s);
  }

  public String getLabelText() {
    return label.getText();
  }
}
