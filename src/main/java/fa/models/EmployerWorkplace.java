package fa.models;

import java.util.List;

import fa.utils.serialization.SerializableProperty;
import javafx.beans.property.ObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class EmployerWorkplace {

  private final SerializableProperty<Employer> employer;
  private final SerializableProperty<List<Workplace>> workplaces;

  public EmployerWorkplace() {
    this(null, null);
  }

  public EmployerWorkplace(Employer employer, List<Workplace> workplaces) {
    this.employer = new SerializableProperty<>(employer);
    this.workplaces = new SerializableProperty<>(workplaces);
  }

  public ObjectProperty<Employer> employerProperty() {
    return this.employer.getProperty();
  }

  public ObjectProperty<List<Workplace>> workplacesProperty() {
    return this.workplaces.getProperty();
  }

  public ObservableList<Workplace> workplacesObservable() {
    return FXCollections.observableList(this.workplacesProperty().getValue());
  }

  @Override
  public String toString() {
    return String.format("%s %s", this.employerProperty().getValue(), this.workplacesProperty().getValue());
  }
}
