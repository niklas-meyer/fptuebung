package view;

import controller.ControllerCustomer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javax.swing.text.View;

/**
 * Created by Henry on 05.02.2017.
 */
public

class PasswordDialogue extends Stage {
    ViewCustomer viewCustomer;
    ControllerCustomer controllerCustomer;

    public PasswordDialogue(ViewCustomer viewCustomer, ControllerCustomer controllerCustomer) {
        super();
        this.viewCustomer = viewCustomer;
        this.controllerCustomer = controllerCustomer;
        setTitle("Authentification");
        Group root = new Group();
        Scene scene = new Scene(root, 250, 150, Color.WHITE);
        setScene(scene);

        GridPane gridpane = new GridPane();
        gridpane.setPadding(new Insets(5));
        gridpane.setHgap(5);
        gridpane.setVgap(5);

        Label userName = new Label("User Name: ");
        gridpane.add(userName, 0, 1);

        Label password = new Label("Password: ");
        gridpane.add(password, 0, 2);
        final TextField userNameInput = new TextField("");
        gridpane.add(userNameInput, 1, 1);

        final PasswordField passwordInput = new PasswordField();
        passwordInput.setText("");
        gridpane.add(passwordInput, 1, 2);

        Button login = new Button("Login");


        login.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                controllerCustomer.sendLoginMessage(userNameInput.getText(), passwordInput.getText());

                //viewCustomer.sendMessageToServer(msg);
                close();
            }
        });

        gridpane.add(login, 1, 3);
        GridPane.setHalignment(login, HPos.RIGHT);
        root.getChildren().add(gridpane);
    }

}
