package fa;

import java.util.Date;

import fa.components.Editor;
import fa.models.DB;
import fa.models.Workplace;
import javafx.fxml.FXML;

public class WorkplaceEditorController {
  @FXML
  private Editor<Workplace> editor;

  private Workplace selectedItem;

  public void initialize() {
    System.out.format("[ %s ]: WorkplaceEditorController initialized.\n", new Date());

    editor.setTableColumn("id", "ID");
    editor.setTableColumn("Sector", "sector");
    editor.setTableColumn("Workplace", "workplace");
    editor.setTableColumn("Employer", "employer");
    editor.setTableColumn("Category", "category");
    editor.setTableColumn("Duration", "duration");
    editor.setTableColumn("Working Hours", "workingHours");
    editor.setTableColumn("Description", "description");
    editor.setTableColumn("Position", "position");
    editor.setTableColumn("Qualifications", "qualifications");
    editor.setTableColumn("Wage", "wage");
    editor.setTableColumn("Conditions", "conditions");
    editor.setTableColumn("Phone Number", "phoneNumber");
    editor.setTableColumn("Email Address", "emailAddress");

    editor.setTableItems(DB.getInstance().getWorkplaces());

    System.out.println(DB.getInstance().getWorkplaces());
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
