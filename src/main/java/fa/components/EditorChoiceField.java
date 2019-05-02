package fa.components;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
    this.field.setItems(options);
    this.setToDefault();
  }

  public void setOnAction(EventHandler<ActionEvent> eventHandler) {
    this.field.setOnAction(eventHandler);
  }

  public void setToDefault() {
    this.field.getSelectionModel().selectFirst();
  }

  public void setValue(T s) {
    this.field.setValue(s);
  }

  public T getValue() {
    return this.field.getValue();
  }

  public void setLabelText(String s) {
    this.label.setText(s);
  }

  public String getLabelText() {
    return this.label.getText();
  }
}
