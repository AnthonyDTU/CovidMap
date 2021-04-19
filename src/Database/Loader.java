package Database;

import java.sql.Date;
import java.util.*;
import java.io.*;


public class Loader extends DataBase{

    public Loader() { }

    String line = "";
    String splitBy = ",";

    public DataFromFile LoadFile(String fileName)
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
            return new DataFromFile(fileName, dataFieldKeys, lineKeys, hashMaps);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
