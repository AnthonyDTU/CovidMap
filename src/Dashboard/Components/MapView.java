package Dashboard.Components;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import java.util.HashMap;
import java.util.List;

public class MapView {

    VBox mainLayout;
    ImageView imageView;
    List<String> regionButtonKeys;
    HashMap<String, Button> regionButtons;
    Label KPIHeaderLabel;
    List<String> KPIFieldKeys;
    HashMap<String, KPIField> KPIFields;


    public MapView(VBox mainLayout, ImageView imageView, List<String> regionButtonKeys, HashMap<String, Button> regionButtons, Label KPIHeaderLabel, List<String> KPIFieldKeys, HashMap<String, KPIField> KPIFields){
        this.mainLayout = mainLayout;
        this.imageView = imageView;
        this.regionButtonKeys = regionButtonKeys;
        this.regionButtons = regionButtons;
        this.KPIHeaderLabel = KPIHeaderLabel;
        this.KPIFieldKeys = KPIFieldKeys;
        this.KPIFields = KPIFields;
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

    public List<String> getKPIFieldKeys() {
        return KPIFieldKeys;
    }

    public HashMap<String, KPIField> getKPIFields() {
        return  KPIFields;
    }

    public void setKPIFieldValue(String key, String text){
        KPIFields.get(key).setPrimaryValue(text);
    }

    public void setKPIHeaderLabel(String text){
        KPIHeaderLabel.setText(text);
    }


    // *****************************************************************************************************************
    // Add Event Handlers
    // *****************************************************************************************************************

    public void addEventHandlerToRegionButtons(EventHandler<ActionEvent> eventHandler)
    {
        for (String buttonKey : regionButtonKeys) {
            regionButtons.get(buttonKey).setOnAction(eventHandler);
        }
    }

    public void addEventHandlerToImageVIew(EventHandler<MouseEvent> event)
    {
        imageView.setOnMouseClicked(event);
    }
}
