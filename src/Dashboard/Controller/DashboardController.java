package Dashboard.Controller;

import Dashboard.Controller.ComponentIntializers.DataViewInitializer;
import Dashboard.Controller.ComponentIntializers.HeaderViewInitializer;
import Dashboard.Model.DataFile;
import Dashboard.Model.DashboardModel;
import Dashboard.Controller.ComponentIntializers.MapViewInitializer;
import Dashboard.View.DashboardView;
import javafx.scene.control.Button;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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

        // Intitialize the Model
        this.model.setTestsOverTimeData(LoadFile(model.getFolderPath(), testsOverTimeFilename));
        this.model.setTestsByRegionsOverTimeData(LoadFile(model.getFolderPath(), testsByRegionOverTimeFilename));
        this.model.setDeathsOverTimeData(LoadFile(model.getFolderPath(), deathsOverTimeFilename));
        this.model.setNewlyAdmittedOverTimeData(LoadFile(model.getFolderPath(), newlyAdmittedOverTimeFilename));
        this.model.setRegionSummaryData(LoadFile(model.getFolderPath(), regionSummaryFilename));
        this.model.setCasesByAgeData(LoadFile(model.getFolderPath(), casesByAgeFilename));
        //model.setCasesBySexData(LoadFile(model.getFolderPath(), casesBySexFilename));

        // Intialize the View
        // Header Bar:
        HeaderViewInitializer headerBarInitializer = new HeaderViewInitializer();
        this.view.setHeaderView(headerBarInitializer.CreateHeaderView());
        //
        // Map View:
        MapViewInitializer mapViewInitializer = new MapViewInitializer();
        this.view.setMapView(mapViewInitializer.CreateMapView());
        this.view.getMapView().addEventHandlerToRegionButtons(new RegionButtonEventHandler());
        this.view.getMapView().addEventHandlerToImageVIew(new ImageViewEventHandler());
        //
        // Data View:
        DataViewInitializer dataViewInitializer = new DataViewInitializer();
        this.view.setDataView(dataViewInitializer.CreateDataView());


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
            // Turned out to work great, since all data in a category can just be loaded by getting the DataFile() class.
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
        public void handle(ActionEvent actionEvent)
        {
            Button sender = (Button)actionEvent.getSource();
            UpdateRegionKPIFields(sender.getId());
        }
    }

    class ImageViewEventHandler implements EventHandler<MouseEvent>{

        @Override
        public void handle(MouseEvent mouseEvent)
        {
            ImageView sender = (ImageView)mouseEvent.getSource();
            UpdateRegionKPIFields(sender.getId());
        }
    }

    private void UpdateRegionKPIFields(String senderID)
    {
        DataFile regionSummary = model.getRegionSummary();
        view.getMapView().setKPIHeaderLabel(senderID);

        var KPIFieldKeys = view.getMapView().getKPIFieldKeys();
        for (String key : KPIFieldKeys )
        {
            view.getMapView().setKPIFieldValue(key, regionSummary.getData().get(senderID).get(key).toString());
        }
    }

    // Something needs to call this (dropdown selector maybe?)
    class UpdateDataFieldKPIs implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent actionEvent){


        }
    }
}
