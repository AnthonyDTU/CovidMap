package Dashboard.Components;

import Dashboard.ComponentIntializers.MapViewInitializer;
import Dashboard.DashboardModel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MapView {

    private VBox mainLayout;
    private Pane mapPane;
    private VBox mapHeader;
    private ImageView imageView;
    private List<String> regionButtonKeys;
    private HashMap<String, Button> regionButtons;
    private Label KPIHeaderLabel;
    private List<String> KPIFieldKeys;
    private HashMap<String, KPIField> KPIFields;

    private final int mainLayoutWidth = 800;
    private final int headerHeight = 100;

    public MapView(){
        this.mainLayout = new VBox();
        this.mapHeader = new VBox();
        this.imageView = new ImageView();
        this.regionButtonKeys = new ArrayList<>();
        this.regionButtons = new HashMap<>();
        this.KPIHeaderLabel = new Label();
        this.KPIFieldKeys = new ArrayList<>();
        this.KPIFields = new HashMap<>();
    }

    // This function needs to be called in order to initializer the MapView for my application
    //
    public MapView InitializeMapView(DashboardModel data){
        MapViewInitializer initializer = new MapViewInitializer();
        return initializer.InitializeMapView(data, this);
    }

    // ***********************************************************************************************************
    // Getters and setters
    // ***********************************************************************************************************

    public int getMainLayoutWidth() { return mainLayoutWidth; }
    public int getHeaderHeight() { return headerHeight; }

    public void setMainLayout(VBox mainLayout) { this.mainLayout = mainLayout; }
    public VBox getMainLayout() { return mainLayout; }

    public void setMapPane(Pane mapPane) { this.mapPane = mapPane; }
    public Pane getMapPane() { return mapPane; }

    public void setMapHeader(VBox mapHeader) { this.mapHeader = mapHeader; }
    public VBox getMapHeader() { return mapHeader; }

    public void setImageView(ImageView imageView) { this.imageView = imageView; }
    public ImageView getImageView() { return imageView; }

    public void setRegionButtonKeys(List<String> regionButtonKeys) {this .regionButtonKeys = regionButtonKeys; }
    public List<String> getRegionButtonKeys() { return regionButtonKeys; }

    public void setRegionButtons(HashMap<String, Button> regionButtons) {this.regionButtons = regionButtons; }
    public HashMap<String, Button> getRegionButtons() {
        return regionButtons;
    }

    public void setKPIHeaderLabel(Label KPIHeaderLabel) { this.KPIHeaderLabel = KPIHeaderLabel; }
    public Label getKPIHeaderLabel() {
        return KPIHeaderLabel;
    }

    public void setKPIFieldKeys(List<String> KPIFieldKeys) {this.KPIFieldKeys = KPIFieldKeys; }
    public List<String> getKPIFieldKeys() {
        return KPIFieldKeys;
    }

    public void setKPIFields(HashMap<String, KPIField> KPIFields) { this.KPIFields = KPIFields; }
    public HashMap<String, KPIField> getKPIFields() {
        return  KPIFields;
    }


    public void setKPIFieldValue(String key, String text){
        KPIFields.get(key).setValueLabelText(text);
    }
    public void setKPIHeaderLabel(String text){
        KPIHeaderLabel.setText(text);
    }


    // *****************************************************************************************************************
    // Add Event Handlers
    // *****************************************************************************************************************

    // Adds event handlers to the region selection buttons
    //
    public void addEventHandlerToRegionButtons(EventHandler<ActionEvent> eventHandler)
    {
        for (String buttonKey : regionButtonKeys) {
            regionButtons.get(buttonKey).setOnAction(eventHandler);
        }
    }

    // Adds event handlers to the map imageView
    //
    public void addEventHandlerToImageVIew(EventHandler<MouseEvent> event)
    {
        imageView.setOnMouseClicked(event);
    }
}
