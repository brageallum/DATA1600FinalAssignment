package fa;

import java.util.Date;

import fa.components.Editor;
import fa.models.DB;
import fa.models.Employer;
import fa.models.Store;
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
    editor.setTableColumn("Workplaces", "workplacesNames");

    editor.setTableItems(DB.getInstance().getEmployers());

    if (Store.dbInitializedProperty().getValue()) {
      System.out.println(DB.getInstance().getEmployer(100));
    }
  }
}
