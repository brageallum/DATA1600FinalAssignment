package fa.models;

import java.util.ArrayList;

public class DB {

  private ArrayList<Jobseeker> jobseekers;

  private static DB instance;

  public static DB init() {
    if (instance == null) {
      instance = new DB();
    }
    return instance;
  }

  private DB() {}

  public void setJobseekers(ArrayList<Jobseeker> jobseekers) {
    this.jobseekers = jobseekers;
  }

  public ArrayList<Jobseeker> getJobseekers() {
    return this.jobseekers;
  }

  @Override
  public String toString() {
    StringBuilder returnData = new StringBuilder();
    for (Jobseeker seeker : this.jobseekers) {
      returnData.append(seeker.toString());
    }
    return returnData.toString();
  }

}
