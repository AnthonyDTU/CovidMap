package Dashboard;

import Dashboard.Components.ChartConfigurations;
import Dashboard.ComponentIntializers.DataViewInitializer;
import Dashboard.Components.DataFile;
import Dashboard.ComponentIntializers.MapViewInitializer;
import Dashboard.Components.DataView;
import Dashboard.Components.MapView;
import com.github.kilianB.MultiTypeChart;
import com.github.kilianB.TypedSeries;
import javafx.geometry.Side;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.Chart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Button;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javax.xml.crypto.Data;
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
        this.model.setCasesBySexData(LoadFile(model.getFolderPath(), casesBySexFilename));

        // Intialize the View
        //
        // Map View:
        this.view.setMapView(new MapView().InitializeMapView());
        this.view.getMapView().addEventHandlerToRegionButtons(new RegionButtonEventHandler());
        this.view.getMapView().addEventHandlerToImageVIew(new ImageViewEventHandler());
        //
        // Data View:
        this.view.setDataView(new DataView().InitializeDataView());
        this.view.getDataView().addEventHandlerToComboBox(new FilterComboBoxesEventHandler());
    }

    private DataFile LoadFile(String folderPath, String fileName)
    {
        try
        {
            // Tutorial for reading file found at https://www.javatpoint.com/java-string-to-int, but adapted a lot
            // parsing a CSV file into BufferedReader class constructor
            BufferedReader bufferedReader = new BufferedReader(new FileReader("Covid Data\\" + fileName));

            String[] data = bufferedReader.readLine().split(splitBy);
            List<String> dataFieldKeys = new ArrayList<>(Arrays.asList(data));
            dataFieldKeys.remove(0);

            List<String> lineKeys = new ArrayList<>();
            HashMap<String, HashMap<String, Integer>> hashMaps = new HashMap<>();

            while ((line = bufferedReader.readLine()) != null)
            {
                data = line.replace(".", "").replace(",",".").split(splitBy);
                HashMap<String, Integer> dataHashMap = new HashMap<>();

                for (int i = 1; i < data.length; i++){

                    if (data[i].contains("(")){
                        int index = data[i].indexOf("(");
                        data[i] = data[i].substring(0, index);
                    }
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

    class FilterComboBoxesEventHandler implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent actionEvent)
        {
            VBox newChartsArea = new VBox(40);

            for (int j = 0; j < 4; j++){

                ChartConfigurations chartConfiguration = ChartConfigurations.values()[j];
                chartConfiguration.resetCumulativeValue();

                int startIndex = 0;
                switch (view.getDataView().getTimePeriodSelected())
                {
                    case "All Time":

                        break;

                    case "30 Dage":
                        startIndex = chartConfiguration.getDataFile().getLineKeys().size() - 30 - chartConfiguration.getNumberOfTotalLines();
                        break;

                    case "7 Dage":
                        startIndex = chartConfiguration.getDataFile().getLineKeys().size() - 7 - chartConfiguration.getNumberOfTotalLines();
                        break;
                }

                Chart multiTypeChart = createTimeChart(chartConfiguration, startIndex);
                view.getDataView().getCharts().put(chartConfiguration, multiTypeChart);
                newChartsArea.getChildren().add(multiTypeChart);
            }

            HBox pieCharts = new HBox(0);
            pieCharts.getChildren().addAll(view.getDataView().getCharts().get(ChartConfigurations.ByAge), view.getDataView().getCharts().get(ChartConfigurations.BySex));
            pieCharts.setMaxWidth(700);
            newChartsArea.getChildren().add(pieCharts);

            ScrollPane newChartsScrollPane = new ScrollPane();
            newChartsScrollPane.setStyle("-fx-background: white;");
            newChartsScrollPane.setPrefWidth(700);
            newChartsScrollPane.setPannable(true);
            newChartsScrollPane.setContent(newChartsArea);

            view.getDataView().reloadCharts(newChartsScrollPane);
        }


        private Chart createTimeChart(ChartConfigurations chartConfiguration, int startIndex){

            TypedSeries<String,Number> cumulativeSeries = TypedSeries.<String,Number>
                    builder(chartConfiguration.getLegendTwoTitle()).area()
                    .withYAxisIndex(0)
                    .withYAxisSide(Side.LEFT)
                    .build();

            TypedSeries<String,Number> dailySeries = TypedSeries.<String,Number>
                    builder(chartConfiguration.getLegendOneTitle()).area()
                    .withYAxisIndex(1)
                    .withYAxisSide(Side.RIGHT)
                    .build();


            DataFile dataFile = chartConfiguration.getDataFile();

            for (int i = startIndex; i < dataFile.getLineKeys().size() - chartConfiguration.getNumberOfTotalLines(); i++){ // Dont read last line

                String lineKey = dataFile.getLineKeys().get(i);
                chartConfiguration.addToCumulativeValue(dataFile.getData().get(lineKey).get(dataFile.getDataFieldKeys().get(chartConfiguration.getIndexOfData())));

                dailySeries.addData(lineKey, dataFile.getData().get(lineKey).get(dataFile.getDataFieldKeys().get(chartConfiguration.getIndexOfData())));
                cumulativeSeries.addData(lineKey, chartConfiguration.getCumulativeValue());
            }


            MultiTypeChart<String, Number> multiTypeChart= new MultiTypeChart<>(new CategoryAxis(), new NumberAxis());

            multiTypeChart.setTitle(chartConfiguration.getTitle());
            multiTypeChart.setId(String.valueOf(chartConfiguration.getConfigurationIndex()));

            multiTypeChart.addSeries(cumulativeSeries);
            multiTypeChart.addSeries(dailySeries);

            multiTypeChart.setSeriesColor(1, 90);
            multiTypeChart.setSeriesColor(2, 50);

            return multiTypeChart;
        }
    }
}
