package chat;/**
 * Created by Leona on 05.02.2017.
 */


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ChatApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        GridPane gp = new GridPane();
        Label l1 = new Label("Chat");
        HBox h1 = new HBox();
        HBox h2 = new HBox();
        
        gp.add(l1,1,1);
        gp.add(h1,1,2);
        gp.add(h2,1,3);


        Scene sc = new Scene(gp,300,300);

        primaryStage.setScene(sc);
        primaryStage.setTitle("ChatApp");
        primaryStage.show();
    }
}
