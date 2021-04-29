package Dashboard.Controller.ComponentIntializers;

import Dashboard.View.Components.HeaderView;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HeaderViewInitializer {

    HeaderView headerBar;
    HBox mainLayout = new HBox(10);
    Button menuButton = new Button();
    List<String> KPILabelKeys = new ArrayList<>();
    HashMap<String, javafx.scene.control.Label> KPITitleLabels = new HashMap<>();
    HashMap<String, Label> KPIValueLabels = new HashMap<>();

    public HeaderViewInitializer(){
        headerBar = new HeaderView(mainLayout, menuButton, KPILabelKeys, KPITitleLabels, KPIValueLabels);
    }

    public HeaderView CreateHeaderView(){


        return null;
    }

    private Button CreateMenuButton(){

        menuButton.setMaxSize(75, 75);
        menuButton.setMinSize(75, 75);
        menuButton.setPrefSize(75, 75);
        menuButton.setStyle(new Color(100,100,100).toString());
        menuButton.setOnAction(actionEvent -> headerBar.menuButtonPressed());
        return null;
    }

    private HBox CreateKPIFields(){


        return null;
    }
}
