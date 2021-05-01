package Dashboard.Controller.ComponentIntializers;

import Dashboard.Model.DashboardModel;
import Dashboard.Model.DataFile;
import Dashboard.View.Components.DataView;
import Dashboard.View.Components.KPIField;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.chart.Chart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;

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
    private final int headerBarHeight = 100;
    private final int comboBoxWidth = 250;


    public DataViewInitializer(){
        dataView = new DataView(mainLayout, KPIArea, chartsArea, chartsKeys, charts, KPIFieldKeys, KPIFields);
    }


    private HBox CreateHeaderBar()
    {
        Label timePeriodLabel = new Label("Tidshorisont:");
        GridPane.setConstraints(timePeriodLabel, 0,0);

        Label regionLabel = new Label("Region:");
        GridPane.setConstraints(regionLabel,1,0);

        ObservableList<String> timePeriodOptions =
                FXCollections.observableArrayList(
                        "Total",
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

        GridPane.setConstraints(timePeriodSelector,0,0);
        GridPane.setValignment(timePeriodSelector, VPos.CENTER);
        GridPane.setHalignment(timePeriodSelector, HPos.CENTER);

        ComboBox regionSelector = new ComboBox(regionOptions);
        regionSelector.setPrefWidth(comboBoxWidth);

        GridPane.setConstraints(regionSelector,1,0);
        GridPane.setValignment(regionSelector, VPos.CENTER);
        GridPane.setHalignment(regionSelector, HPos.CENTER);

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.getColumnConstraints().addAll(createNewColumn(50), createNewColumn((50)));
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

    }
}
