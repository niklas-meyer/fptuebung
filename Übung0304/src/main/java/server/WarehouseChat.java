package server;

import chat.ChatClient;
import chat.ClientService;
import chat.StartServer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

import java.rmi.RemoteException;

/**
 * Created by Henry on 09.02.2017.
 */
public class WarehouseChat extends Application  {

    ClientService chatClient;

    public static void main(String[] args) {
        launch(args);

    }

    @Override
    public void start(Stage primaryStage) {

        StartServer.startRegistry();
        GridPane gp = new GridPane();
        Scene sc = new Scene(gp,300,300);
        TextArea chatText = new TextArea("Herzlichen Willkommen");
        chatText.setEditable(false);

        chatText.setMinSize(300,100);
        HBox h1 = new HBox();
        HBox h2 = new HBox();
        TextArea inputText = new TextArea();
        inputText.setPrefSize(300, 50);
        Button enterButton = new Button("Enter");
        h1.setHgrow(enterButton, Priority.ALWAYS);
        h2.setHgrow(inputText, Priority.ALWAYS);

        h1.getChildren().addAll(inputText);
        h2.getChildren().addAll(enterButton);
        gp.add(chatText,1,1);
        gp.add(h1,1,2);
        gp.add(h2,1,3);

        if(chatClient == null){
            try {
                chatClient = new ChatClient("Warehouse-Admin", chatText);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        enterButton.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                if(chatClient == null){
                    try {
                        if(inputText.getText() != null){
                            chatClient = new ChatClient(inputText.getText(), chatText);
                        } else {
                            chatClient = new ChatClient("Unknown", chatText);
                        }
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                } else if (inputText.getText() != null) {
                    try {
                        chatClient.send(inputText.getText());
                    } catch (RemoteException e){
                        e.printStackTrace();
                    }
                }
                inputText.setText("");
            }
        });



        primaryStage.setScene(sc);
        primaryStage.setTitle("Chat mit Kunden");
        primaryStage.show();

        primaryStage.setOnHiding(event -> Platform.runLater(() -> {
            if(chatClient != null){
                try {
                    chatClient.disconnect();
                } catch (RemoteException e){
                    e.printStackTrace();
                }

            }
            System.exit(0);
        }));
    }
}
