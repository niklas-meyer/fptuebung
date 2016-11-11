import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by NiklasM on 11.11.16.
 */
public class MainClass extends Application{

    public static void main(String[] args) {Application.launch();}

    public void start(Stage primaryStage) throws Exception {

        ModelShop model = new ModelShop();
        ViewShop view = new ViewShop();
        ControllerShop controller = new ControllerShop();
        controller.link(model, view);
        Scene scene  = new Scene(view);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
