package fa.models;

import fa.utils.serialization.SerializableProperty;
import javafx.beans.property.ObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class EmployerWorkplace {

  private final SerializableProperty<Employer> employer;
  private final SerializableProperty<List<TemporaryPosition>> workplaces;

  public EmployerWorkplace() {
    this(null, null);
  }

  public EmployerWorkplace(Employer employer, List<TemporaryPosition> temporaryPositions) {
    this.employer = new SerializableProperty<>(employer);
    this.workplaces = new SerializableProperty<>(temporaryPositions);
  }

  public ObjectProperty<Employer> employerProperty() {
    return this.employer.getProperty();
  }

  public ObjectProperty<List<TemporaryPosition>> workplacesProperty() {
    return this.workplaces.getProperty();
  }

  public ObservableList<TemporaryPosition> workplacesObservable() {
    return FXCollections.observableList(this.workplacesProperty().getValue());
  }

  @Override
  public String toString() {
    return String.format("%s %s", this.employerProperty().getValue(), this.workplacesProperty().getValue());
  }
}
