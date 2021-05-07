package Dashboard.ComponentIntializers;

import Dashboard.Components.RegionCoordinates;
import Dashboard.DashboardModel;
import Dashboard.Components.DataFile;
import Dashboard.Components.KPIField;
import Dashboard.Components.MapView;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MapViewInitializer {

    MapView mapView;
    public MapViewInitializer(){
        mapView = new MapView(new VBox(),
                new VBox(),
                new ImageView(),
                new ArrayList<String>(),
                new HashMap<String, Button>(),
                new Label(),
                new ArrayList<String>(),
                new HashMap<String, KPIField>());
    }


    public MapView CreateMapView() {

        DashboardModel data = new DashboardModel();
        DataFile regionSummary = data.getRegionSummary();

        mapView.setKPIHeaderLabel(createKPIHeaderLabel(regionSummary));
        mapView.setKPIFieldKeys(createKPIFieldKeys(regionSummary));
        mapView.setKPIFields(createKPIFields(regionSummary));
        mapView.setRegionButtonKeys(createRegionButtonKeys(regionSummary));
        mapView.setRegionButtons(createRegionButtons(regionSummary));
        mapView.setImageView(createMapImageView(regionSummary));
        mapView.setMapPane(createMapPane());
        mapView.setMapHeader(createMapHeader());
        mapView.setMainLayout(createLayout());

        return mapView;
    }


    private Label createKPIHeaderLabel(DataFile regionSummary){

        Label KPIHeaderLabel = new Label();
        KPIHeaderLabel.setText(regionSummary.getLineKeys().get(regionSummary.getLineKeys().size() - 1));
        KPIHeaderLabel.setFont(Font.font("Arial", FontWeight.BOLD, 28));
        KPIHeaderLabel.setAlignment(Pos.CENTER);
        return KPIHeaderLabel;
    }

    private List<String> createKPIFieldKeys(DataFile regionSummary){

        List<String> KPIFieldKeys = new ArrayList<>();
        for (String KPI : regionSummary.getDataFieldKeys()) {
            KPIFieldKeys.add(KPI);
        }
        return KPIFieldKeys;
    }

    private HashMap<String, KPIField> createKPIFields(DataFile regionSummary){

        HashMap<String, KPIField> KPIFields = new HashMap<>();
        int i = 0;
        for (String KPI : regionSummary.getDataFieldKeys())
        {
            KPIField kpiField = new KPIField(KPI, regionSummary.getData().get(regionSummary.getLineKeys().get(regionSummary.getLineKeys().size() - 1)).get(KPI).toString());
            GridPane.setConstraints(kpiField.getLayout(), i++, 1);
            KPIFields.put(KPI, kpiField);
        }

        return KPIFields;
    }


    private List<String> createRegionButtonKeys(DataFile regionSummary){

        List<String> regionButtonKeys = new ArrayList<>();
        for (int i = 0; i < regionSummary.getLineKeys().size() - 1; i++)
        {
            regionButtonKeys.add(regionSummary.getLineKeys().get(i));
        }

        return regionButtonKeys;
    }


    private HashMap<String, Button> createRegionButtons(DataFile regionSummary){

        HashMap<String, Button> regionButtons = new HashMap<>();

        for (int i = 0; i < regionSummary.getLineKeys().size() - 1; i++)
        {
            int totalPositive = regionSummary.getData().get(regionSummary.getLineKeys().get(regionSummary.getLineKeys().size() - 1)).get(regionSummary.getDataFieldKeys().get(1));
            int regionPositive = regionSummary.getData().get(regionSummary.getLineKeys().get(i)).get(regionSummary.getDataFieldKeys().get(1));

            Button regionButton = createRegionButton(regionSummary.getLineKeys().get(i), totalPositive, regionPositive);
            regionButtons.put(regionSummary.getLineKeys().get(i), regionButton);
        }

        return regionButtons;
    }

    private ImageView createMapImageView(DataFile regionSummary){

        ImageView mapImageView = new ImageView();

        try
        {
            mapImageView.setImage(new Image(new FileInputStream("Regionskort.jpg")));
            mapImageView.setPreserveRatio(true);
            mapImageView.setFitWidth(mapView.getMainLayoutWidth());
            mapImageView.setId(regionSummary.getLineKeys().get(regionSummary.getLineKeys().size() - 1));
        }
        catch (FileNotFoundException e)
        {
            // MessageBox
        }

        return mapImageView;
    }

    private Pane createMapPane(){

        Pane mapPane = new Pane();
        mapPane.setMaxWidth(mapView.getMainLayoutWidth());
        mapPane.getChildren().add(mapView.getImageView());

        for (String KPI : mapView.getRegionButtonKeys())
        {
            mapPane.getChildren().add(mapView.getRegionButtons().get(KPI));
        }
        return mapPane;
    }

    private VBox createMapHeader(){

        GridPane KPIGrid = new GridPane();
        KPIGrid.getRowConstraints().addAll(createNewRow(40), createNewRow(60));
        KPIGrid.setAlignment(Pos.TOP_CENTER);
        KPIGrid.setPrefWidth(mapView.getMainLayoutWidth());

        for (String KPI : mapView.getKPIFieldKeys())
        {
            KPIGrid.getColumnConstraints().add(createNewColumn((int)(100 / mapView.getKPIFieldKeys().size())));
            KPIGrid.getChildren().add(mapView.getKPIFields().get(KPI).getLayout());
        }

        VBox mapHeader = new VBox();
        mapHeader.setMinHeight(mapView.getHeaderHeight());
        mapHeader.setMinWidth(mapView.getMainLayoutWidth());
        mapHeader.setMaxWidth(mapView.getMainLayoutWidth());
        mapHeader.setAlignment(Pos.CENTER);
        mapHeader.getChildren().addAll(mapView.getKPIHeaderLabel(), KPIGrid);


        return mapHeader;
    }

    private VBox createLayout(){

        VBox mainLayout = new VBox(10);
        mainLayout.getChildren().addAll(mapView.getMapHeader(), mapView.getMapPane());
        mainLayout.setAlignment(Pos.CENTER);
        mainLayout.setPrefWidth(mapView.getMainLayoutWidth());
        mapView.setMainLayout(mainLayout);
        return mainLayout;
    }

    // **********************************************************************************************
    // Helper Functions
    // **********************************************************************************************

    private Button createRegionButton(String ButtonID, int totalPositive, int regionPositive)
    {
        Button regionButton = new Button();
        regionButton.setId(ButtonID);

        regionButton.setAlignment(Pos.CENTER);
        regionButton.setLayoutX(RegionCoordinates.valueOf(ButtonID).getXCoordinate());
        regionButton.setLayoutY(RegionCoordinates.valueOf(ButtonID).getYCoordinate());

        float radius = 15 + (((float)regionPositive / (float)totalPositive) * 100);

        regionButton.setShape(new Circle(radius)); // Radius calculated based on cases in region
        regionButton.setMinSize(2*radius, 2*radius);
        regionButton.setMaxSize(2*radius, 2*radius);

        regionButton.setOpacity(0.35);
        regionButton.setStyle("-fx-background-color: #ff0000");
        return regionButton;
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
