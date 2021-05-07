package Dashboard;

import Dashboard.Components.DataView;
import Dashboard.Components.MapView;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.layout.*;

public class DashboardView {

    DataView dataView;
    MapView mapView;
    VBox root;


    public DashboardView(){
    }

    public void setMapView(MapView mapView){
        this.mapView = mapView;
    }
    public MapView getMapView() {
        return mapView;
    }


    public void setDataView(DataView dataView){
        this.dataView = dataView;
    }
    public DataView getDataView() {
        return dataView;
    }


    public Parent asParent() {

        root = CreateCompleteUI(dataView, mapView);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: white;");
        return root;
    }


    private VBox CreateCompleteUI(DataView dataView, MapView mapView)
    {
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(55);
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPercentWidth(45);

        GridPane.setConstraints(dataView.getMainLayout(),1,0);
        GridPane.setConstraints(mapView.getMainLayout(), 0, 0);

        GridPane mainGridPane = new GridPane();
        mainGridPane.getColumnConstraints().addAll(column1, column2);
        mainGridPane.getChildren().addAll(dataView.getMainLayout(), mapView.getMainLayout());
        mainGridPane.setAlignment(Pos.CENTER);

        VBox root = new VBox(10);
        root.setAlignment(Pos.TOP_CENTER);
        root.getChildren().addAll(mainGridPane);
        root.setBackground(Background.EMPTY);

        return root;
    }
}
