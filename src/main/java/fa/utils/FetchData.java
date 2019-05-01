package fa.utils;

public class FetchData {
/*
  public static TemporaryPosition getWorkplace(int id, DB db) {
    try {
      return db.getWorkplaces()
        .filtered(s -> (s.getID() == id))
        .get(0);
    } catch(IndexOutOfBoundsException e) {
      // TODO: Add custom error
      throw new Error("No workplace found for this field.");
    }
  }

  public static Employer getEmployer(int id, DB db) {
    try {
      return db.getEmployers()
        .filtered(s -> (s.getID() == id))
        .get(0);
    } catch(IndexOutOfBoundsException e) {
      // TODO: Add custom error
      throw new Error("No employer found for this field.");
    }

  }

  public static JobSeeker toJobSeeker(int id, DB db) {
    try {
      return db.getJobSeekers()
        .filtered(s -> (s.getID() == id))
        .get(0);
    } catch(IndexOutOfBoundsException e) {
      // TODO: Add custom error
      throw new Error("No job seeker found for this field.");
    }
  }

  public static EmployerWorkplace getWorkplacesFromEmployer(int id, DB db) throws IndexOutOfBoundsException {
    return db.getEmployerWorkplaces()
      .filtered(s -> (s.employerProperty().getValue().getID() == id))
      .get(0);
  }

  public static List<TemporaryPosition> getWorkplaces(String data, DB db) {
    return Arrays.stream(data.split(","))
      .map(s -> FetchData.getWorkplace(Integer.parseInt(s), db))
      .collect(Collectors.toList());
  }
  */

}
