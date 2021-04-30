package Dashboard.Controller.ComponentIntializers;

import Dashboard.Model.DashboardModel;
import Dashboard.Model.DataFile;
import Dashboard.View.Components.KPIField;
import Dashboard.View.Components.MapView;

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

        HBox mapHeader = CreateMapHeader();
        mapHeader.setAlignment(Pos.TOP_CENTER);

        Pane mapPane = CreateMapPane();

        mainLayout.getChildren().addAll(mapHeader, mapPane);
        mainLayout.setAlignment(Pos.CENTER);
        mainLayout.setPrefWidth(mainLayoutWidth);

        return mapView;
    }


    private HBox CreateMapHeader(){

        GridPane KPIGrid = new GridPane();

        DashboardModel data = new DashboardModel();
        DataFile regionSummary = data.getRegionSummary();

        KPIGrid.getRowConstraints().addAll(createNewRow(40), createNewRow(60));
        KPIGrid.setAlignment(Pos.TOP_CENTER);
        KPIGrid.setPrefWidth(mainLayoutWidth);

        KPIHeaderLabel.setText(regionSummary.getLineKeys().get(regionSummary.getLineKeys().size() - 1));

        GridPane.setConstraints(KPIHeaderLabel, 0, 0);
        KPIGrid.getChildren().add(KPIHeaderLabel);

        int i = 0;
        for (String KPI : regionSummary.getDataFieldKeys())
        {
            KPIFieldKeys.add(KPI);
            KPIFields.put(KPI, new KPIField(KPI, regionSummary.getData().get(regionSummary.getLineKeys().get(regionSummary.getLineKeys().size() - 1)).get(KPI).toString()));

            KPIGrid.getColumnConstraints().add(createNewColumn((int)(100 / regionSummary.getDataFieldKeys().size())));
            GridPane.setConstraints(KPIFields.get(KPI).getLayout(), i++, 1);
            KPIGrid.getChildren().add(KPIFields.get(KPI).getLayout());
        }

        HBox mapHeader = new HBox();
        mapHeader.setMinHeight(headerHeight);
        mapHeader.setMinWidth(mainLayoutWidth);
        mapHeader.setMaxWidth(mainLayoutWidth);
        //mapHeader.setAlignment(Pos.CENTER);
        mapHeader.setAlignment(Pos.CENTER);
        mapHeader.getChildren().add(KPIGrid);

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
        mapPane.setMinWidth(mainLayoutWidth);

        for (int i = 0; i < regionSummary.getLineKeys().size() - 1; i++)
        {
            Button regionButton = createRegionButton(regionSummary.getLineKeys().get(i));
            regionButtonKeys.add(regionSummary.getLineKeys().get(i));
            regionButtons.put(regionSummary.getLineKeys().get(i), regionButton);
            mapPane.getChildren().add(regionButton);
        }

        return mapPane;
    }


    private Button createRegionButton(String ButtonID){

        Button regionButton = new Button();

        regionButton.setId(ButtonID);
        regionButton.setAlignment(Pos.CENTER);
        regionButton.setLayoutX(100); // Coordinates needed.
        regionButton.setLayoutY(100); // Coordinates needed.
        regionButton.setShape(new Circle(20)); // Radius calculated based on cases in region
        regionButton.setMinSize(2*20, 2*20); // Radius calculated based on cases in region
        regionButton.setMaxSize(2*20, 2*20); // Radius calculated based on cases in region
        regionButton.setOpacity(1); // Figure this out
        regionButton.setStyle(new Color(100, 100, 100).toString()); // Find RGB values for color

        return regionButton;
    }

}
