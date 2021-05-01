package Dashboard.View.Components;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class KPIField {

    VBox layout;
    Label primaryTitleLabel;
    Label primaryValueLabel;
    Label secondaryTitleLabel;
    Label secondaryValueLabel;

    public KPIField(){
    }

    public KPIField(String title, String value)
    {
        primaryTitleLabel = new Label(title);
        primaryValueLabel = new Label(value);

        primaryTitleLabel.setFont(Font.font("Arial", 15));
        primaryValueLabel.setFont(Font.font("Arial", FontWeight.BOLD, 28));

        layout = new VBox(0);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(primaryTitleLabel, primaryValueLabel);
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
