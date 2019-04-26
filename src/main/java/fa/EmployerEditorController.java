package fa;

import java.util.Date;

import fa.components.Editor;
import fa.models.DB;
import fa.models.Employer;
import javafx.fxml.FXML;

public class EmployerEditorController {
  @FXML
  private Editor<Employer> editor;

  private Employer selectedItem;

  public void initialize() {
    System.out.format("[ %s ]: EmployerEditorController initialized.\n", new Date());

    editor.setTableColumn("id", "ID");
    editor.setTableColumn("First Name", "firstName");
    editor.setTableColumn("Last Name", "lastName");
    editor.setTableColumn("Birthdate", "birthDate");
    editor.setTableColumn("Sector", "sector");
    editor.setTableColumn("Address", "address");
    editor.setTableColumn("Industry", "industry");
    editor.setTableColumn("Phone Number", "phoneNumber");
    editor.setTableColumn("Email Address", "emailAddress");

    editor.setTableItems(DB.getInstance().getEmployers());

    System.out.println(DB.getInstance().getEmployers());
    /*
    editor.onNewItem((observableValue, oldValue, newValue) -> {
      if (newValue != null) selectItem(newValue);
      else clearForm();
    });
    editor.onAddNew(e -> {
      editor.clearSelection();
      selectedItem = new Workplace();
      clearForm();
      editor.showEditor();
    });
    */
  }
}
