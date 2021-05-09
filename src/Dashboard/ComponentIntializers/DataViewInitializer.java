package Dashboard.ComponentIntializers;

import Dashboard.DashboardModel;
import Dashboard.Components.DataFile;
import Dashboard.Components.ChartConfigurations;
import Dashboard.Components.DataView;

import com.github.kilianB.MultiTypeChart;
import com.github.kilianB.TypedSeries;
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

    private final ObservableList<String> regionOptions =
            FXCollections.observableArrayList(
                    "Danmark",
                    "Nordjylland",
                    "Midtjylland",
                    "Syddanmark",
                    "Sjælland",
                    "Hovedstaden"
            );

    public DataViewInitializer(){
    }


    public DataView InitializeDataView(DataView dataView)
    {
        dataView.setChartsKeys(createChartKeys());
        dataView.setCharts(createCharts(dataView));
        dataView.setTimePeriodComboBox(createTimePeriodComboBox(dataView));
        dataView.setRegionComboBox(createRegionComboBox(dataView));
        dataView.setHeaderBar(createHeaderBar(dataView));
        dataView.setChartsArea(createChartsArea(dataView));
        dataView.setMainLayout(createMainLayout(dataView));

        return dataView;
    }

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

    private HashMap<ChartConfigurations, Chart> createCharts(DataView dataView)
    {
        HashMap<ChartConfigurations, Chart> charts = new HashMap<>();

        for (ChartConfigurations chartConfiguration : dataView.getChartsKeys())
        {
            Chart chart;

            if (chartConfiguration == ChartConfigurations.ByAge){
                chart = createCasesByAgePieChart(dataView, chartConfiguration);
            }
            else if (chartConfiguration == ChartConfigurations.BySex){
                chart = createCasesBySexChart(dataView, chartConfiguration);
            }
            else {
                chart = createTimeChart(chartConfiguration);
            }

            charts.put(chartConfiguration, chart);
        }

        return charts;
    }

    private ComboBox createTimePeriodComboBox(DataView dataView)
    {
        return createFilterComboBox(dataView,0, timePeriodOptions);
    }

    private ComboBox createRegionComboBox(DataView dataView)
    {
        return createFilterComboBox(dataView,1, regionOptions);
    }

    private HBox createHeaderBar(DataView dataView)
    {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.getColumnConstraints().addAll(createNewColumn(50), createNewColumn((50)));
        grid.getRowConstraints().addAll(createNewRow(55), createNewRow(5), createNewRow(40));
        grid.getChildren().addAll(createFilterLabel(0, "Time Period:"), dataView.getTimePeriodComboBox(), createFilterLabel(1, "Region:"), dataView.getRegionComboBox());
        grid.setPrefWidth(dataView.getMainLayoutWidth());
        grid.setPrefHeight(dataView.getHeaderBarHeight());

        HBox headerBar = new HBox(10);
        headerBar.setPrefHeight(dataView.getHeaderBarHeight());
        headerBar.getChildren().addAll(grid);

        return headerBar;
    }

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

    private ComboBox createFilterComboBox(DataView dataView, int gridspot, ObservableList<String> data){

        ComboBox comboBox = new ComboBox();
        comboBox.setItems(data);
        comboBox.setValue(data.get(0));
        comboBox.setPrefWidth(dataView.getComboBoxWidth());

        GridPane.setConstraints(comboBox,gridspot,2);
        GridPane.setValignment(comboBox, VPos.TOP);
        GridPane.setHalignment(comboBox, HPos.CENTER);

        return comboBox;
    }

    private Label createFilterLabel(int gridspot, String text){
        Label regionLabel = new Label(text);
        regionLabel.setFont(Font.font("Arial", 15));
        GridPane.setConstraints(regionLabel,gridspot,0);
        GridPane.setHalignment(regionLabel, HPos.CENTER);
        GridPane.setValignment(regionLabel, VPos.BOTTOM);
        return regionLabel;
    }


    private Chart createTimeChart(ChartConfigurations chartConfiguration){

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

        for (int i = 0; i < dataFile.getLineKeys().size() - chartConfiguration.getNumberOfTotalLines(); i++){ // Dont read last line

            String lineKey = dataFile.getLineKeys().get(i);
            chartConfiguration.addToCumulativeValue(dataFile.getData().get(lineKey).get(dataFile.getDataFieldKeys().get(chartConfiguration.getIndexOfData())));

            dailySeries.addData(lineKey, dataFile.getData().get(lineKey).get(dataFile.getDataFieldKeys().get(chartConfiguration.getIndexOfData())));
            cumulativeSeries.addData(lineKey, chartConfiguration.getCumulativeValue());
        }

        // MultiTypeChart is a component found on GitHub:
        // https://github.com/KilianB/JavaFXMultiChart
        MultiTypeChart<String, Number> multiTypeChart= new MultiTypeChart<>(new CategoryAxis(), new NumberAxis());

        multiTypeChart.setTitle(chartConfiguration.getTitle());
        multiTypeChart.setId(String.valueOf(chartConfiguration.getConfigurationIndex()));

        multiTypeChart.addSeries(cumulativeSeries);
        multiTypeChart.addSeries(dailySeries);

        multiTypeChart.setSeriesColor(1, 90);
        multiTypeChart.setSeriesColor(2, 50);

        return multiTypeChart;
    }

    private Chart createCasesByAgePieChart(DataView dataView, ChartConfigurations chartConfiguration){

        DashboardModel data = new DashboardModel();
        DataFile casesByAge = data.getCasesByAgeData();

        // PieChart introduction found here:
        // https://docs.oracle.com/javafx/2/charts/pie-chart.htm
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

        for (int i = 0; i < casesByAge.getLineKeys().size() - 1; i++){ // Dont read last line

            String lineKey = casesByAge.getLineKeys().get(i);
            String dataKey = casesByAge.getDataFieldKeys().get(0);

            pieChartData.add(new PieChart.Data(lineKey, casesByAge.getData().get(lineKey).get(dataKey)));
        }

        final Chart pieChart = new PieChart(pieChartData);
        pieChart.setTitle(chartConfiguration.getTitle());
        pieChart.setLegendVisible(false);
        pieChart.setPrefWidth(dataView.getMainLayoutWidth());
        return pieChart;
    }


    private Chart createCasesBySexChart(DataView dataView, ChartConfigurations chartConfiguration){

        DataFile casesBySexData = chartConfiguration.getDataFile();

        int indexOfLast = casesBySexData.getLineKeys().size() - 1;
        String keyOfLast = casesBySexData.getLineKeys().get(indexOfLast);

        int casesWomen = casesBySexData.getData().get(keyOfLast).get(casesBySexData.getDataFieldKeys().get(0));
        int casesMen = casesBySexData.getData().get(keyOfLast).get(casesBySexData.getDataFieldKeys().get(1));
        int totalCases = casesBySexData.getData().get(keyOfLast).get(casesBySexData.getDataFieldKeys().get(2));

        float percentageMen = (float) casesMen / totalCases * 100;
        float percentWomen = (float) casesWomen / totalCases * 100;


        // PieChart introduction found here:
        // https://docs.oracle.com/javafx/2/charts/pie-chart.htm
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        pieChartData.add(new PieChart.Data("Men", percentageMen));
        pieChartData.add(new PieChart.Data("Women", percentWomen));

        final Chart pieChart = new PieChart(pieChartData);
        pieChart.setTitle(chartConfiguration.getTitle());
        pieChart.setLegendVisible(false);
        pieChart.setPrefWidth(dataView.getMainLayoutWidth());

        return pieChart;
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


