package Dashboard.Controller.ComponentIntializers;

import Dashboard.Model.DashboardModel;
import Dashboard.Model.DataFile;
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

    DataView dataView;
    VBox mainLayout = new VBox(10);
    HBox headerBar = new HBox(10);
    ComboBox timePeriodComboBox = new ComboBox();
    ComboBox regionComboBox = new ComboBox();
    VBox chartsArea = new VBox();
    List<ChartConfigurations> chartsKeys = new ArrayList<>();
    HashMap<ChartConfigurations, Chart> charts = new HashMap<>();

    private final int mainLayoutWidth = 700;
    private final int headerBarHeight = 150;
    private final int comboBoxWidth = 250;


    public DataViewInitializer(){
        dataView = new DataView(mainLayout, headerBar, timePeriodComboBox, regionComboBox, chartsArea, chartsKeys, charts);
    }


    private HBox CreateHeaderBar()
    {
        Label timePeriodLabel = new Label("Tidshorisont:");
        timePeriodLabel.setFont(Font.font("Arial", 15));
        GridPane.setConstraints(timePeriodLabel, 0,0);
        GridPane.setHalignment(timePeriodLabel, HPos.CENTER);
        GridPane.setValignment(timePeriodLabel, VPos.BOTTOM);

        Label regionLabel = new Label("Region:");
        regionLabel.setFont(Font.font("Arial", 15));
        GridPane.setConstraints(regionLabel,1,0);
        GridPane.setHalignment(regionLabel, HPos.CENTER);
        GridPane.setValignment(regionLabel, VPos.BOTTOM);

        ObservableList<String> timePeriodOptions =
                FXCollections.observableArrayList(
                        "All Time",
                        "30 Dage",
                        "7 Dage"
                );

        ObservableList<String> regionOptions =
                FXCollections.observableArrayList(
                        "Danmark",
                        "Nordjylland",
                        "Midtjylland",
                        "Syddanmark",
                        "Sjælland",
                        "Hovedstaden"
                );

        timePeriodComboBox.setItems(timePeriodOptions);
        timePeriodComboBox.setValue(timePeriodOptions.get(0));
        timePeriodComboBox.setPrefWidth(comboBoxWidth);

        GridPane.setConstraints(timePeriodComboBox,0,2);
        GridPane.setValignment(timePeriodComboBox, VPos.TOP);
        GridPane.setHalignment(timePeriodComboBox, HPos.CENTER);

        regionComboBox.setItems(regionOptions);
        regionComboBox.setValue(regionOptions.get(0));
        regionComboBox.setPrefWidth(comboBoxWidth);

        GridPane.setConstraints(regionComboBox,1,2);
        GridPane.setValignment(regionComboBox, VPos.TOP);
        GridPane.setHalignment(regionComboBox, HPos.CENTER);

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.getColumnConstraints().addAll(createNewColumn(50), createNewColumn((50)));
        grid.getRowConstraints().addAll(createNewRow(55), createNewRow(5), createNewRow(40));
        grid.getChildren().addAll(timePeriodLabel, timePeriodComboBox, regionLabel, regionComboBox);
        grid.setPrefWidth(mainLayoutWidth);
        grid.setPrefHeight(headerBarHeight);

        HBox headerBar = new HBox(10);
        headerBar.setPrefHeight(headerBarHeight);
        headerBar.getChildren().addAll(grid);

        return headerBar;
    }

    private VBox CreateScrollPane()
    {
        Chart positiveOverTimeChart = createTimeChart(ChartConfigurations.Positive);
        dataView.getCharts().put(ChartConfigurations.Positive, positiveOverTimeChart);
        dataView.getChartsKeys().add(ChartConfigurations.Positive);

        Chart testedOverTimeChart = createTimeChart(ChartConfigurations.Tested);
        dataView.getCharts().put(ChartConfigurations.Tested, testedOverTimeChart);
        dataView.getChartsKeys().add(ChartConfigurations.Positive);

        Chart admittedOverTimeChart = createTimeChart(ChartConfigurations.Admitted);
        dataView.getCharts().put(ChartConfigurations.Admitted, admittedOverTimeChart);
        dataView.getChartsKeys().add(ChartConfigurations.Admitted);

        Chart deathsOverTimeChart = createTimeChart(ChartConfigurations.Deaths);
        dataView.getCharts().put(ChartConfigurations.Deaths, deathsOverTimeChart);
        dataView.getChartsKeys().add(ChartConfigurations.Deaths);

        Chart casesByAgeChart = createCasesByAgePieChart(ChartConfigurations.ByAge);
        dataView.getCharts().put(ChartConfigurations.ByAge, casesByAgeChart);
        dataView.getChartsKeys().add(ChartConfigurations.ByAge);

        Chart casesBySexChart = createCasesBySexChart(ChartConfigurations.BySex);
        dataView.getCharts().put(ChartConfigurations.BySex, casesBySexChart);
        dataView.getChartsKeys().add(ChartConfigurations.BySex);


        HBox pieCharts = new HBox(0);
        pieCharts.getChildren().addAll(casesByAgeChart, casesBySexChart);
        pieCharts.setMaxWidth(mainLayoutWidth);


        VBox charts = new VBox(40);
        charts.getChildren().addAll(positiveOverTimeChart, testedOverTimeChart, admittedOverTimeChart, deathsOverTimeChart, pieCharts);

        ScrollPane areaScrollPane = new ScrollPane();
        areaScrollPane.setStyle("-fx-background: white;");
        areaScrollPane.setPrefWidth(mainLayoutWidth);
        areaScrollPane.setPannable(true);
        areaScrollPane.setContent(charts);


        VBox chartsVBox = new VBox();
        chartsVBox.getChildren().add(areaScrollPane);

        return chartsVBox;
    }

    public DataView CreateDataView()
    {
        dataView.setHeaderBar(CreateHeaderBar());
        dataView.setChartsArea(CreateScrollPane());



        mainLayout.getChildren().addAll(dataView.getHeaderBar(), dataView.getChartsArea());
        mainLayout.setPrefWidth(mainLayoutWidth);

        dataView.setMainLayout(mainLayout);

        return dataView;
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

        MultiTypeChart<String, Number> multiTypeChart= new MultiTypeChart<>(new CategoryAxis(), new NumberAxis());

        multiTypeChart.setTitle(chartConfiguration.getTitle());
        multiTypeChart.setId(String.valueOf(chartConfiguration.getConfigurationIndex()));

        multiTypeChart.addSeries(cumulativeSeries);
        multiTypeChart.addSeries(dailySeries);

        multiTypeChart.setSeriesColor(1, 90);
        multiTypeChart.setSeriesColor(2, 50);

        return multiTypeChart;
    }

    private Chart createCasesByAgePieChart(ChartConfigurations chartConfiguration){

        DashboardModel data = new DashboardModel();
        DataFile casesByAge = data.getCasesByAgeData();

        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

        for (int i = 0; i < casesByAge.getLineKeys().size() - 1; i++){ // Dont read last line

            String lineKey = casesByAge.getLineKeys().get(i);
            String dataKey = casesByAge.getDataFieldKeys().get(0);

            pieChartData.add(new PieChart.Data(lineKey, casesByAge.getData().get(lineKey).get(dataKey)));
        }

        final Chart pieChart = new PieChart(pieChartData);
        pieChart.setTitle(chartConfiguration.getTitle());
        pieChart.setLegendVisible(false);
        pieChart.setPrefWidth(mainLayoutWidth);
        return pieChart;
    }


    private Chart createCasesBySexChart(ChartConfigurations chartConfiguration){

        DataFile casesBySexData = chartConfiguration.getDataFile();

        int indexOfLast = casesBySexData.getLineKeys().size() - 1;
        String keyOfLast = casesBySexData.getLineKeys().get(indexOfLast);

        int casesWomen = casesBySexData.getData().get(keyOfLast).get(casesBySexData.getDataFieldKeys().get(0));
        int casesMen = casesBySexData.getData().get(keyOfLast).get(casesBySexData.getDataFieldKeys().get(1));
        int totalCases = casesBySexData.getData().get(keyOfLast).get(casesBySexData.getDataFieldKeys().get(2));

        float percentageMen = (float) casesMen / totalCases * 100;
        float percentWomen = (float) casesWomen / totalCases * 100;


        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        pieChartData.add(new PieChart.Data("Men", percentageMen));
        pieChartData.add(new PieChart.Data("Women", percentWomen));

        final Chart pieChart = new PieChart(pieChartData);
        pieChart.setTitle(chartConfiguration.getTitle());
        pieChart.setLegendVisible(false);
        pieChart.setPrefWidth(mainLayoutWidth);


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


