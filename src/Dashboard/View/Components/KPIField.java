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

    Font valueLabelFont = new Font("Arial", 20);

    public KPIField(){
    }

    private void setFont(){

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

    public KPIField(String primaryTitle, String primaryValue, String secondaryTitle, String secondaryValue)
    {
        primaryTitleLabel = new Label(primaryTitle);
        primaryValueLabel = new Label(primaryValue);

        primaryTitleLabel.setFont(Font.font("Arial", 15));
        primaryValueLabel.setFont(Font.font("Arial", FontWeight.BOLD, 28));

        GridPane.setConstraints(primaryTitleLabel, 0, 0);
        GridPane.setConstraints(primaryValueLabel, 0, 1);

        secondaryTitleLabel = new Label(secondaryTitle);
        secondaryValueLabel = new Label(secondaryValue);

        secondaryTitleLabel.setFont(Font.font("Arial", 15));
        secondaryValueLabel.setFont(Font.font("Arial", FontWeight.BOLD, 28));

        GridPane.setConstraints(secondaryTitleLabel, 1, 0);
        GridPane.setConstraints(secondaryValueLabel, 1, 1);

        GridPane grid = new GridPane();
        grid.getChildren().addAll(primaryTitleLabel, primaryValueLabel, secondaryTitleLabel, secondaryValueLabel);

        layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(grid);
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
