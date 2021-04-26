package Dashboard;

import Dashboard.Controller.DashboardController;
import Dashboard.Model.DashboardModel;
import Dashboard.View.DashboardView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        // MVC Arcitechture toturial, without fxml: https://stackoverflow.com/questions/36868391/using-javafx-controller-without-fxml
        DashboardModel model = new DashboardModel("folderPath");
        DashboardController controller = new DashboardController(model);
        DashboardView view = new DashboardView(controller, model);

        Scene scene = new Scene(view.asParent(), 1600, 900);
        scene.setFill(Color.WHITE);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
