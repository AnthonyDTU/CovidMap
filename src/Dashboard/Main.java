package Dashboard;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        // MVC Arcitechture toturial, without fxml: https://stackoverflow.com/questions/36868391/using-javafx-controller-without-fxml
        dashboardModel model = new dashboardModel();
        dashboardController controller = new dashboardController(model);
        dashboardView view = new dashboardView(controller, model);

        Scene scene = new Scene(view.asParent(), 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
