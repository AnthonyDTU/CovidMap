package Dashboard.Controller;

import Dashboard.Model.DataFile;
import Dashboard.Model.DashboardModel;
import Dashboard.Controller.ComponentIntializers.MapViewInitializer;
import Dashboard.View.DashboardView;
import javafx.scene.control.Button;


import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class DashboardController {

    private DashboardModel model;
    private DashboardView view;
    private String folderPath;

    private String line = "";
    private final String splitBy = ";";

    private final String testsOverTimeFilename = "Test_pos_over_time.csv";
    private final String testsByRegionOverTimeFilename = "Test_regioner.csv";
    private final String deathsOverTimeFilename = "Deaths_over_time.csv";
    private final String newlyAdmittedOverTimeFilename = "Newly_admitted_over_time.csv";
    private final String regionSummaryFilename = "Region_summary.csv";
    private final String casesByAgeFilename = "Cases_by_age.csv";
    private final String casesBySexFilename = "Cases_by_sex.csv";

    public DashboardController(DashboardModel model, DashboardView view){
        this.model = model;
        this.view = view;

        this.model.setTestsOverTimeData(LoadFile(model.getFolderPath(), testsOverTimeFilename));
        this.model.setTestsByRegionsOverTimeData(LoadFile(model.getFolderPath(), testsByRegionOverTimeFilename));
        this.model.setDeathsOverTimeData(LoadFile(model.getFolderPath(), deathsOverTimeFilename));
        this.model.setNewlyAdmittedOverTimeData(LoadFile(model.getFolderPath(), newlyAdmittedOverTimeFilename));
        this.model.setRegionSummaryData(LoadFile(model.getFolderPath(), regionSummaryFilename));
        this.model.setCasesByAgeData(LoadFile(model.getFolderPath(), casesByAgeFilename));
        //model.setCasesBySexData(LoadFile(model.getFolderPath(), casesBySexFilename));

        MapViewInitializer mapViewInitializer = new MapViewInitializer();
        this.view.setMapView(mapViewInitializer.CreateMapView());
        this.view.getMapView().addEventHandlerToRegionButtons(new RegionButtonEventHandler());
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
            dataFieldKeys.remove(0);

            while ((line = bufferedReader.readLine()) != null)
            {
                data = line.replace(".", "").replace(",",".").split(splitBy);
                HashMap<String, Integer> dataHashMap = new HashMap<>();

                for (int i = 1; i < data.length; i++){
                    dataHashMap.put(dataFieldKeys.get(i - 1), (int)Float.parseFloat(data[i].strip()));
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


    class RegionButtonEventHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent actionEvent) {

            Button sender = (Button)actionEvent.getSource();
            DashboardModel data = new DashboardModel();
            DataFile regionSummary = model.getRegionSummary();



            var KPILabelKeys = view.getMapView().getKPILabelKeys();
            var KPIHeaderLabel = view.getMapView().getKPIHeaderLabel();
            var KPIValueLabels = view.getMapView().getKPIValueLabels();

            KPIHeaderLabel.setText(sender.getId());
            for (String key : KPILabelKeys )
            {
                KPIValueLabels.get(key).setText(regionSummary.getData().get(sender.getId()).get(key).toString());
            }
        }
    }

}
