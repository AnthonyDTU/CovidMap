package Dashboard.View.Components;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.util.HashMap;

public class HeaderBar {

    Button menuButton;
    HashMap<String, KPIField> kpiFields;
    HashMap<String, Label> kpiLabels;

    public HeaderBar(){



    }
}

class KPIField {

    VBox mainLayout;
    Label titleLabel;
    Label valueLabel;
    String title;
    String value;

    public KPIField(String title, String value){
        this.title = title;
        this.value = value;

        titleLabel.setText(title);
        valueLabel.setText(value);

        mainLayout.setSpacing(10);
        mainLayout.getChildren().add(titleLabel);
        mainLayout.getChildren().add(valueLabel);
    }
}