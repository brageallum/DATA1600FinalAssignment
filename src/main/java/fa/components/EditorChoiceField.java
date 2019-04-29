package fa.components;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class EditorChoiceField extends VBox {
  @FXML private Label label;
  @FXML private ChoiceBox<String> field;

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

  public void setOptions(ObservableList<String> options) {
    field.setItems(options);
    this.setToDefault();
  }

  public <E extends Enum<E>> void setOptions(E[] options) {
    List<String> sectorOptions = Arrays.stream(options)
      .map(Enum::toString)
      .collect(Collectors.toList());
    ObservableList<String> observableSectorOptions = FXCollections.observableArrayList(sectorOptions);
    this.setOptions(observableSectorOptions);
  }

  public void setToDefault() {
    field.getSelectionModel().selectFirst();
  }

  public void setValue(String s) {
    field.setValue(s);
  }

  public String getValue() {
    return field.getValue();
  }

  public void setLabelText(String s) {
    label.setText(s);
  }

  public String getLabelText() {
    return label.getText();
  }
}
