package Dashboard.Components;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.util.HashMap;
import java.util.List;

public class HeaderView {

    HBox mainLayout;
    Button menuButton;
    List<String> KPILabelKeys;
    HashMap<String, Label> KPITitleLabels;
    HashMap<String, Label> KPIValueLabels;

    public HeaderView(HBox mainLayout, Button menuButton, List<String> KPILabelKeys, HashMap<String, Label> KPITitleLabels, HashMap<String, Label> KPIValueLabels){
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
