package fa.utils;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple wrapper class for JavaFX's ObservableList, which allows for serialization.
 */

public class SerializableObservableList<T> implements Serializable {
  private List<T> list;
  private SerializableCallback<T, Observable[]> extractor;
  private transient ObservableList<T> observableList;

  public SerializableObservableList(SerializableCallback<T, Observable[]> extractor) {
    list = new ArrayList<>();
    this.extractor = extractor;
  }

  public ObservableList<T> getObservableList() {
    if (observableList == null) {
      initializeObservableList();
    }

    return observableList;
  }

  private void initializeObservableList() {
    observableList = FXCollections.observableArrayList(extractor);
    observableList.addAll(list);
    observableList.addListener((ListChangeListener<? super T>) change -> list = toList(observableList));
  }

  private List<T> toList(ObservableList<T> observableList) {
    return new ArrayList<>(observableList);
  }
}
