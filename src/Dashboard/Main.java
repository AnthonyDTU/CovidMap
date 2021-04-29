package Dashboard;

import Dashboard.Controller.DashboardController;
import Dashboard.Model.DashboardModel;
import Dashboard.View.DashboardView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        // MVC Arcitechture toturial, without fxml: https://stackoverflow.com/questions/36868391/using-javafx-controller-without-fxml
        // And then one more (the acutal format) here: https://www.youtube.com/watch?v=dTVVa2gfht8&ab_channel=DerekBanas
        DashboardModel model = new DashboardModel("C:\\Users\\Anton\\Desktop\\DTU\\IT Elektronik\\2. Semester\\Objekt Orienteret\\Semester Project\\Covid Data\\");
        DashboardView view = new DashboardView();
        DashboardController controller = new DashboardController(model, view);


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
