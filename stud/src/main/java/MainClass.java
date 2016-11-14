import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by NiklasM on 11.11.16.
 */
public class MainClass extends Application{

    public static void main(String[] args) {Application.launch();}

    public void start(Stage primaryStage) throws Exception {
        Stage secondStage = new Stage();
        ModelShop model = new ModelShop();
        ViewShop view = new ViewShop();
        Order order = new Order();
        ViewCustomer viewC = new ViewCustomer();
        ControllerShop controller = new ControllerShop();
        ControllerCustomer controller2 = new ControllerCustomer();
        controller.link(model, view);
        controller2.link(order, viewC);
        Scene scene  = new Scene(view);
        Scene scene2  = new Scene(viewC);
        primaryStage.setScene(scene);
        secondStage.setScene(scene2);
        primaryStage.show();
        secondStage.show();
    }
}
