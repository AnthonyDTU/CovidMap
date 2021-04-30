package Dashboard.Controller.ComponentIntializers;

import Dashboard.Model.DashboardModel;
import Dashboard.Model.DataFile;
import Dashboard.View.Components.DataView;
import Dashboard.View.Components.KPIField;

import javafx.geometry.Pos;
import javafx.scene.chart.Chart;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;

public class DataViewInitializer {

    DataView dataView;
    VBox mainLayout = new VBox(10);
    ScrollPane KPIArea = new ScrollPane();
    ScrollPane chartsArea = new ScrollPane();
    List<String> chartsKeys = new ArrayList<>();
    HashMap<String, Chart> charts = new HashMap<>();
    List<String> KPIFieldKeys = new ArrayList<>();
    HashMap<String, KPIField> KPIFields = new HashMap<>();

    private final int mainLayoutWidth = 800;


    public DataViewInitializer(){
        dataView = new DataView(mainLayout, KPIArea, chartsArea, chartsKeys, charts, KPIFieldKeys, KPIFields);
    }


    private ScrollPane CreateKPIFieldArea()
    {

        ScrollPane KPIAreaScrollPane = new ScrollPane();
        KPIAreaScrollPane.setPrefWidth(mainLayoutWidth);
        return KPIAreaScrollPane;
        //
        //KPIArea.setPrefWidth(mainLayoutWidth);
//
        //Button button = new Button("press me");
        //KPIArea.setContent(button);
//
        //VBox KPIVbox = new VBox(10);
        //KPIVbox.getChildren().add(KPIArea);
        //KPIVbox.setAlignment(Pos.CENTER);
        //KPIVbox.setPrefWidth(mainLayoutWidth);




        // DashboardModel data = new DashboardModel();
        // DataFile testsOverTime = data.getTestsOverTimeData();

        // for (String KPI : testsOverTime.getDataFieldKeys())
        // {
        //     KPIFieldKeys.add(KPI);
        // }

        // KPIField kpiField = new KPIField();



    }

    private ScrollPane CreateChartsArea()
    {
        ScrollPane chartsScrollPane = new ScrollPane();
        chartsScrollPane.setPrefWidth(mainLayoutWidth);
        return chartsScrollPane;
    }

    public DataView CreateDataView()
    {

        KPIArea = CreateKPIFieldArea();
        chartsArea = CreateChartsArea();
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.getRowConstraints().addAll(createNewRow(50), createNewRow(50));
        GridPane.setConstraints(KPIArea, 0,0);
        GridPane.setConstraints(chartsArea, 0, 1);

        grid.getChildren().addAll(KPIArea, chartsArea);
        grid.setPrefHeight(900);

        mainLayout.getChildren().addAll(grid);




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
