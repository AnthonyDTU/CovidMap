package Dashboard.Controller.ComponentIntializers;

import Dashboard.Model.DashboardModel;
import Dashboard.Model.DataFile;
import Dashboard.View.Components.DataView;
import Dashboard.View.Components.KPIField;

import com.github.kilianB.MultiTypeChart;
import com.github.kilianB.TypedSeries;
import com.github.kilianB.ValueMarker;
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

        DashboardModel data = new DashboardModel();

        MultiTypeChart<String, Number> multiTypeChart= new MultiTypeChart<>(new CategoryAxis(), new NumberAxis());

        TypedSeries<String,Number> daylySeries = TypedSeries.<String,Number>
                builder("Positive").area()
                .withYAxisIndex(0)
                .withYAxisSide(Side.LEFT)
                .build();

        TypedSeries<String,Number> kumulativSeries = TypedSeries.<String,Number>
                builder("Kumulativ").area()
                .withYAxisIndex(2)
                .withYAxisSide(Side.RIGHT)
                .build();

        DataFile dataFile;


        int kumulative = 0;
        int dataFieldIndex = 0;

        if (chartCategory == ChartCategory.Positive)
        {
            dataFile = data.getTestsOverTimeData();
            dataFieldIndex = dataFile.getDataFieldKeys().indexOf("NewPositive");
        }
        else if (chartCategory == ChartCategory.Tested)
        {
            dataFile = data.getTestsOverTimeData();
            dataFieldIndex = dataFile.getDataFieldKeys().indexOf("Tested");
        }
        else if (chartCategory == ChartCategory.Admitted)
        {
            dataFile = data.getNewlyAdmittedOverTimeData();
            dataFieldIndex = dataFile.getDataFieldKeys().indexOf("Total");
        }
        else
        {
            dataFile = data.getDeathsOverTimeData();
            dataFieldIndex = dataFile.getDataFieldKeys().indexOf("Antal_døde");
        }

        for (int i = 0; i < dataFile.getLineKeys().size() - 2; i++){ // Dont read last line

            String lineKey = dataFile.getLineKeys().get(i);
            kumulative += dataFile.getData().get(lineKey).get(dataFile.getDataFieldKeys().get(dataFieldIndex));

            daylySeries.addData(lineKey, dataFile.getData().get(lineKey).get(dataFile.getDataFieldKeys().get(dataFieldIndex)));

            if (chartCategory == ChartCategory.Tested)
                kumulativSeries.addData(lineKey, dataFile.getData().get(lineKey).get(dataFile.getDataFieldKeys().get(dataFieldIndex + 1)));
            else
                kumulativSeries.addData(lineKey, kumulative);
        }


//
////Add/remove value markers
//        boolean showLabel = true;
//        multiTypeChart.addValueMarker(new ValueMarker<Number>(5,true, Color.BLUE,showLabel));
//        multiTypeChart.addValueMarker(new ValueMarker<Number>(12,false, Color.BLACK,showLabel));
//        multiTypeChart.addValueMarker(new ValueMarker<Number>(20,false, Color.GREEN,showLabel));

        multiTypeChart.addSeries(kumulativSeries);
        multiTypeChart.addSeries(daylySeries);

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


    enum ChartCategory{
        Positive,
        Tested,
        Admitted,
        Deaths;
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

    private void CreateDataChart(){

        List<String> chartTitles = new ArrayList<>();
        chartTitles.add("Test Over Time");
        chartTitles.add("Positive Over Time");
        chartTitles.add("Deaths Over Time");
        chartTitles.add("Newly Admitted Over Time");
        chartTitles.add("Admitted Over Time");
        chartTitles.add("Cases By Age");
        chartTitles.add("Cases By Sex");


        for (String chartTitle : chartTitles) {

            chartsKeys.add(chartTitle);


        }






    }
}
