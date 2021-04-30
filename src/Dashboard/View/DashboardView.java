package Dashboard.View;

import Dashboard.Controller.DashboardController;
import Dashboard.Model.DashboardModel;
import Dashboard.View.Components.DataView;
import Dashboard.View.Components.HeaderView;
import Dashboard.View.Components.MapView;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.layout.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DashboardView {

    HeaderView headerView;
    DataView dataView;
    MapView mapView;
    VBox root;


    public DashboardView(){
    }

    public void setHeaderView(HeaderView headerView){
        this.headerView = headerView;
    }
    public HeaderView getHeaderView(){
        return headerView;
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
        GridPane mainGridPane = new GridPane();

        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(40);
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPercentWidth(60);
        mainGridPane.getColumnConstraints().addAll(column1, column2);


        GridPane.setConstraints(dataView.getMainLayout(),0,0);
        GridPane.setConstraints(mapView.getMainLayout(), 1, 0);
        mainGridPane.getChildren().addAll(dataView.getMainLayout(), mapView.getMainLayout());
        mainGridPane.setAlignment(Pos.CENTER);

        VBox root = new VBox(10);
        root.setAlignment(Pos.TOP_CENTER);
        root.getChildren().addAll(mainGridPane);
        root.setBackground(Background.EMPTY);

        return root;
    }
}
