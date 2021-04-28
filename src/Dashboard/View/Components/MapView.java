package Dashboard.View.Components;

import Dashboard.Model.DashboardModel;
import Dashboard.Model.DataFile;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.chart.Chart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;

import java.awt.*;
import java.util.HashMap;
import java.util.List;

public class MapView {

    VBox mainLayout;
    ImageView imageView;
    List<String> regionButtonKeys;
    HashMap<String, Button> regionButtons;
    Label KPIHeaderLabel;
    List<String> KPILabelKeys;
    HashMap<String, Label> KPITitleLabels;
    HashMap<String, Label> KPIValueLabels;

    public MapView(VBox mainLayout, ImageView imageView, List<String> regionButtonKeys, HashMap<String, Button> regionButtons, Label KPIHeaderLabel, List<String> KPILabelKeys, HashMap<String, Label> KPITitleLabels, HashMap<String, Label> KPIValueLabels){
        this.mainLayout = mainLayout;
        this.imageView = imageView;
        this.regionButtonKeys = regionButtonKeys;
        this.regionButtons = regionButtons;
        this.KPIHeaderLabel = KPIHeaderLabel;
        this.KPILabelKeys = KPILabelKeys;
        this.KPITitleLabels = KPITitleLabels;
        this.KPIValueLabels = KPIValueLabels;
    }

    public VBox getMainLayout() {
        return mainLayout;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public List<String> getButtonKeys() {
        return regionButtonKeys;
    }

    public HashMap<String, Button> getButtons() {
        return regionButtons;
    }

    public Label getKPIHeaderLabel() {
        return KPIHeaderLabel;
    }

    public List<String> getKPILabelKeys() {
        return KPILabelKeys;
    }

    public HashMap<String, Label> getKPITitleLabels() {
        return  KPITitleLabels;
    }

    public HashMap<String, Label> getKPIValueLabels() {
        return KPIValueLabels;
    }

    // *****************************************************************************************************************
    // Add Event Handlers
    // *****************************************************************************************************************

    public void addEventHandlerToRegionButtons(EventHandler<ActionEvent> event){

        List<String> buttonKeys = getButtonKeys();

        for (String buttonKey : regionButtonKeys) {
            regionButtons.get(buttonKey).setOnAction(event);
        }
    }




}
