package Dashboard.View.ComponentIntializers;

import Dashboard.Model.DashboardModel;
import Dashboard.Model.DataFile;
import Dashboard.View.Components.MapView;
import com.sun.javafx.geom.Shape;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MapViewInitializer {

    MapView mapView;
    VBox layout = new VBox(10);
    List<String> buttonKeys = new ArrayList<>();
    HashMap<String, Button> regionButtons = new HashMap<>();
    Label KPIHeaderLabel = new Label();
    ImageView mapImageView = new ImageView();
    List<String> KPILabelKeys = new ArrayList<>();
    HashMap<String, Label> KPITitleLabels = new HashMap<>();
    HashMap<String, Label> KPIValueLabels = new HashMap<>();

    public MapViewInitializer(){
        mapView = new MapView(layout, mapImageView, buttonKeys, regionButtons, KPIHeaderLabel, KPILabelKeys, KPITitleLabels, KPIValueLabels);
    }

    public MapView CreateMapView() {

        HBox mapHeader = CreateMapHeader();
        Pane mapPane = CreateMapPane();


        mapHeader.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(mapHeader, mapPane);
        layout.setAlignment(Pos.CENTER);
        layout.setLayoutX(200);
        layout.setLayoutY(200);

        return mapView;
    }

    private HBox CreateMapHeader(){

        // Map Header
        GridPane KPIGrid = new GridPane();
        HBox mapHeader = new HBox();
        mapHeader.setMinHeight(75);

        KPIGrid.setAlignment(Pos.CENTER);

        RowConstraints titleRow = new RowConstraints();
        RowConstraints KPIRow = new RowConstraints();

        titleRow.setPercentHeight(40);
        KPIRow.setPercentHeight(60);

        KPIGrid.getRowConstraints().addAll(titleRow, KPIRow);

        DashboardModel data = new DashboardModel();
        DataFile regionSummary = data.getRegionSummary();

        KPIHeaderLabel = new Label(regionSummary.getLineKeys().get(regionSummary.getLineKeys().size() - 1));
        GridPane.setConstraints(KPIHeaderLabel, 0, 0);
        KPIGrid.getChildren().add(KPIHeaderLabel);

        int i = 0;
        for (String KPI : regionSummary.getDataFieldKeys())
        {
            Label KPITitle = new Label(KPI);
            Label KPIValue = new Label(regionSummary.getData().get(regionSummary.getLineKeys().get(regionSummary.getLineKeys().size() - 1)).get(KPI).toString());

            KPILabelKeys.add(KPI);
            KPITitleLabels.put(KPI, KPITitle);
            KPIValueLabels.put(KPI, KPIValue);

            ColumnConstraints column = new ColumnConstraints();
            column.setPercentWidth((int)(100 / regionSummary.getDataFieldKeys().size()));

            KPIGrid.getColumnConstraints().add(column);
            VBox KPIField = new VBox(10);
            KPIField.getChildren().addAll(KPITitle, KPIValue);
            //KPIGrid.getChildren().add(KPIField);

            GridPane.setConstraints(KPIField, i, 1);
            KPIGrid.getChildren().add(KPIField);
            i++;
        }

        mapHeader.getChildren().add(KPIGrid);
        mapHeader.setLayoutX(100);
        mapHeader.setLayoutY(100);
        mapHeader.setAlignment(Pos.CENTER);

        return mapHeader;
    }


    private Pane CreateMapPane()
    {

        DashboardModel data = new DashboardModel();
        DataFile regionSummary = data.getRegionSummary();

        // Figure out button coordinates

        try
        {
            mapImageView.setImage(new Image(new FileInputStream("Regionskort.jpg")));
            mapImageView.setPreserveRatio(true);
            mapImageView.setFitHeight(750);
        }
        catch (FileNotFoundException e)
        {

        }

        Pane mapPane = new Pane();
        mapPane.setPrefHeight(750);
        mapPane.setPrefSize(mapImageView.getFitWidth(), mapImageView.getFitHeight());
        mapPane.getChildren().add(mapImageView);

        Button imageViewOverlayButton = new Button();
        imageViewOverlayButton.setOpacity(0.1);
        imageViewOverlayButton.setId(regionSummary.getLineKeys().get(regionSummary.getLineKeys().size() - 1));
        imageViewOverlayButton.setPrefSize(750,750);
        imageViewOverlayButton.setOnAction(actionEvent -> mapView.RegionButtonClicked((Button) actionEvent.getSource()));
        mapPane.getChildren().add(imageViewOverlayButton);



        for (int i = 0; i < regionSummary.getLineKeys().size() - 1; i++){

            Button regionButton = new Button();

            regionButton.setId(regionSummary.getLineKeys().get(i));
            regionButton.setAlignment(Pos.CENTER);
            regionButton.setLayoutX(100); // Coordinates needed.
            regionButton.setLayoutY(100); // Coordinates needed.
            regionButton.setShape(new Circle(20)); // Radius calculated based on cases in region
            regionButton.setMinSize(2*20, 2*20); // Radius calculated based on cases in region
            regionButton.setMaxSize(2*20, 2*20); // Radius calculated based on cases in region
            regionButton.setOpacity(0.4); // Figure this out
            regionButton.setStyle(new Color(100, 100, 100).toString()); // Find RGB values for color
            regionButton.setOnAction(actionEvent -> mapView.RegionButtonClicked((Button) actionEvent.getSource())); // No clue if this works...

            buttonKeys.add(regionSummary.getLineKeys().get(i));
            regionButtons.put(regionSummary.getLineKeys().get(i), regionButton);
            mapPane.getChildren().add(regionButton);
        }

        return mapPane;
    }

}
