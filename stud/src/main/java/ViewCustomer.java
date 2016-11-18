/**
 * Created by Leona on 14.11.2016.
 */

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import javax.swing.*;

public class ViewCustomer extends BorderPane{
    private HBox order= new HBox();
    private VBox v1 = new VBox();
    private VBox v2 = new VBox();
    private HBox h1 = new HBox();
    private Label l1 = new Label("Your Orders");
    private Button buyButton = new Button("Buy");
    private TableView tv = new TableView();
    private ListView lv = new ListView();

    public void bindData (Order o) {
        tv.setItems(o.orderedProducts);
    }

    public ViewCustomer(){

        TableColumn t1 = new TableColumn("Name");
        TableColumn t2 = new TableColumn("Price");
        TableColumn t3 = new TableColumn("MaxCount");
        TableColumn t4 = new TableColumn("OrderCount");

        t1.setCellValueFactory(
                new PropertyValueFactory<Product,String>("Name")
        );
        t2.setCellValueFactory(
                new PropertyValueFactory<Product,String>("Price")
        );
        t4.setCellValueFactory(
                new PropertyValueFactory<Product,String>("OrderCount")
        );

        tv.getColumns().addAll(t1,t2,t3,t4);
        lv.setStyle("min-width:100px");
        v1.getChildren().add(lv);
        v2.getChildren().add(tv);
        h1.getChildren().add(buyButton);
        order.getChildren().add(l1);
        h1.setMargin(buyButton, new Insets(20,0,20,0));
        order.setStyle("-fx-background-color: #55AA00, linear-gradient(#55AA00 50%, white 100%), radial-gradient(center 50% -40%, radius 200%, #e6e6e6 45%, rgba(230,230,230,0) 50%);");
        order.setMargin(l1, new Insets(20,50,20,50));
        order.setAlignment(Pos.CENTER);
        l1.setStyle("-fx-font-family: Avenir Ultra Light; -fx-font-size: 28; ");

        this.setRight(v2);
        this.setLeft(v1);
        this.setBottom(h1);
        this.setTop(order);
        h1.setAlignment(Pos.CENTER);
        buyButton.setMinSize(200,30);
        buyButton.setStyle("-fx-background-color: #c3c4c4, linear-gradient(#d6d6d6 50%, white 100%), radial-gradient(center 50% -40%, radius 200%, #e6e6e6 45%, rgba(230,230,230,0) 50%); -fx-background-radius: 30; -fx-background-insets: 0,1,1;  -fx-text-fill: black; -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 3, 0.0 , 0 , 1 );");
    }
}
