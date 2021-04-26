package Dashboard.Model;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DashboardModel extends DataFile{

    private String folderPath;

    private final String testsOverTimeFilename = "Test_pos_over_time.csv";
    private final String testsByRegionOverTimeFilename = "Test_regioner.csv";
    private final String deathsOverTimeFilename = "Deaths_over_time.csv";
    private final String newlyAdmittedOverTimeFilename = "Newly_admitted_over_time.csv";
    private final String casesByAgeFilename = "Cases_by_age.csv";
    private final String casesBySexFilename = "Cases_by_sex.csv";

    private DataFile testsOverTime = new DataFile();
    private DataFile testsByRegionsOverTime = new DataFile();
    private DataFile deathsOverTime = new DataFile();
    private DataFile newlyAdmittedOverTime = new DataFile();
    private DataFile casesByAge = new DataFile();
    private DataFile casesBySex = new DataFile();

    // After public DataBase(String folderPath) has been called once, this initializes the object, with previously loaded data
    public DashboardModel(){
    }

    // Needs to be called once, to intialize the Data
    public DashboardModel(String folderPath){
        this.folderPath = folderPath;
    }

    public String getFolderPath(){
        return folderPath;
    }

    public final void setFolderPath(String folderPath){
        this.folderPath = folderPath;
    }


    public final void setTestsOverTimeData(DataFile testsOverTime){
        this.testsOverTime = testsOverTime;
    }

    public DataFile getTestsOverTimeData(){
        return testsOverTime;
    }


    public void setTestsByRegionsOverTimeData(DataFile testsByRegionsOverTime){
        this.testsByRegionsOverTime = testsByRegionsOverTime;
    }

    public DataFile getTestsByRegionsOverTimeData(){
        return testsByRegionsOverTime;
    }


    public void setDeathsOverTimeData(DataFile deathsOverTime){
        this.deathsOverTime = deathsOverTime;
    }

    public DataFile getDeathsOverTimeData(){
        return deathsOverTime;
    }


    public void setNewlyAdmittedOverTimeData(DataFile newlyAdmittedOverTime){
        this.newlyAdmittedOverTime = newlyAdmittedOverTime;
    }

    public DataFile GetNewlyAdmittedOverTimeData(){
        return newlyAdmittedOverTime;
    }


    public final void setCasesByAgeData(DataFile casesByAge){
        this.casesByAge = casesByAge;
    }

    public DataFile GetCasesByAgeData(){
        return casesByAge;
    }


    public final void setCasesBySexData(DataFile casesBySex){
        this.casesBySex = casesBySex;
    }

    public DataFile GetCasesBySexData(){
        return casesBySex;
    }
}
