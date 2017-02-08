import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
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
        ViewShop view = new ViewShop();
        ControllerShop controller = new ControllerShop();
        controller.link(model, view);

        Scene scene = new Scene(view);
        primaryStage.setTitle("Top Shop");
        primaryStage.setScene(scene);

        primaryStage.show();


        
        //customer erstellen
        Stage stage2 = new Stage();
        ControllerCustomer controller2 = new ControllerCustomer();
        ViewCustomer view2 = new ViewCustomer(controller2);
        controller2.link(model, view2);

        stage2.setOnHiding(event -> Platform.runLater(() -> {
            view2.onWindowClose();
            System.exit(0);
        }));


        stage2.setTitle("Customer Übersicht");






        Scene scene2 = new Scene(view2);

        stage2.setScene(scene2);
        stage2.setX(primaryStage.getX() - stage2.getWidth());
        stage2.setY(primaryStage.getY() - stage2.getHeight());
        stage2.show();



    }

}
