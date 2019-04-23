package fa.components;

import fa.utils.validation.StringValidator;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class EditorTextField extends VBox {
  @FXML private Label label;
  @FXML private Label errorMsg;
  @FXML private TextField field;

  private StringValidator[] validators = new StringValidator[0];

  public EditorTextField() {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fa/components/EditorTextField.fxml"));
    fxmlLoader.setRoot(this);
    fxmlLoader.setController(this);

    try {
      fxmlLoader.load();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void initialize() {
    field.textProperty().addListener(((observableValue, oldValue, newValue) -> validate()));
  }

  public boolean validate() {
    for (StringValidator validator : validators) {
      if (!validator.validate(field.getText())) {
        errorMsg.setText(validator.getErrorText());
        return false;
      }
    }
    errorMsg.setText("");
    return true;
  }

  public void setValidators(StringValidator... validators) {
    this.validators = validators;
  }

  public void setValue(String s) {
    field.setText(s);
  }

  public String getValue() {
    return field.getText();
  }

  public void setLabelText(String s) {
    label.setText(s);
  }

  public String getLabelText() {
    return label.getText();
  }
}
