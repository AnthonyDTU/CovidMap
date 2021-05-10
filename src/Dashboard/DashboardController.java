package Dashboard;

import Dashboard.Components.*;
import javafx.scene.chart.Chart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class DashboardController {

    private DashboardModel model;
    private DashboardView view;

    private final String testsOverTimeFilename = "Covid Data/Test_pos_over_time.csv";
    private final String testsByRegionOverTimeFilename = "Covid Data/Test_regioner.csv";
    private final String deathsOverTimeFilename = "Covid Data/Deaths_over_time.csv";
    private final String newlyAdmittedOverTimeFilename = "Covid Data/Newly_admitted_over_time.csv";
    private final String regionSummaryFilename = "Covid Data/Region_summary.csv";
    private final String casesByAgeFilename = "Covid Data/Cases_by_age.csv";
    private final String casesBySexFilename = "Covid Data/Cases_by_sex.csv";
    private final String municipalityTestedFilename = "Covid Data/Municipality_tested_persons_time_series.csv";
    private final String municipalityPositiveFilename = "Covid Data/Municipality_cases_time_series.csv";

    public DashboardController(DashboardModel model, DashboardView view){
        this.model = model;
        this.view = view;

        // Intitialize the Model
        this.model.setTestsOverTimeData(new DataFile().LoadFile(testsOverTimeFilename));
        this.model.setTestsByRegionsOverTimeData(new DataFile().LoadFile(testsByRegionOverTimeFilename));
        this.model.setDeathsOverTimeData(new DataFile().LoadFile(deathsOverTimeFilename));
        this.model.setNewlyAdmittedOverTimeData(new DataFile().LoadFile(newlyAdmittedOverTimeFilename));
        this.model.setRegionSummaryData(new DataFile().LoadFile(regionSummaryFilename));
        this.model.setCasesByAgeData(new DataFile().LoadFile(casesByAgeFilename));
        this.model.setCasesBySexData(new DataFile().LoadFile(casesBySexFilename));
        this.model.setMunicipalityTestedOverTime(new DataFile().LoadFile(municipalityTestedFilename));
        this.model.setMunicipalityPositiveOverTime(new DataFile().LoadFile(municipalityPositiveFilename));

        if (this.model.checkAllFilesLoaded()){
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
            //
            this.view.createRoot(view.getDataView(), view.getMapView());
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("File not loaded");
            alert.setContentText("One or more files could not be found, or were not loaded correctly");
            alert.show();
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


    class FilterComboBoxesEventHandler implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent actionEvent)
        {
            VBox newChartsArea = new VBox(40);

            for (int j = 0; j < 4; j++){

                ChartConfigurations chartConfiguration = ChartConfigurations.values()[j];
                chartConfiguration.resetCumulativeValue();

                String municipality = view.getDataView().getMunicipalitySelected();

                if (chartConfiguration == ChartConfigurations.Positive && municipality != "Danmark"){
                    chartConfiguration = ChartConfigurations.MunicipalityPositive;
                    chartConfiguration.resetCumulativeValue();
                }
                else if (chartConfiguration == ChartConfigurations.Tested && municipality != "Danmark"){
                    chartConfiguration = ChartConfigurations.MunicipalityTested;
                    chartConfiguration.resetCumulativeValue();
                }

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

                Chart dataChart = new DataChart().createUpdatedTimeChart(chartConfiguration, municipality, startIndex);
                view.getDataView().getCharts().put(chartConfiguration, dataChart);
                newChartsArea.getChildren().add(dataChart);
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
    }
}
