package Dashboard.View;

import Dashboard.Controller.DashboardController;
import Dashboard.Model.DashboardModel;
import Dashboard.Model.DataFile;
import Dashboard.View.ComponentIntializers.DataViewInitializer;
import Dashboard.View.ComponentIntializers.HeaderBarInitializer;
import Dashboard.View.ComponentIntializers.MapViewInitializer;
import Dashboard.View.Components.DataView;
import Dashboard.View.Components.MapView;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class DashboardView {

    DashboardController controller;
    DashboardModel model;

    final CategoryAxis xAxis = new CategoryAxis();
    final NumberAxis yAxis = new NumberAxis();


    private List<BarChart<String, Number>> charts = new ArrayList<BarChart<String, Number>>();


    private GridPane view ;

    public DashboardView(DashboardController controller, DashboardModel model){

        this.controller = controller;
        this.model = model;
    }

    VBox headerView;
    DataView dataView;
    MapView mapView;
    VBox root;


    public Parent asParent() {

        HeaderBarInitializer headerBarInitializer = new HeaderBarInitializer();
        DataViewInitializer dataViewInitializer = new DataViewInitializer();
        MapViewInitializer mapViewInitializer = new MapViewInitializer();

        headerView = headerBarInitializer.CreateHeaderBar();
        dataView = dataViewInitializer.CreateDataView();


        mapView = mapViewInitializer.CreateMapView();
        root = CreateCompleteUI(dataView, mapView);


        StackPane holder = new StackPane();
        Canvas canvas = new Canvas(400,  300);

        holder.getChildren().add(canvas);
        root.getChildren().add(holder);


        return root ;
    }


    private VBox CreateCompleteUI(VBox dataView, VBox mapView)
    {
        GridPane mainGridPane = new GridPane();

        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(40);
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPercentWidth(60);
        mainGridPane.getColumnConstraints().addAll(column1, column2);

        mainGridPane.add(dataView, 0, 0);
        mainGridPane.add(mapView, 1, 0);


        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(mainGridPane);
        root.setBackground(Background.EMPTY);

        return root;
    }






}
