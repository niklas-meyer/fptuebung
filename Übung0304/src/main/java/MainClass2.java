import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.ViewCustomer;
import controller.*;
import model.*;
/**
 * Created by Surya on 18.11.2016.
 */

public class MainClass2 extends Application{

    public static void main(String[] args) {

        Application.launch(args);
    }

    //@Override
    public void start(Stage secondaryStage) throws Exception {


        ModelShop model = new ModelShop();

        ViewCustomer view2 = new ViewCustomer();
        ControllerCustomer controller2 = new ControllerCustomer();
        controller2.link(model, view2);
        Stage stage2 = new Stage();
        stage2.setTitle("Customer Übersicht");
        Scene scene2 = new Scene(view2);
        stage2.setScene(scene2);
        stage2.setX(secondaryStage.getX() - stage2.getWidth());
        stage2.setY(secondaryStage.getY() - stage2.getHeight());
        stage2.show();
        secondaryStage.show();
    }

}
