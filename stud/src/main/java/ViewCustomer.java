/**
 * Created by Leona on 14.11.2016.
 */

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import javax.swing.*;

public class ViewCustomer extends BorderPane{

    private VBox v1 = new VBox();
    private VBox v2 = new VBox();
    private HBox h1 = new HBox();
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

        this.setRight(v2);
        this.setLeft(v1);
        this.setBottom(h1);
        h1.setAlignment(Pos.CENTER_RIGHT);
        buyButton.setMinSize(200,30);
    }
}
