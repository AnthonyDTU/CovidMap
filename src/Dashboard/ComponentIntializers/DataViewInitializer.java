package Dashboard.ComponentIntializers;

import Dashboard.Components.DataChart;
import Dashboard.DashboardModel;
import Dashboard.Components.DataFile;
import Dashboard.Components.ChartConfigurations;
import Dashboard.Components.DataView;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.*;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataViewInitializer {

    // ComboBox introduktion:
    // https://docs.oracle.com/javafx/2/ui_controls/combo-box.htm
    private final ObservableList<String> timePeriodOptions =
            FXCollections.observableArrayList(
                    "All Time",
                    "30 Dage",
                    "7 Dage"
            );

    private ObservableList<String> municipalityOptions = FXCollections.observableArrayList();

    public DataViewInitializer(){
    }


    // Receives an empty DataView, and return an initialized one.
    //
    public DataView InitializeDataView(DataView dataView)
    {
        dataView.setChartsKeys(createChartKeys());
        dataView.setCharts(createCharts(dataView));
        dataView.setTimePeriodComboBox(createTimePeriodComboBox(dataView));
        dataView.setMunicipalityComboBox(createMunicipalityComboBox(dataView));
        dataView.setHeaderBar(createHeaderBar(dataView));
        dataView.setChartsArea(createChartsArea(dataView));
        dataView.setMainLayout(createMainLayout(dataView));

        return dataView;
    }

    // Creates keys for all the charts, for later access
    //
    private List<ChartConfigurations> createChartKeys()
    {
        List<ChartConfigurations> chartConfigurationKeys = new ArrayList<>();
        chartConfigurationKeys.add(ChartConfigurations.Positive);
        chartConfigurationKeys.add(ChartConfigurations.Tested);
        chartConfigurationKeys.add(ChartConfigurations.Admitted);
        chartConfigurationKeys.add(ChartConfigurations.Deaths);
        chartConfigurationKeys.add(ChartConfigurations.ByAge);
        chartConfigurationKeys.add(ChartConfigurations.BySex);
        return chartConfigurationKeys;
    }

    // Creates and configures all the charts contained in the DataView
    //
    private HashMap<ChartConfigurations, Chart> createCharts(DataView dataView)
    {
        HashMap<ChartConfigurations, Chart> charts = new HashMap<>();

        for (ChartConfigurations chartConfiguration : dataView.getChartsKeys())
        {
            Chart chart;

            if (chartConfiguration == ChartConfigurations.ByAge){
                chart = new DataChart().initializeCasesByAgePieChart(chartConfiguration);
            }
            else if (chartConfiguration == ChartConfigurations.BySex){
                chart = new DataChart().initializeCasesBySexPieChart(chartConfiguration);
            }
            else {
                chart = new DataChart().initializeTimeChart(chartConfiguration);
            }

            charts.put(chartConfiguration, chart);
        }

        return charts;
    }

    // Creates the time filter ComboBox
    //
    private ComboBox<String> createTimePeriodComboBox(DataView dataView)
    {
        return createFilterComboBox(dataView,0, timePeriodOptions);
    }

    // Creates the municipality filter ComboBox
    //
    private ComboBox<String> createMunicipalityComboBox(DataView dataView)
    {
        DashboardModel data = new DashboardModel();
        DataFile municipalityData = data.getMunicipalityPositiveOverTime();

        municipalityOptions.clear();
        municipalityOptions.add("Danmark");
        municipalityOptions.addAll(municipalityData.getDataFieldKeys());

        return createFilterComboBox(dataView,1, municipalityOptions);
    }

    // Add the components of the headerBar to a HBox
    //
    private HBox createHeaderBar(DataView dataView)
    {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.getColumnConstraints().addAll(createNewColumn(50), createNewColumn((50)));
        grid.getRowConstraints().addAll(createNewRow(55), createNewRow(5), createNewRow(40));
        grid.getChildren().addAll(createFilterLabel(0, "Time Period:"), dataView.getTimePeriodComboBox(), createFilterLabel(1, "Municipallity:"), dataView.getMunicipalityComboBox());
        grid.setPrefWidth(dataView.getMainLayoutWidth());
        grid.setPrefHeight(dataView.getHeaderBarHeight());

        HBox headerBar = new HBox(10);
        headerBar.setPrefHeight(dataView.getHeaderBarHeight());
        headerBar.getChildren().addAll(grid);

        return headerBar;
    }

    // Creates the area where the charts are to be located
    //
    private VBox createChartsArea(DataView dataView)
    {
        HBox pieCharts = new HBox(0);
        pieCharts.setPrefWidth(dataView.getMainLayoutWidth());

        VBox timeCharts = new VBox(40);
        timeCharts.setPrefWidth(dataView.getMainLayoutWidth());

        for (ChartConfigurations chartConfiguration : dataView.getChartsKeys())
        {
            if (chartConfiguration == ChartConfigurations.ByAge || chartConfiguration == ChartConfigurations.BySex){
                pieCharts.getChildren().add(dataView.getCharts().get(chartConfiguration));
            }
            else {
                timeCharts.getChildren().add(dataView.getCharts().get(chartConfiguration));
            }
        }

        timeCharts.getChildren().add(pieCharts);
        ScrollPane areaScrollPane = new ScrollPane();
        areaScrollPane.setStyle("-fx-background: white;");
        areaScrollPane.setPrefWidth(dataView.getMainLayoutWidth());
        areaScrollPane.setPannable(true);
        areaScrollPane.setContent(timeCharts);

        VBox chartsArea = new VBox();
        chartsArea.getChildren().add(areaScrollPane);
        return chartsArea;
    }

    // Stiches everything together into a final layout
    //
    private VBox createMainLayout(DataView dataView)
    {
        VBox mainLayout = new VBox(10);
        mainLayout.getChildren().addAll(dataView.getHeaderBar(), dataView.getChartsArea());
        mainLayout.setPrefWidth(dataView.getMainLayoutWidth());
        return mainLayout;
    }

    // **********************************************************************************************
    // Helper Functions
    // **********************************************************************************************

    // Creates both the filter comboBoxes, according to the specifications provided in the parameters
    //
    private ComboBox<String> createFilterComboBox(DataView dataView, int gridspot, ObservableList<String> data){

        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.setItems(data);
        comboBox.setValue(data.get(0));
        comboBox.setPrefWidth(dataView.getComboBoxWidth());

        GridPane.setConstraints(comboBox,gridspot,2);
        GridPane.setValignment(comboBox, VPos.TOP);
        GridPane.setHalignment(comboBox, HPos.CENTER);

        return comboBox;
    }

    // Creates a label describing the filter ComboBox function
    //
    private Label createFilterLabel(int gridspot, String text){
        Label regionLabel = new Label(text);
        regionLabel.setFont(Font.font("Arial", 15));
        GridPane.setConstraints(regionLabel,gridspot,0);
        GridPane.setHalignment(regionLabel, HPos.CENTER);
        GridPane.setValignment(regionLabel, VPos.BOTTOM);
        return regionLabel;
    }


    private ColumnConstraints createNewColumn(int percentWidth){
        ColumnConstraints column = new ColumnConstraints();
        column.setPercentWidth(percentWidth);
        return column;
    }

    private RowConstraints createNewRow(int percentHeight){
        RowConstraints row = new RowConstraints();
        row.setPercentHeight(percentHeight);
        return row;
    }
}


