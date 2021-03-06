package Dashboard.Components;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class DataFile {

    private List<String> dataFieldKeys;
    private List<String> lineKeys;
    // Order of retrieval: Linekey, Datakey
    private HashMap<String, HashMap<String, Integer>> data;

    public DataFile(){
        this.dataFieldKeys = new ArrayList<>();
        this.lineKeys = new ArrayList<>();
        this.data = new HashMap<>();
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

    public DataFile LoadFile(String fileName){

        String line;
        final String splitBy = ";";

        try
        {
            // Tutorial for reading file found at https://www.javatpoint.com/java-string-to-int, but adapted a lot
            // parsing a CSV file into BufferedReader class constructor
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            String[] readData = bufferedReader.readLine().split(splitBy);

            // Read the header line, specifying data fields
            dataFieldKeys.addAll(Arrays.asList(readData).subList(1, readData.length));

            // Read all the data contained in the file, and register the line key for each line
            while ((line = bufferedReader.readLine()) != null)
            {
                // Read the next line
                readData = line.replace(".", "").replace(",",".").split(splitBy);
                HashMap<String, Integer> dataHashMap = new HashMap<>();

                // Read each data field contained in the current line
                for (int i = 1; i < readData.length; i++){

                    if (readData[i].contains("(")){
                        int index = readData[i].indexOf("(");
                        readData[i] = readData[i].substring(0, index);
                    }
                    // Add the line to the data map.
                    dataHashMap.put(dataFieldKeys.get(i - 1), (int)Float.parseFloat(readData[i].strip()));
                }

                data.put(readData[0], dataHashMap);
                lineKeys.add(readData[0]);
            }

            return this;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
