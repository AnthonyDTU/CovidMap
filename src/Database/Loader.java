package Database;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
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
            String[] keys = bufferedReader.readLine().split(splitBy);

            List<HashMap<String, Integer>> hashMapList = new ArrayList<HashMap<String, Integer>>();

            while ((line = bufferedReader.readLine()) != null){
                String[] data = line.split(splitBy);
                HashMap<String, Integer> testOverTimeHashMap = new HashMap<>();
                for (int i = 0; i < data.length; i++){
                    testOverTimeHashMap.put(keys[i], Integer.parseInt(data[i]));
                }
                hashMapList.add(testOverTimeHashMap);
            }



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

        try
        {
            // Tutorial found at https://www.javatpoint.com/java-string-to-int, but adapted a lot
            // parsing a CSV file into BufferedReader class constructor
            BufferedReader bufferedReader = new BufferedReader(new FileReader(folderPath + testsByRegionOverTimeFilename));
            line = bufferedReader.readLine();

            while ((line = bufferedReader.readLine()) != null)
            {
                String[] data = line.split(splitBy);
                testByRegionsEntries.add(new testsByRegionEntry(data[0],
                                                                Integer.parseInt(data[1]),
                                                                Integer.parseInt(data[2]),
                                                                Integer.parseInt(data[3]),
                                                                Integer.parseInt(data[4]),
                                                                Integer.parseInt(data[5]),
                                                                Integer.parseInt(data[6]),
                                                                Integer.parseInt(data[7]),
                                                                Integer.parseInt(data[8]),
                                                                Integer.parseInt(data[9])));
            }
            return testByRegionsEntries;
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public List<deathsEntry> LoadDeathsOverTime()
    {
        List<deathsEntry> deathsOverTime = new ArrayList<deathsEntry>();

        try
        {
            // Tutorial found at https://www.javatpoint.com/java-string-to-int, but adapted a lot
            // parsing a CSV file into BufferedReader class constructor
            BufferedReader bufferedReader = new BufferedReader(new FileReader(folderPath + deathsOverTimeFilename));
            line = bufferedReader.readLine();

            while ((line = bufferedReader.readLine()) != null)
            {
                String[] data = line.split(splitBy);
                deathsOverTime.add(new deathsEntry(data[0],
                                                   Integer.parseInt(data[1]),
                                                   Integer.parseInt(data[2])));
            }
            return deathsOverTime;
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public List<newlyAdmittedEntry> LoadNewlyAdmittedOverTime()
    {
        List<newlyAdmittedEntry> newlyAdmittedOverTime = new ArrayList<newlyAdmittedEntry>();

        try
        {
            // Tutorial found at https://www.javatpoint.com/java-string-to-int, but adapted a lot
            // parsing a CSV file into BufferedReader class constructor
            BufferedReader bufferedReader = new BufferedReader(new FileReader(folderPath + newlyAdmittedOverTimeFilename));
            line = bufferedReader.readLine();

            while ((line = bufferedReader.readLine()) != null)
            {
                String[] data = line.split(splitBy);
                newlyAdmittedOverTime.add(new newlyAdmittedEntry(data[0],
                                                                 Integer.parseInt(data[1]),
                                                                 Integer.parseInt(data[2]),
                                                                 Integer.parseInt(data[3]),
                                                                 Integer.parseInt(data[4]),
                                                                 Integer.parseInt(data[5]),
                                                                 Integer.parseInt(data[6]),
                                                                 Integer.parseInt(data[7]),
                                                                 Integer.parseInt(data[8])));
            }
            return newlyAdmittedOverTime;
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public List<casesByAgeEntry> LoadCasesByAge()
    {
        List<casesByAgeEntry> casesByAge = new ArrayList<casesByAgeEntry>();

        try
        {
            // Tutorial found at https://www.javatpoint.com/java-string-to-int, but adapted a lot
            // parsing a CSV file into BufferedReader class constructor
            BufferedReader bufferedReader = new BufferedReader(new FileReader(folderPath + casesByAgeFilename));
            line = bufferedReader.readLine();

            while ((line = bufferedReader.readLine()) != null)
            {
                String[] data = line.split(splitBy);
                casesByAge.add(new casesByAgeEntry(data[0],
                                                   Integer.parseInt(data[1]),
                                                   Integer.parseInt(data[2]),
                                                   Integer.parseInt(data[3])));
            }
            return casesByAge;
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public List<casesBySexEntry> LoadCasesBySex()
    {
        List<casesBySexEntry> casesBySex = new ArrayList<casesBySexEntry>();

        try
        {
            // Tutorial found at https://www.javatpoint.com/java-string-to-int, but adapted a lot
            // parsing a CSV file into BufferedReader class constructor
            BufferedReader bufferedReader = new BufferedReader(new FileReader(folderPath + casesBySexFilename));
            line = bufferedReader.readLine();

            while ((line = bufferedReader.readLine()) != null)
            {
                String[] data = line.split(splitBy);

                // parse data even more....

                casesBySex.add(new casesBySexEntry(data[0],
                                                   Integer.parseInt(data[1]),
                                                   Integer.parseInt(data[2]),
                                                   Integer.parseInt(data[3]),
                                                   Integer.parseInt(data[4]),
                                                   Integer.parseInt(data[5])));
            }
            return casesBySex;
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
