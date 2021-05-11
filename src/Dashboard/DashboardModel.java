package Dashboard;

import Dashboard.Components.DataFile;

public class DashboardModel {

    private DataFile testsOverTime = new DataFile();
    private DataFile testsByRegionsOverTime = new DataFile();
    private DataFile deathsOverTime = new DataFile();
    private DataFile newlyAdmittedOverTime = new DataFile();
    private DataFile regionSummary = new DataFile();
    private DataFile casesByAge = new DataFile();
    private DataFile casesBySex = new DataFile();
    private DataFile municipalityTestedOverTime = new DataFile();
    private DataFile municipalityPositiveOverTime = new DataFile();


    public DashboardModel(){
    }

    // ***********************************************************************************************************
    // Getters and setters
    // ***********************************************************************************************************


    public final void setTestsOverTimeData(DataFile testsOverTime) { this.testsOverTime = testsOverTime; }
    public DataFile getTestsOverTimeData() { return testsOverTime; }


    public final void setTestsByRegionsOverTimeData(DataFile testsByRegionsOverTime) { this.testsByRegionsOverTime = testsByRegionsOverTime; }
    public DataFile getTestsByRegionsOverTimeData() { return testsByRegionsOverTime; }


    public final void setDeathsOverTimeData(DataFile deathsOverTime) { this.deathsOverTime = deathsOverTime; }
    public DataFile getDeathsOverTimeData() { return deathsOverTime; }


    public void setNewlyAdmittedOverTimeData(DataFile newlyAdmittedOverTime) { this.newlyAdmittedOverTime = newlyAdmittedOverTime; }
    public DataFile getNewlyAdmittedOverTimeData() { return newlyAdmittedOverTime; }


    public void setRegionSummaryData(DataFile regionSummary) {this.regionSummary = regionSummary; }
    public DataFile getRegionSummary() { return regionSummary; }


    public void setCasesByAgeData(DataFile casesByAge) { this.casesByAge = casesByAge; }
    public DataFile getCasesByAgeData() { return casesByAge; }


    public void setCasesBySexData(DataFile casesBySex) { this.casesBySex = casesBySex; }
    public DataFile getCasesBySexData() { return casesBySex; }

    public void setMunicipalityTestedOverTime(DataFile municipalityTestedOverTime) { this.municipalityTestedOverTime = municipalityTestedOverTime; }
    public DataFile getMunicipalityTestedOverTime() { return municipalityTestedOverTime; }

    public void setMunicipalityPositiveOverTime(DataFile municipalityPositiveOverTime) { this.municipalityPositiveOverTime = municipalityPositiveOverTime; }
    public DataFile getMunicipalityPositiveOverTime() { return municipalityPositiveOverTime; }

    // Checking function to determine if any of the files was not found
    //
    public boolean checkAllFilesLoaded(){
        return testsOverTime != null &&
                testsByRegionsOverTime != null &&
                deathsOverTime != null &&
                newlyAdmittedOverTime != null &&
                regionSummary != null &&
                casesByAge != null &&
                casesBySex != null &&
                municipalityTestedOverTime != null &&
                municipalityPositiveOverTime != null;
    }
}
