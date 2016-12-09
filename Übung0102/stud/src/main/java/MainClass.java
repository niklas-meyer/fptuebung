import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by NiklasM on 11.11.16.
 */
public class MainClass extends Application{

    public static void main(String[] args) {Application.launch();}

    public void start(Stage primaryStage) throws Exception {

        //Product View
        ModelShop model = new ModelShop();
        ViewShop view = new ViewShop();
        ControllerShop controller = new ControllerShop();
        controller.link(model, view);
        Scene scene  = new Scene(view);

        //Order-View
        Stage secondStage = new Stage();

        Order order = new Order();
        ViewCustomer viewC = new ViewCustomer();
        ControllerCustomer controller2 = new ControllerCustomer();
        controller2.link(order, viewC);
        Scene scene2  = new Scene(viewC);

        //General
        primaryStage.setScene(scene);
        secondStage.setScene(scene2);
        primaryStage.show();
        secondStage.show();
    }
}
