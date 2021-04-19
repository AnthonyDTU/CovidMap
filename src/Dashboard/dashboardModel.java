package Dashboard;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class dashboardModel extends DataFile{

    String folderPath;

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
    public dashboardModel(){
    }

    // Needs to be called once, to intialize the Data
    public dashboardModel(String folderPath){
        this.folderPath = folderPath;

//        Loader loader = new Loader();
//        testsOverTime = loader.LoadFile(testsOverTimeFilename);
//        testsByRegionsOverTime = loader.LoadFile(testsByRegionOverTimeFilename);
//        deathsOverTime = loader.LoadFile(deathsOverTimeFilename);
//        newlyAdmittedOverTime = loader.LoadFile(newlyAdmittedOverTimeFilename);
//        casesByAge = loader.LoadFile(casesByAgeFilename);
//        casesBySex = loader.LoadFile(casesBySexFilename);
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

class DataFile {

    private String fileName;
    private List<String> dataFieldKeys = new ArrayList<>();
    private List<String> lineKeys = new ArrayList<>();
    private HashMap<String, HashMap<String, Integer>> data = new HashMap<>();

    public DataFile() { }

    public DataFile(String fileName, List<String> dataFieldKeys, List<String> lineKeys, HashMap<String, HashMap<String, Integer>> data){
        this.fileName = fileName;
        this.dataFieldKeys = dataFieldKeys;
        this.lineKeys = lineKeys;
        this.data = data;
    }

    public String getFileName(){
        return fileName;
    }

    public List<String> getDataFieldKeys(){
        return dataFieldKeys;
    }

    public List<String> getLineKeys(){
        return lineKeys;
    }

    public HashMap<String, HashMap<String, Integer>> getData() {
        return data;
    }
}
