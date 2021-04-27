package Dashboard.View.Components;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HeaderBar {

    HBox mainLayout;
    Button menuButton;
    List<String> KPILabelKeys;
    HashMap<String, Label> KPITitleLabels;
    HashMap<String, Label> KPIValueLabels;

    public HeaderBar(HBox mainLayout, Button menuButton, List<String> KPILabelKeys, HashMap<String, Label> KPITitleLabels, HashMap<String, Label> KPIValueLabels){
        this.mainLayout = mainLayout;
        this.menuButton = menuButton;
        this.KPILabelKeys = KPILabelKeys;
        this.KPITitleLabels = KPITitleLabels;
        this.KPIValueLabels = KPIValueLabels;
    }

    public HBox getMainLayout() {
        return mainLayout;
    }

    public Button getMenuButton() {
        return menuButton;
    }

    public List<String> getKPILabelKeys() {
        return KPILabelKeys;
    }

    public HashMap<String, Label> getKPITitleLabels() {
        return KPITitleLabels;
    }

    public HashMap<String, Label> getKPIValueLabels() {
        return KPIValueLabels;
    }

    // *****************************************************************************************************************
    // Event Handlers
    // *****************************************************************************************************************

    public void menuButtonPressed(){

    }
}
