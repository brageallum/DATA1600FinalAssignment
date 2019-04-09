package fa.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import fa.models.DB;
import fa.models.Jobseeker;

interface ReadStrategy {

  // TODO: Change void to data model type.
  DB readFile(File fileToRead) throws IOException;

}

class CSVReader implements ReadStrategy {

  @Override
  public DB readFile(File file) throws IOException {
    // TODO: Add reader logic.
    ArrayList<Jobseeker> jobseekers = new ArrayList<>();

    try {
      BufferedReader reader = Files.newBufferedReader(Paths.get(file.toString()));
      String line;

      while((line = reader.readLine()) != null) {
        Jobseeker seeker = parseJobseeker(line);
        jobseekers.add(seeker);
      }
    } catch(IOException e) {
      System.out.println(e.getMessage());
    }

    DB data = DB.init();
    data.setJobseekers(jobseekers);

    return data;
  }

  private Jobseeker parseJobseeker(String line) {
    String[] data = line.split(",");

    String firstName = data[0];
    String lastName = data[1];
    String emailAddress = data[2];
    String phoneNumber = data[3];
    //Date birthDate = data[4]; // Handle Date
    String education = data[5];
    String workExperience = data[6];
    int wage = Integer.parseInt(data[7]);
    String references = data[8];

    return new Jobseeker(
      firstName,
      lastName,
      emailAddress,
      phoneNumber,
      null,
      education,
      workExperience,
      wage,
      references
    );
  }

}

class SerializedReader implements ReadStrategy {

  @Override
  public DB readFile(File file) {
    // TODO: Add reader logic.
    ArrayList<Jobseeker> jobseekers = new ArrayList<>();

    return DB.init();
  }

}

public class Reader {

  private static DB returnData;

  public static DB read(String ext, File file) {

    switch(ext) {
      case "csv":
        try {
          returnData = new CSVReader().readFile(file);
        } catch(IOException e) {
          System.out.println(e.getMessage());
        }
        break;
      case "jobj":
        returnData = new SerializedReader().readFile(file);
        break;
    }

    return returnData;
  }

}
