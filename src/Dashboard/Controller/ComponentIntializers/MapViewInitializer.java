package Dashboard.Controller.ComponentIntializers;

import Dashboard.Model.DashboardModel;
import Dashboard.Model.DataFile;
import Dashboard.View.Components.KPIField;
import Dashboard.View.Components.MapView;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MapViewInitializer {

    MapView mapView;
    VBox mainLayout = new VBox(10);
    List<String> regionButtonKeys = new ArrayList<>();
    HashMap<String, Button> regionButtons = new HashMap<>();
    Label KPIHeaderLabel = new Label();
    ImageView mapImageView = new ImageView();
    List<String> KPIFieldKeys = new ArrayList<>();
    HashMap<String, KPIField> KPIFields = new HashMap<>();

    private final int mainLayoutWidth = 800;
    private final int mapHeight = 750;
    private final int headerHeight = 100;


    public MapViewInitializer(){
        mapView = new MapView(mainLayout, mapImageView, regionButtonKeys, regionButtons, KPIHeaderLabel, KPIFieldKeys, KPIFields);
    }


    public MapView CreateMapView() {

        VBox mapHeader = CreateMapHeader();
        mapHeader.setAlignment(Pos.TOP_CENTER);

        Pane mapPane = CreateMapPane();

        mainLayout.getChildren().addAll(mapHeader, mapPane);
        mainLayout.setAlignment(Pos.CENTER);
        mainLayout.setPrefWidth(mainLayoutWidth);
        //mainLayout.setMaxWidth(mainLayoutWidth);

        return mapView;
    }


    private VBox CreateMapHeader(){

        GridPane KPIGrid = new GridPane();

        DashboardModel data = new DashboardModel();
        DataFile regionSummary = data.getRegionSummary();

        KPIGrid.getRowConstraints().addAll(createNewRow(40), createNewRow(60));
        KPIGrid.setAlignment(Pos.TOP_CENTER);
        KPIGrid.setPrefWidth(mainLayoutWidth);

        KPIHeaderLabel.setText(regionSummary.getLineKeys().get(regionSummary.getLineKeys().size() - 1));
        KPIHeaderLabel.setFont(Font.font("Arial", FontWeight.BOLD, 28));
        KPIHeaderLabel.setAlignment(Pos.CENTER);

        int i = 0;
        for (String KPI : regionSummary.getDataFieldKeys())
        {
            KPIFieldKeys.add(KPI);
            KPIFields.put(KPI, new KPIField(KPI, regionSummary.getData().get(regionSummary.getLineKeys().get(regionSummary.getLineKeys().size() - 1)).get(KPI).toString()));

            KPIGrid.getColumnConstraints().add(createNewColumn((int)(100 / regionSummary.getDataFieldKeys().size())));
            GridPane.setConstraints(KPIFields.get(KPI).getLayout(), i++, 1);
            KPIGrid.getChildren().add(KPIFields.get(KPI).getLayout());
        }

        VBox mapHeader = new VBox();
        mapHeader.setMinHeight(headerHeight);
        mapHeader.setMinWidth(mainLayoutWidth);
        mapHeader.setMaxWidth(mainLayoutWidth);
        //mapHeader.setAlignment(Pos.CENTER);
        mapHeader.setAlignment(Pos.CENTER);
        mapHeader.getChildren().addAll(KPIHeaderLabel, KPIGrid);

        return mapHeader;
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


    private Pane CreateMapPane()
    {
        DashboardModel data = new DashboardModel();
        DataFile regionSummary = data.getRegionSummary();

        try
        {
            mapImageView.setImage(new Image(new FileInputStream("Regionskort.jpg")));
            mapImageView.setPreserveRatio(true);
            mapImageView.setFitWidth(mainLayoutWidth);
            mapImageView.setId(regionSummary.getLineKeys().get(regionSummary.getLineKeys().size() - 1));
        }
        catch (FileNotFoundException e)
        {
            // MessageBox
        }

        Pane mapPane = new Pane();
        mapPane.getChildren().add(mapImageView);
        mapPane.setMaxWidth(mainLayoutWidth);
        //mapPane.setMinWidth(mainLayoutWidth);

        for (int i = 0; i < regionSummary.getLineKeys().size() - 1; i++)
        {
            int totalPositive = regionSummary.getData().get(regionSummary.getLineKeys().get(regionSummary.getLineKeys().size() - 1)).get(regionSummary.getDataFieldKeys().get(1));
            int regionPositive = regionSummary.getData().get(regionSummary.getLineKeys().get(i)).get(regionSummary.getDataFieldKeys().get(1));

            Button regionButton = createRegionButton(regionSummary.getLineKeys().get(i), totalPositive, regionPositive);
            regionButtonKeys.add(regionSummary.getLineKeys().get(i));
            regionButtons.put(regionSummary.getLineKeys().get(i), regionButton);
            mapPane.getChildren().add(regionButton);
        }

        return mapPane;
    }


    private Button createRegionButton(String ButtonID, int totalPositive, int regionPositive)
    {
        Button regionButton = new Button();
        regionButton.setId(ButtonID);

        regionButton.setAlignment(Pos.CENTER);
        regionButton.setLayoutX(regionCoordinates.valueOf(ButtonID).getXCoordinate());
        regionButton.setLayoutY(regionCoordinates.valueOf(ButtonID).getYCoordinate());

        float radius = 15 + (((float)regionPositive / (float)totalPositive) * 100);

        regionButton.setShape(new Circle(radius)); // Radius calculated based on cases in region
        regionButton.setMinSize(2*radius, 2*radius);
        regionButton.setMaxSize(2*radius, 2*radius);

        regionButton.setOpacity(0.35);
        regionButton.setStyle("-fx-background-color: #ff0000");
        return regionButton;
    }


    enum regionCoordinates {
        Nordjylland (335, 90),
        Midtjylland (215, 320),
        Syddanmark (190,550),
        Sjælland (550,530),
        Hovedstaden (605,390);

        private int xCoordinate;
        private int yCoordinate;

        regionCoordinates(int xCoordinate, int yCoordinate) {
            this.xCoordinate = xCoordinate;
            this.yCoordinate = yCoordinate;
        }

        public int getXCoordinate() {
            return xCoordinate;
        }

        public int getYCoordinate(){
            return yCoordinate;
        }
    }
}
