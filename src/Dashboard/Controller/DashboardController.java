package Dashboard.Controller;

import Dashboard.Model.DataFile;
import Dashboard.Model.DashboardModel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class DashboardController {

    private DashboardModel model;
    private String folderPath;

    private String line = "";
    private final String splitBy = ",";

    private final String testsOverTimeFilename = "Test_pos_over_time.csv";
    private final String testsByRegionOverTimeFilename = "Test_regioner.csv";
    private final String deathsOverTimeFilename = "Deaths_over_time.csv";
    private final String newlyAdmittedOverTimeFilename = "Newly_admitted_over_time.csv";
    private final String casesByAgeFilename = "Cases_by_age.csv";
    private final String casesBySexFilename = "Cases_by_sex.csv";

    public DashboardController(DashboardModel model){
        this.model = model;

        model.setCasesBySexData(LoadFile(model.getFolderPath(), testsOverTimeFilename));
        model.setTestsByRegionsOverTimeData(LoadFile(model.getFolderPath(), testsByRegionOverTimeFilename));
        model.setDeathsOverTimeData(LoadFile(model.getFolderPath(), deathsOverTimeFilename));
        model.setNewlyAdmittedOverTimeData(LoadFile(model.getFolderPath(), newlyAdmittedOverTimeFilename));
        model.setCasesByAgeData(LoadFile(model.getFolderPath(), casesByAgeFilename));
        model.setCasesBySexData(LoadFile(model.getFolderPath(), casesBySexFilename));
    }



    public DataFile LoadFile(String folderPath, String fileName)
    {
        try
        {
            // Tutorial for reading file found at https://www.javatpoint.com/java-string-to-int, but adapted a lot
            // parsing a CSV file into BufferedReader class constructor
            BufferedReader bufferedReader = new BufferedReader(new FileReader(folderPath + fileName));
            List<String> lineKeys = new ArrayList<>();
            HashMap<String, HashMap<String, Integer>> hashMaps = new HashMap<>();

            String[] data = bufferedReader.readLine().split(splitBy);
            List<String> dataFieldKeys = new ArrayList<>(Arrays.asList(data));

            while ((line = bufferedReader.readLine()) != null)
            {
                data = line.split(splitBy);
                HashMap<String, Integer> dataHashMap = new HashMap<>();

                for (int i = 1; i < data.length; i++){
                    dataHashMap.put(dataFieldKeys.get(i), Integer.parseInt(data[i]));
                }

                hashMaps.put(data[0], dataHashMap);
                lineKeys.add(data[0]);
            }

            // Idea for returning multiple variables found here: https://www.geeksforgeeks.org/returning-multiple-values-in-java/
            return new DataFile(fileName, dataFieldKeys, lineKeys, hashMaps);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
