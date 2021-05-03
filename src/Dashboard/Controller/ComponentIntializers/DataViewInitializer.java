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
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.geometry.VPos;
import javafx.scene.chart.Chart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
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

    private final int mainLayoutWidth = 800;
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
        ScrollPane areaScrollPane = new ScrollPane();
        areaScrollPane.setPrefWidth(mainLayoutWidth);
        areaScrollPane.setPannable(true);

//Create a chart


        MultiTypeChart<Number, Number> multiTypeChart= new MultiTypeChart<>(new NumberAxis(), new NumberAxis());

//Builder pattern
        TypedSeries lineSeries = TypedSeries.builder("Line").line().build();
        TypedSeries areaSeries = TypedSeries.builder("Area").area().build();
        TypedSeries scatterSeries = TypedSeries.builder("Scatter").scatter().build();

//Advanced control with full generics
        TypedSeries<Number,Number> lineSeries1 = TypedSeries.<Number,Number>
                builder("Line Series 1").line()
                .withYAxisIndex(1)
                .withYAxisSide(Side.RIGHT)
                .build();

//Add/remove value markers
        boolean showLabel = true;
        multiTypeChart.addValueMarker(new ValueMarker<Number>(5,true,Color.BLUE,showLabel));
        multiTypeChart.addValueMarker(new ValueMarker<Number>(12,false,Color.BLACK,showLabel));
        multiTypeChart.addValueMarker(new ValueMarker<Number>(20,false,Color.GREEN,showLabel));

//add series
        multiTypeChart.addSeries(scatterSeries);
        multiTypeChart.addSeries(areaSeries);
        multiTypeChart.addSeries(lineSeries);

//customize color if desired
        multiTypeChart.setSeriesColor(1, 90);
        multiTypeChart.setSeriesColor(2, 50);

        areaScrollPane.setContent(multiTypeChart);


        return areaScrollPane;
    }

    public DataView CreateDataView()
    {
        headerBar = CreateHeaderBar();
        chartsArea = CreateScrollPane();

        mainLayout.getChildren().addAll(headerBar, chartsArea);

        return dataView;

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
