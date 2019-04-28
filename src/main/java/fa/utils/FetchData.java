package fa.utils;

import fa.models.DB;
import fa.models.Employer;
import fa.models.JobSeeker;
import fa.models.Workplace;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FetchData {

  public static Workplace toWorkplace(int id, DB db) {
    return db.getWorkplaces()
      .stream()
      .filter(s -> (s.getID() == id))
      .collect(Collectors.toList())
      .get(0);
  }

  public static Employer toEmployer(int id, DB db) {
    return db.getEmployers()
      .stream()
      .filter(s -> (s.getID() == id))
      .collect(Collectors.toList())
      .get(0);
  }

  public static JobSeeker toJobSeeker(int id, DB db) {
    return db.getJobSeekers()
      .stream()
      .filter(s -> (s.getID() == id))
      .collect(Collectors.toList())
      .get(0);
  }

  public static List<Workplace> getWorkplaces(String data, DB db) {
    return Arrays.stream(data.split(","))
      .map(s -> FetchData.toWorkplace(Integer.parseInt(s), db))
      .collect(Collectors.toList());
  }

}
