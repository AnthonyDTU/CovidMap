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

    public MapViewInitializer(){
    }


    // Receives an empty MapView, and return an initialized one.
    //
    public MapView InitializeMapView(DashboardModel data, MapView mapView) {

        DataFile regionSummary = data.getRegionSummary();

        mapView.setKPIHeaderLabel(createKPIHeaderLabel(regionSummary));
        mapView.setKPIFieldKeys(createKPIFieldKeys(regionSummary));
        mapView.setKPIFields(createKPIFields(regionSummary));
        mapView.setRegionButtonKeys(createRegionButtonKeys(regionSummary));
        mapView.setRegionButtons(createRegionButtons(regionSummary));
        mapView.setImageView(createMapImageView(mapView, regionSummary));
        mapView.setMapPane(createMapPane(mapView));
        mapView.setMapHeader(createMapHeader(mapView));
        mapView.setMainLayout(createLayout(mapView));

        return mapView;
    }


    // Creates the label describing what the KPI values relates to
    //
    private Label createKPIHeaderLabel(DataFile regionSummary){

        Label KPIHeaderLabel = new Label();
        KPIHeaderLabel.setText(regionSummary.getLineKeys().get(regionSummary.getLineKeys().size() - 1));
        KPIHeaderLabel.setFont(Font.font("Arial", FontWeight.BOLD, 28));
        KPIHeaderLabel.setAlignment(Pos.CENTER);
        return KPIHeaderLabel;
    }

    // Creates the keys for the KPI Fields
    //
    private List<String> createKPIFieldKeys(DataFile regionSummary){

        List<String> KPIFieldKeys = new ArrayList<>(regionSummary.getDataFieldKeys());
        return KPIFieldKeys;
    }

    // Creates the KPI Fields needed
    //
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


    // Creates the region button keys
    //
    private List<String> createRegionButtonKeys(DataFile regionSummary){

        List<String> regionButtonKeys = new ArrayList<>();
        for (int i = 0; i < regionSummary.getLineKeys().size() - 1; i++)
        {
            regionButtonKeys.add(regionSummary.getLineKeys().get(i));
        }

        return regionButtonKeys;
    }


    // Creates and configures the region buttons
    //
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

    // Creates and configures the imageView
    //
    private ImageView createMapImageView(MapView mapView, DataFile regionSummary){

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

    // Composes the Map, with both imageView and buttons
    //
    private Pane createMapPane(MapView mapView){

        Pane mapPane = new Pane();
        mapPane.setMaxWidth(mapView.getMainLayoutWidth());
        mapPane.getChildren().add(mapView.getImageView());

        for (String KPI : mapView.getRegionButtonKeys())
        {
            mapPane.getChildren().add(mapView.getRegionButtons().get(KPI));
        }
        return mapPane;
    }

    // Composes the header containing the KPI title and values
    private VBox createMapHeader(MapView mapView){

        GridPane KPIGrid = new GridPane();
        KPIGrid.getRowConstraints().addAll(createNewRow(40), createNewRow(60));
        KPIGrid.setAlignment(Pos.TOP_CENTER);
        KPIGrid.setPrefWidth(mapView.getMainLayoutWidth());

        for (String KPI : mapView.getKPIFieldKeys())
        {
            KPIGrid.getColumnConstraints().add(createNewColumn((100 / mapView.getKPIFieldKeys().size())));
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

    // Stiches everything together into a final layout
    //
    private VBox createLayout(MapView mapView){

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

    // Creates the actual button used as region selector
    //
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
