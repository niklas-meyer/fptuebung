import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.ModelShop;
import view.*;
import controller.*;
public class Main extends Application {
	
	/**
	 * Created by Surya on 01.11.2016.
	 */
	
	
    public static void main(String[] args) {

        Application.launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
    	//model für beide 
    	
        ModelShop model = new ModelShop();

        //shop view und controler verlinken
        ViewShop view = new ViewShop();
        ControllerShop controller = new ControllerShop();
        controller.link(model, view);
        
        Scene scene = new Scene(view);
        primaryStage.setTitle("Top Shop");
        primaryStage.setScene(scene);
        
        //customer erstellen 
        ViewCustomer view2 = new ViewCustomer();
        ControllerCustomer controller2 = new ControllerCustomer();
        controller2.link(model, view2);
        Stage stage2 = new Stage();
        stage2.setTitle("Customer Übersicht");
        Scene scene2 = new Scene(view2);
        stage2.setScene(scene2);
        stage2.setX(primaryStage.getX() - stage2.getWidth());
        stage2.setY(primaryStage.getY() - stage2.getHeight());
        stage2.show();


        primaryStage.show();
    }

}
