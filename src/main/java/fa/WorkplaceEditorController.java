package fa;

import fa.components.Editor;
import fa.models.DB;
import fa.models.Workplace;
import javafx.fxml.FXML;

import java.util.Date;

public class WorkplaceEditorController {
  @FXML
  private Editor<Workplace> editor;

  private Workplace selectedItem;

  public void initialize() {
    System.out.format("[ %s ]: WorkplaceEditorController initialized.\n", new Date());

    this.setTableColumns();

    this.editor.setTableItems(DB.getInstance().getWorkplaces());

    System.out.println(DB.getInstance().getWorkplaces());
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
    this.editor.setTableColumn("Sector", "sector");
    this.editor.setTableColumn("Workplace", "workplace");
    this.editor.setTableColumn("Employer", "employer");
    this.editor.setTableColumn("Category", "category");
    this.editor.setTableColumn("Duration", "duration");
    this.editor.setTableColumn("Working Hours", "workingHours");
    this.editor.setTableColumn("Description", "description");
    this.editor.setTableColumn("Position", "position");
    this.editor.setTableColumn("Qualifications", "qualifications");
    this.editor.setTableColumn("Wage", "wage");
    this.editor.setTableColumn("Conditions", "conditions");
    this.editor.setTableColumn("Phone Number", "phoneNumber");
    this.editor.setTableColumn("Email Address", "emailAddress");
  }
}
