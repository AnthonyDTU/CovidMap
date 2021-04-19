package Database;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class DataBase extends DataFromFile{

    String folderPath;

    private final String testsOverTimeFilename = "Test_pos_over_time.csv";
    private final String testsByRegionOverTimeFilename = "Test_regioner.csv";
    private final String deathsOverTimeFilename = "Deaths_over_time.csv";
    private final String newlyAdmittedOverTimeFilename = "Newly_admitted_over_time.csv";
    private final String casesByAgeFilename = "Cases_by_age.csv";
    private final String casesBySexFilename = "Cases_by_sex.csv";

    private static DataFromFile testsOverTime = new DataFromFile();
    private static DataFromFile testsByRegionsOverTime = new DataFromFile();
    private static DataFromFile deathsOverTime = new DataFromFile();
    private static DataFromFile newlyAdmittedOverTime = new DataFromFile();
    private static DataFromFile casesByAge = new DataFromFile();
    private static DataFromFile casesBySex = new DataFromFile();

    // After public DataBase(String folderPath) has been called once, this initializes the object, with previously loaded data
    public DataBase(){
    }

    // Needs to be called once, to intialize the Data
    public DataBase(String folderPath){
        this.folderPath = folderPath;

        Loader loader = new Loader();
        testsOverTime = loader.LoadFile(testsOverTimeFilename);
        testsByRegionsOverTime = loader.LoadFile(testsByRegionOverTimeFilename);
        deathsOverTime = loader.LoadFile(deathsOverTimeFilename);
        newlyAdmittedOverTime = loader.LoadFile(newlyAdmittedOverTimeFilename);
        casesByAge = loader.LoadFile(casesByAgeFilename);
        casesBySex = loader.LoadFile(casesBySexFilename);
    }

    public DataFromFile GetTestOverTimeData(){
        return testsOverTime;
    }

    public DataFromFile GetTestsByRegionsOverTimeData(){
        return testsByRegionsOverTime;
    }

    public DataFromFile GetDeathsOverTimeData(){
        return deathsOverTime;
    }

    public DataFromFile GetNewlyAdmittedOverTimeData(){
        return newlyAdmittedOverTime;
    }

    public DataFromFile GetCasesByAgeData(){
        return casesByAge;
    }

    public DataFromFile GetCasesBySexData(){
        return casesBySex;
    }
}
