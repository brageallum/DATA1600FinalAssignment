package fa;

import fa.components.Editor;
import fa.models.DB;
import fa.models.Employer;
import javafx.fxml.FXML;

import java.util.Date;

public class EmployerEditorController {
  @FXML
  private Editor<Employer> editor;

  private Employer selectedItem;

  public void initialize() {
    System.out.format("[ %s ]: EmployerEditorController initialized.\n", new Date());

    this.setTableColumns();

    this.editor.setTableItems(DB.getInstance().getEmployers());

    System.out.println(DB.getInstance().getEmployers());
    /*
    editor.onNewItem((observableValue, oldValue, newValue) -> {
      if (newValue != null) selectItem(newValue);
      else clearForm();
    });
    editor.onAddNew(e -> {
      selectedItem = new Workplace();
      clearForm();
    });
    */
  }

  private void setTableColumns() {
    this.editor.setTableColumn("id", "ID");
    this.editor.setTableColumn("First Name", "firstName");
    this.editor.setTableColumn("Last Name", "lastName");
    this.editor.setTableColumn("Birthdate", "birthDate");
    this.editor.setTableColumn("Sector", "sector");
    this.editor.setTableColumn("Address", "address");
    this.editor.setTableColumn("Industry", "industry");
    this.editor.setTableColumn("Phone Number", "phoneNumber");
    this.editor.setTableColumn("Email Address", "emailAddress");
  }
}
