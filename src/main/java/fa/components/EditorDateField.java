package fa.components;

import fa.utils.LocalDateValidator;
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
    field.valueProperty().addListener(((observableValue, oldValue, newValue) -> validate()));
  }

  public boolean validate() {
    for (LocalDateValidator validator : validators) {
      if (!validator.validate(field.getValue())) {
        errorMsg.setText(validator.getErrorText());
        return false;
      }
    }
    errorMsg.setText("");
    return true;
  }

  public void setValidators(LocalDateValidator... validators) {
    this.validators = validators;
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
