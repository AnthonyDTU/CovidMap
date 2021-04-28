package Dashboard.View;

import Dashboard.Controller.DashboardController;
import Dashboard.Model.DashboardModel;
import Dashboard.View.Components.DataView;
import Dashboard.View.Components.HeaderBar;
import Dashboard.View.Components.MapView;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.layout.*;

import java.util.ArrayList;
import java.util.List;

public class DashboardView {

    DashboardController controller;
    DashboardModel model;

    final CategoryAxis xAxis = new CategoryAxis();
    final NumberAxis yAxis = new NumberAxis();


    private List<BarChart<String, Number>> charts = new ArrayList<BarChart<String, Number>>();


    private GridPane view ;

    public DashboardView(){

    }


    public void setMapView(MapView mapView){
        this.mapView = mapView;
    }


    public MapView getMapView() {
        return mapView;
    }


    public DataView getDataView(){
        return dataView;
    }

    HeaderBar headerView;
    DataView dataView;
    MapView mapView;
    VBox root;


    public Parent asParent() {

        //HeaderBarInitializer headerBarInitializer = new HeaderBarInitializer();
        //DataViewInitializer dataViewInitializer = new DataViewInitializer();


        //headerView = headerBarInitializer.CreateHeaderBar();
        //dataView = dataViewInitializer.CreateDataView();

        // StackPane holder = new StackPane();
        // Canvas canvas = new Canvas(1600,  900);
        // holder.getChildren().add(canvas);
        // root.getChildren().add(holder);
        root = mapView.getMainLayout();

        //Button button = new Button("Press");

        //root.getChildren().add(button);
        root.setAlignment(Pos.CENTER);
        root.setLayoutY(300);


        return root ;
    }


    private VBox CreateCompleteUI(MapView mapView)
    {
        GridPane mainGridPane = new GridPane();

        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(40);
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPercentWidth(60);
        mainGridPane.getColumnConstraints().addAll(column1, column2);

        //mainGridPane.add(dataView.getMainLayout(), 0, 0);
        mainGridPane.add(mapView.getMainLayout(), 0, 0);
        mainGridPane.getChildren().add(mapView.getMainLayout());

        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(mainGridPane);
        root.setBackground(Background.EMPTY);

        return root;
    }






}
