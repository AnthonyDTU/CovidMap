package Database;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DataBase {

    String folderPath;

    public DataBase(){
    }

    public DataBase(String folderPath){
        this.folderPath = folderPath;
        
        Loader loader = new Loader(folderPath);
        testsOverTime = loader.LoadTestsOverTime();
        testsByRegionsOverTime = loader.LoadTestByRegionOverTime();
        deathsOverTime = loader.LoadDeathsOverTime();
        newlyAdmittedOverTime = loader.LoadNewlyAdmittedOverTime();
        casesByAge = loader.LoadCasesByAge();
        casesBySex = loader.LoadCasesBySex();
    }

    // *********************************************************************************
    public record testsEntry(
            String date,
            int newPositive,
            int notPreviousPositive,
            int positivePercent,
            int previousPercent,
            int tested,
            int testedCumulative
    ){}
    private List<testsEntry> testsOverTime = new ArrayList<testsEntry>();
    // *********************************************************************************

    // *********************************************************************************
    // If week = total, the values in the other fields is totals of every entry,
    public record testsByRegionEntry(
            String week,
            int region_Hovedstaden,
            int region_Midtjylland,
            int region_Nordjylland,
            int region_Sjaelland,
            int region_SydDanmark,
            int StatensSerumInstitut,
            int TestCenterDanmark,
            int Total,
            int TotalCumulative
    ){}
    private List<testsByRegionEntry> testsByRegionsOverTime = new ArrayList<testsByRegionEntry>();
    // *********************************************************************************

    // *********************************************************************************
    public record deathsEntry(
            String date,
            int deaths,
            int deathsCumulative // Calculated by program, not loaded from file
    ){}
    private List<deathsEntry> deathsOverTime = new ArrayList<deathsEntry>();
    // *********************************************************************************

    // *********************************************************************************
    public record newlyAdmittedEntry(
            String date,
            int region_Hovedstaden,
            int region_Midtjylland,
            int region_Nordjylland,
            int region_Sjaelland,
            int region_SydDanmark,
            int region_unknown,
            int totalCurrentlyAdmitted,
            int totalCumulativeAdmitted // Calculated by program, not loaded from file
    ){}
    private List<newlyAdmittedEntry> newlyAdmittedOverTime = new ArrayList<newlyAdmittedEntry>();
    // *********************************************************************************

    // *********************************************************************************
    public record casesByAgeEntry(
            String ageGroup,
            int confirmed,
            int tested,
            int positivePercentage
    ){}
    private List<casesByAgeEntry> casesByAge = new ArrayList<casesByAgeEntry>();
    // *********************************************************************************

    // *********************************************************************************
    public record casesBySexEntry(
            String ageGroup,
            int womenConfimed,
            int womenPercent,
            int MenConfirmed,
            int MenPercent,
            int total
    ){}
    private List<casesBySexEntry> casesBySex = new ArrayList<casesBySexEntry>();
    // *********************************************************************************


    public testsEntry getTestEntry(int index){
        return testsOverTime.get(index);
    }
    
    public testsByRegionEntry getTestsByRegionEntry(int index){
        return testsByRegionsOverTime.get(index);
    }

    public deathsEntry getDeathEntry(int index){
        return deathsOverTime.get(index);
    }

    public newlyAdmittedEntry getNewlyAdmittedEntry(int index){
        return newlyAdmittedOverTime.get(index);
    }

    public casesByAgeEntry getCasesBeAgeEntry(int index){
        return casesByAge.get(index);
    }

    public casesBySexEntry getCasesBySexEntry(int index){
        return casesBySex.get(index);
    }
}


//class Demo{
//
//    public static void main(String[] args) {
//        // Lav (Instanciate) en ny cirkel
//        Cirkel minCirkel = new Cirkel(10);
//        float circumference = minCirkel.GetCurcumference();
//    }
//}
//
//// Når denne bliver Instancieret skabes et objekt
//class Cirkel{
//
//    // Class member
//    private float radius;
//
//    // Constructor (hvad skal der til for at lave en cirkel
//    public Cirkel(float radius){
//        this.radius = radius;
//    }
//
//    // Returnere radius
//    public float GetRadius(){
//        return radius;
//    }
//
//    // Returnere en udregnet omkreds
//    public float GetCurcumference(){
//        return radius * radius * (float)3.14;
//    }
//}
