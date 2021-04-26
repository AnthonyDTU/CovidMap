package Dashboard.View.ComponentIntializers;

import Dashboard.View.Components.MapView;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MapViewInitializer {

    private String[] regions = new String[]{"Region Nordjylland",
                                            "Region Midtjylland",
                                            "Region Syddanmark",
                                            "Region Sjælland",
                                            "Region Hovedstaden"};

    public MapView CreateMapView() {
        // ----------------------------------------
        List<String> buttonKeys = new ArrayList<>();
        HashMap<String, Button> regionButtons = new HashMap<>();

        HBox mapHeader = new HBox();
        mapHeader.setMinHeight(75);

        ImageView mapImageView = new ImageView();

        try {
            mapImageView.setImage(new Image(new FileInputStream("Regionskort.jpg")));
            mapImageView.setPreserveRatio(true);
            mapImageView.setFitHeight(750);
        }
        catch (FileNotFoundException e){

        }

        VBox layout = new VBox(10);

        layout.getChildren().addAll(mapHeader, mapImageView);
        layout.setAlignment(Pos.CENTER);

        for (String region : regions)
        {
            Button regionButton = new Button();
            buttonKeys.add(region);
            regionButtons.put(region, regionButton);

        }
        return new MapView(layout, mapImageView, buttonKeys, regionButtons);
    }
}
