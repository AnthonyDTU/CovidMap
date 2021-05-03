package Dashboard.Model;


import java.util.ArrayList;
import java.util.List;

public class DashboardModel extends DataFile{

    private String folderPath;

    private final String testsOverTimeFilename = "Test_pos_over_time.csv";
    private final String testsByRegionOverTimeFilename = "Test_regioner.csv";
    private final String deathsOverTimeFilename = "Deaths_over_time.csv";
    private final String newlyAdmittedOverTimeFilename = "Newly_admitted_over_time.csv";
    private final String regionSummaryFilename = "Region_summary.csv";
    private final String casesByAgeFilename = "Cases_by_age.csv";
    private final String casesBySexFilename = "Cases_by_sex.csv";

    private static DataFile testsOverTime = new DataFile();
    private static DataFile testsByRegionsOverTime = new DataFile();
    private static DataFile deathsOverTime = new DataFile();
    private static DataFile newlyAdmittedOverTime = new DataFile();
    private static DataFile regionSummary = new DataFile();
    private static DataFile casesByAge = new DataFile();
    private static DataFile casesBySex = new DataFile();

    private static List<String> loadedFiles = new ArrayList<>();

    // After public DataBase(String folderPath) has been called once, this initializes the object, with previously loaded data
    public DashboardModel(){
    }

    // Needs to be called once, to intialize the Data
    public DashboardModel(String folderPath){
        this.folderPath = folderPath;
    }


    public final void setFolderPath(String folderPath) { this.folderPath = folderPath; }
    public String getFolderPath() { return folderPath; }


    public final void setTestsOverTimeData(DataFile testsOverTime) { this.testsOverTime = testsOverTime; }
    public DataFile getTestsOverTimeData() { return testsOverTime; }


    public final void setTestsByRegionsOverTimeData(DataFile testsByRegionsOverTime) { this.testsByRegionsOverTime = testsByRegionsOverTime; }
    public DataFile getTestsByRegionsOverTimeData() { return testsByRegionsOverTime; }


    public final void setDeathsOverTimeData(DataFile deathsOverTime) { this.deathsOverTime = deathsOverTime; }
    public DataFile getDeathsOverTimeData() { return deathsOverTime; }


    public final void setNewlyAdmittedOverTimeData(DataFile newlyAdmittedOverTime) { this.newlyAdmittedOverTime = newlyAdmittedOverTime; }
    public DataFile getNewlyAdmittedOverTimeData() { return newlyAdmittedOverTime; }


    public final void setRegionSummaryData(DataFile regionSummary) {this.regionSummary = regionSummary; }
    public DataFile getRegionSummary() { return regionSummary; }


    public final void setCasesByAgeData(DataFile casesByAge) { this.casesByAge = casesByAge; }
    public DataFile getCasesByAgeData() { return casesByAge; }


    public final void setCasesBySexData(DataFile casesBySex) { this.casesBySex = casesBySex; }
    public DataFile getCasesBySexData() { return casesBySex; }


    public final void addFileToList(String fileName) { loadedFiles.add(fileName); }
    public final void clearLoadedFiles() { loadedFiles.clear(); }
    public List<String> getLoadedFiles() { return loadedFiles; }

}
