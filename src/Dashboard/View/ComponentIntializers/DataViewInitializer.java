package Dashboard.View.ComponentIntializers;

import Dashboard.View.Components.DataView;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.Chart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javax.xml.crypto.Data;
import java.util.HashMap;
import java.util.List;

public class DataViewInitializer {


    public DataView CreateDataView()
    {
        VBox layout = new VBox(10);
        ScrollPane keyNumbersScrollPane = new ScrollPane();
        VBox keyNumbersVBox  = new VBox(10);
        for (int i = 0; i < 20; i++){
            keyNumbersVBox.getChildren().add(new Button("Button " + i));
        }

        keyNumbersScrollPane.setContent(keyNumbersVBox);
        keyNumbersScrollPane.setPrefHeight(325);


        // ----------------------------------------
        ScrollPane graphsScrollPane = new ScrollPane();
        VBox graphsVBox  = new VBox(10);

        for (int i = 0; i < 20; i++){
            graphsVBox.getChildren().add(new Button("Button " + i));
        }

        graphsScrollPane.setContent(graphsVBox);
        graphsScrollPane.setPrefHeight(325);

        Label keyNumbersLabel = new Label("Key Numbers:");
        Label graphsLabel = new Label("Graphs:");


        VBox DataViewVBox = new VBox(10);
        layout.getChildren().addAll(keyNumbersLabel, keyNumbersScrollPane, graphsLabel, graphsScrollPane);

        return null;
    }

    private void CreateDataChart(){

    }
}
