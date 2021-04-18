package Database;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.util.Scanner;

public class Loader extends DataBase{

    public Loader(String folderPath){

    }

    String line = "";
    String splitBy = ",";

    final String testsOverTimeFilename = "Test_pos_over_time.csv";
    final String testsByRegionOverTimeFilename = "Test_regioner.csv";
    final String deathsOverTimeFilename = "Deaths_over_time.csv";
    final String newlyAdmittedOverTimeFilename = "Newly_admitted_over_time.csv";
    final String casesByAgeFilename = "Cases_by_age.csv";
    final String casesBySexFilename = "Cases_by_sex.csv";

    public List<testsEntry> LoadTestsOverTime()
    {
        List<testsEntry> testsEntries = new ArrayList<testsEntry>();

        try
        {
            // Tutorial found at https://www.javatpoint.com/java-string-to-int, but adapted a lot
            // parsing a CSV file into BufferedReader class constructor
            BufferedReader bufferedReader = new BufferedReader(new FileReader(folderPath + testsOverTimeFilename));
            line = bufferedReader.readLine();

            while ((line = bufferedReader.readLine()) != null)
            {
                String[] data = line.split(splitBy);
                testsEntries.add(new testsEntry(data[0],
                                                Integer.parseInt(data[1]),
                                                Integer.parseInt(data[2]),
                                                Integer.parseInt(data[3]),
                                                Integer.parseInt(data[4]),
                                                Integer.parseInt(data[5]),
                                                Integer.parseInt(data[6])));
            }
            return testsEntries;
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public List<testsByRegionEntry> LoadTestByRegionOverTime()
    {
        List<testsByRegionEntry> testByRegionsEntries = new ArrayList<testsByRegionEntry>();
        return testByRegionsEntries;
    }

    public List<deathsEntry> LoadDeathsOverTime()
    {
        List<deathsEntry> deathsOverTime = new ArrayList<deathsEntry>();
        return deathsOverTime;
    }

    public List<newlyAdmittedEntry> LoadNewlyAdmittedOverTime(){
        List<newlyAdmittedEntry> newlyAdmittedOverTime = new ArrayList<newlyAdmittedEntry>();
        return newlyAdmittedOverTime;
    }

    public List<casesByAgeEntry> LoadCasesByAge(){
        List<casesByAgeEntry> casesByAge = new ArrayList<casesByAgeEntry>();
        return casesByAge;
    }

    public List<casesBySexEntry> LoadCasesBySex(){
        List<casesBySexEntry> CasesBySex = new ArrayList<casesBySexEntry>();
        return CasesBySex;
    }
}
