package Dashboard.View.ComponentIntializers;

import Dashboard.View.Components.HeaderBar;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HeaderBarInitializer {

    HeaderBar headerBar;
    HBox mainLayout = new HBox(10);
    Button menuButton = new Button();
    List<String> KPILabelKeys = new ArrayList<>();
    HashMap<String, javafx.scene.control.Label> KPITitleLabels = new HashMap<>();
    HashMap<String, Label> KPIValueLabels = new HashMap<>();

    public HeaderBarInitializer(){
        headerBar = new HeaderBar(mainLayout, menuButton, KPILabelKeys, KPITitleLabels, KPIValueLabels);
    }

    public HeaderBar CreateHeaderBar(){


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
