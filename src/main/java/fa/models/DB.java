package fa.models;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DB {
  private static DB instance;

  private final ObservableList<JobSeeker> jobSeekers = FXCollections.observableArrayList(jobSeeker -> new Observable[] {
    jobSeeker.getFirstName(),
    jobSeeker.getLastName(),
    jobSeeker.getEmailAddress(),
    jobSeeker.getPhoneNumber(),
    jobSeeker.getEducation(),
    jobSeeker.getWorkExperience(),
    jobSeeker.getWage(),
    jobSeeker.getReferences()
  });

  private DB() {}

  public static DB init() {
    if (instance == null) {
      instance = new DB();
    }
    return instance;
  }


  public ObservableList<JobSeeker> getJobSeekers() {
    return jobSeekers;
  }

  public void clearAll() {
    this.jobSeekers.removeAll();
  }

  public void fromMap(Map<String, List<Map<String, String>>> map) {
    getJobSeekers().setAll(map.get("jobSeekers").stream().map(JobSeeker::fromMap).collect(Collectors.toList()));
  }

  public Map<String, List<Map<String, String>>> toMap() {
    Map<String, List<Map<String, String>>> map = new HashMap<>();
    map.put("jobSeekers", jobSeekers.stream().map(JobSeeker::toMap).collect(Collectors.toList()));
    return map;
  }

  @Override
  public String toString() {
    StringBuilder returnData = new StringBuilder();
    for (JobSeeker seeker : this.jobSeekers) {
      returnData.append(seeker.toString());
    }
    return returnData.toString();
  }
}
