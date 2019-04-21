module fa {
  requires javafx.controls;
  requires javafx.fxml;

  opens fa to javafx.fxml;
  exports fa;
}
