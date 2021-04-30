package Dashboard.View.Components;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class KPIField {

    VBox layout;
    Label primaryTitleLabel;
    Label primaryValueLabel;
    Label secondaryTitleLabel;
    Label secondaryValueLabel;

    public KPIField(){
    }

    public KPIField(String title, String value){
        primaryTitleLabel = new Label(title);
        primaryValueLabel = new Label(value);

        layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(primaryTitleLabel, primaryValueLabel);
    }

    public KPIField(String primaryTitle, String primaryValue, String secondaryTitletitleTwo, String secondaryValue){
        primaryTitleLabel = new Label(primaryTitle);
        primaryValueLabel = new Label(primaryValue);

        secondaryTitleLabel = new Label(secondaryTitletitleTwo);
        secondaryValueLabel = new Label(secondaryValue);


        layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(primaryTitleLabel, primaryValueLabel, secondaryTitleLabel, secondaryValueLabel);
    }

    public void setPrimaryTitle(String title){
        primaryTitleLabel.setText(title);
    }

    public void setPrimaryValue(String value){
        primaryValueLabel.setText(value);
    }

    public void setSecondaryTitle(String title){
        secondaryTitleLabel.setText(title);
    }

    public void setSecondaryValueLabel(String value){
        secondaryValueLabel.setText(value);
    }

    public VBox getLayout(){
        return layout;
    }
}
