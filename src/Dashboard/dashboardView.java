package Dashboard;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

public class dashboardView {

    dashboardController controller;
    dashboardModel model;



    private GridPane view ;

    public dashboardView(dashboardController controller, dashboardModel model){

        this.controller = controller;
        this.model = model;
    }

    public Parent asParent() {
        return view ;
    }
}
