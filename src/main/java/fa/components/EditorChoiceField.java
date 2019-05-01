package fa.components;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.List;

public class EditorChoiceField<T> extends VBox {
  @FXML private Label label;
  @FXML private ChoiceBox<T> field;

  public EditorChoiceField() {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fa/components/EditorChoiceField.fxml"));
    fxmlLoader.setRoot(this);
    fxmlLoader.setController(this);

    try {
      fxmlLoader.load();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void setOptions(T[] options) {
    this.setOptions(FXCollections.observableArrayList(options));
  }

  public void setOptions(List<T> options) {
    this.setOptions(FXCollections.observableArrayList(options));
  }

  public void setOptions(ObservableList<T> options) {
    field.setItems(options);
    this.setToDefault();
  }


  public void setToDefault() {
    field.getSelectionModel().selectFirst();
  }

  public void setValue(T s) {
    field.setValue(s);
  }

  public T getValue() {
    return field.getValue();
  }

  public void setLabelText(String s) {
    label.setText(s);
  }

  public String getLabelText() {
    return label.getText();
  }
}
