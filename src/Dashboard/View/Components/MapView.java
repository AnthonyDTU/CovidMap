package Dashboard.View.Components;

import javafx.scene.chart.Chart;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.util.HashMap;
import java.util.List;

public class MapView {

    VBox mainLayout;
    ImageView imageView;
    List<String> buttonKeys;
    HashMap<String, Button> buttons;

    public MapView(VBox mainLayout, ImageView imageView, List<String> buttonKeys, HashMap<String, Button> buttons){
        this.mainLayout = mainLayout;
        this.imageView = imageView;
        this.buttonKeys = buttonKeys;
        this.buttons = buttons;
    }

    public VBox getMainLayout() {
        return mainLayout;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public List<String> getButtonKeys() {
        return buttonKeys;
    }

    public HashMap<String, Button> getButtons() {
        return buttons;
    }
}
