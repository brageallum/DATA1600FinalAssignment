package fa.utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import fa.models.DB;
import fa.models.Employer;
import fa.models.JobSeeker;
import fa.models.Workplace;

public class FetchData {

  public static Workplace toWorkplace(int id, DB db) {
    return db.getWorkplaces()
      .filtered(s -> (s.getID() == id))
      .get(0);
  }

  public static Employer toEmployer(int id, DB db) {
    return db.getEmployers()
      .filtered(s -> (s.getID() == id))
      .get(0);
  }

  public static JobSeeker toJobSeeker(int id, DB db) {
    return db.getJobSeekers()
      .filtered(s -> (s.getID() == id))
      .get(0);
  }

  public static List<Workplace> getWorkplaces(String data, DB db) {
    return Arrays.stream(data.split(","))
      .map(s -> FetchData.toWorkplace(Integer.parseInt(s), db))
      .collect(Collectors.toList());
  }

}
