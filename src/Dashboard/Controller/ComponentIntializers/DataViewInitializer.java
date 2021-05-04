package Dashboard.Controller.ComponentIntializers;

import Dashboard.Model.DashboardModel;
import Dashboard.Model.DataFile;
import Dashboard.View.Components.DataView;
import Dashboard.View.Components.KPIField;

import com.github.kilianB.MultiTypeChart;
import com.github.kilianB.TypedSeries;
import com.github.kilianB.ValueMarker;
import javafx.beans.property.BooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.*;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


import javafx.scene.paint.Color;


import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;

public class DataViewInitializer {

    DataView dataView;
    VBox mainLayout = new VBox(10);
    ScrollPane KPIArea = new ScrollPane();
    HBox headerBar = new HBox(10);
    ScrollPane chartsArea = new ScrollPane();
    List<String> chartsKeys = new ArrayList<>();
    HashMap<String, Chart> charts = new HashMap<>();
    List<String> KPIFieldKeys = new ArrayList<>();
    HashMap<String, KPIField> KPIFields = new HashMap<>();

    private final int mainLayoutWidth = 700;
    private final int headerBarHeight = 150;
    private final int comboBoxWidth = 250;


    public DataViewInitializer(){
        dataView = new DataView(mainLayout, KPIArea, chartsArea, chartsKeys, charts, KPIFieldKeys, KPIFields);
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

        ComboBox timePeriodSelector = new ComboBox(timePeriodOptions);
        timePeriodSelector.setPrefWidth(comboBoxWidth);

        GridPane.setConstraints(timePeriodSelector,0,2);
        GridPane.setValignment(timePeriodSelector, VPos.TOP);
        GridPane.setHalignment(timePeriodSelector, HPos.CENTER);

        ComboBox regionSelector = new ComboBox(regionOptions);
        regionSelector.setPrefWidth(comboBoxWidth);

        GridPane.setConstraints(regionSelector,1,2);
        GridPane.setValignment(regionSelector, VPos.TOP);
        GridPane.setHalignment(regionSelector, HPos.CENTER);

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.getColumnConstraints().addAll(createNewColumn(50), createNewColumn((50)));
        grid.getRowConstraints().addAll(createNewRow(55), createNewRow(5), createNewRow(40));
        grid.getChildren().addAll(timePeriodLabel, timePeriodSelector, regionLabel, regionSelector);
        grid.setPrefWidth(mainLayoutWidth);
        grid.setPrefHeight(headerBarHeight);

        HBox headerBar = new HBox(10);
        headerBar.setPrefHeight(headerBarHeight);
        headerBar.getChildren().addAll(grid);

        return headerBar;
    }

    private ScrollPane CreateScrollPane()
    {
        Chart positiveOverTimeChart = createTimeChart(ChartCategory.Positive);
        Chart testedOverTimeChart = createTimeChart(ChartCategory.Tested);
        Chart admittedOverTimeChart = createTimeChart(ChartCategory.Admitted);
        Chart deathsOverTimeChart = createTimeChart(ChartCategory.Deaths);

        Chart casesByAgeChart = createCasesByAgePieChart();
        dataView.getCharts().put("Cases By Age", casesByAgeChart);
        dataView.getChartsKeys().add("Cases By Age");

        Chart casesBySexChart = createCasesBySexChart();
        dataView.getCharts().put("Cases By Sex", casesBySexChart);
        dataView.getChartsKeys().add("Cases By Sex");




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

        return areaScrollPane;
    }

    public DataView CreateDataView()
    {
        headerBar = CreateHeaderBar();
        chartsArea = CreateScrollPane();

        mainLayout.getChildren().addAll(headerBar, chartsArea);
        mainLayout.setPrefWidth(mainLayoutWidth);

        return dataView;
    }

    private Chart createTimeChart(ChartCategory chartCategory){

        TypedSeries<String,Number> cumulativeSeries = TypedSeries.<String,Number>
                builder(chartCategory.getLegendTwoTitle()).area()
                .withYAxisIndex(0)
                .withYAxisSide(Side.LEFT)
                .build();

        TypedSeries<String,Number> dailySeries = TypedSeries.<String,Number>
                builder(chartCategory.getLegendOneTitle()).area()
                .withYAxisIndex(1)
                .withYAxisSide(Side.RIGHT)
                .build();


        DataFile dataFile = chartCategory.getDataFile();

        for (int i = 0; i < dataFile.getLineKeys().size() - chartCategory.getNumberOfTotalLines(); i++){ // Dont read last line

            String lineKey = dataFile.getLineKeys().get(i);
            chartCategory.addToCumulativeValue(dataFile.getData().get(lineKey).get(dataFile.getDataFieldKeys().get(chartCategory.getIndexOfData())));

            dailySeries.addData(lineKey, dataFile.getData().get(lineKey).get(dataFile.getDataFieldKeys().get(chartCategory.getIndexOfData())));
            cumulativeSeries.addData(lineKey, chartCategory.getCumulativeValue());
        }

        MultiTypeChart<String, Number> multiTypeChart= new MultiTypeChart<>(new CategoryAxis(), new NumberAxis());

        multiTypeChart.setTitle(chartCategory.getTitle());

        multiTypeChart.addSeries(cumulativeSeries);
        multiTypeChart.addSeries(dailySeries);

        multiTypeChart.setSeriesColor(1, 90);
        multiTypeChart.setSeriesColor(2, 50);

        return multiTypeChart;
    }

    private Chart createCasesByAgePieChart(){

        DashboardModel data = new DashboardModel();
        DataFile casesByAge = data.getCasesByAgeData();

        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

        for (int i = 0; i < casesByAge.getLineKeys().size() - 1; i++){ // Dont read last line

            String lineKey = casesByAge.getLineKeys().get(i);
            String dataKey = casesByAge.getDataFieldKeys().get(0);

            pieChartData.add(new PieChart.Data(lineKey, casesByAge.getData().get(lineKey).get(dataKey)));
        }

        final Chart pieChart = new PieChart(pieChartData);
        pieChart.setTitle("Cases By Age");
        pieChart.setLegendVisible(false);
        pieChart.setPrefWidth(mainLayoutWidth);
        return pieChart;
    }


    private Chart createCasesBySexChart(){
        DashboardModel data = new DashboardModel();
        DataFile casesBySexData = data.getCasesBySexData();


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
        pieChart.setTitle("Cases By Sex");
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


enum ChartCategory{
    Positive ("Positive Cases","Daily Positive","Total Positive",0,2,0, new DataFile()),
    Tested ("Tested","Daily Tested","Total Tested",4,2,0, new DataFile()),
    Admitted ("Admitted","Daily Admitted","Total Admitted",0,1,0, new DataFile()),
    Deaths ("Deaths","Daily Deaths","Total Deaths",0,1,0, new DataFile());

    private String title;
    private String legendOneTitle;
    private String legendTwoTitle;
    private int indexOfData;
    private int numberOfTotalLines;
    private int cumulativeValue;
    private DataFile dataFile;

    ChartCategory(String title, String legendOneTitle, String legendTwoTitle, int indexOfData, int numberOfTotalLines, int cumalativeValue, DataFile dataFile){
        this.title = title;
        this.legendOneTitle = legendOneTitle;
        this.legendTwoTitle = legendTwoTitle;
        this.indexOfData = indexOfData;
        this.numberOfTotalLines = numberOfTotalLines;
        this.cumulativeValue = cumalativeValue;
        this.dataFile = dataFile;
    }

    public String getTitle(){
        return title;
    }

    public String getLegendOneTitle(){
        return legendOneTitle;
    }

    public String getLegendTwoTitle(){
        return legendTwoTitle;
    }

    public int getIndexOfData(){
        return indexOfData;
    }

    public int getNumberOfTotalLines(){
        return numberOfTotalLines;
    }

    public void addToCumulativeValue(int value){
        cumulativeValue += value;
    }

    public int getCumulativeValue(){
        return cumulativeValue;
    }

    public DataFile getDataFile(){

        DashboardModel data = new DashboardModel();
        if (this == Positive){
            return data.getTestsOverTimeData();
        }
        else if (this == Tested){
            return data.getTestsOverTimeData();
        }
        else if (this == Admitted){
            return data.getNewlyAdmittedOverTimeData();
        }
        else {
            return data.getDeathsOverTimeData();
        }
    }
}
