package Dashboard.Components;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


public class KPIField {

    private VBox layout;
    private Label titleLabel;
    private Label valueLabel;

    // Creates and configures the nessesary components
    //
    public KPIField(String title, String value)
    {
        titleLabel = new Label(title);
        valueLabel = new Label(value);

        titleLabel.setFont(Font.font("Arial", 15));
        valueLabel.setFont(Font.font("Arial", FontWeight.BOLD, 28));

        layout = new VBox(0);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(titleLabel, valueLabel);


    }

    // Get and set the text displayed by the value label
    public void setValueLabelText(String value){
        valueLabel.setText(value);
    }
    public String getValueLabelText() { return valueLabel.getText(); }

    // Gets the layout of the KPIField
    //
    public VBox getLayout(){
        return layout;
    }
}
