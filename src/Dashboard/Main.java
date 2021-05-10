package Dashboard;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        // MVC Architecture tutorial, without fxml: https://stackoverflow.com/questions/36868391/using-javafx-controller-without-fxml
        // And then one more (the actual format) here: https://www.youtube.com/watch?v=dTVVa2gfht8&ab_channel=DerekBanas
        DashboardModel model = new DashboardModel();
        DashboardView view = new DashboardView();
        DashboardController controller = new DashboardController(model, view);

        Scene scene = new Scene(view.asParent(), 1600, 900);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
