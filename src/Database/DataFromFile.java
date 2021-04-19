package Database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataFromFile {

    private String fileName;
    private List<String> dataFieldKeys = new ArrayList<>();
    private List<String> lineKeys = new ArrayList<>();
    private HashMap<String, HashMap<String, Integer>> data = new HashMap<>();

    public DataFromFile() { };

    public DataFromFile(String fileName, List<String> dataFieldKeys, List<String> lineKeys, HashMap<String, HashMap<String, Integer>> data){
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
