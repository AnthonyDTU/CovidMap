package Dashboard.View.Components;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class KPIField {

    VBox layout;
    Label titleLabel;
    Label valueLabel;

    public KPIField(){
    }

    public KPIField(String title, String value){
        titleLabel = new Label(title);
        valueLabel = new Label(value);

        layout = new VBox(10);
        layout.getChildren().addAll(titleLabel, valueLabel);
    }

    public void setTitle(String title){
        titleLabel.setText(title);
    }

    public void setValue(String value){
        valueLabel.setText(value);
    }

    public VBox getLayout(){
        return layout;
    }
}
