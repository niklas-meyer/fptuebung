import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import javax.swing.*;

/**
 * Created by NiklasM on 09.11.16.
 */
public class ViewShop extends GridPane {


    private ListView<Product> lv = new ListView<>();
    private Button loadB = new Button("Load");
    private HBox topBox = new HBox();
    private HBox topBox2 = new HBox();
    private Button saveB = new Button("Save");
    private Label l1 = new Label("Name");
    private Label l2 = new Label("Price");
    private Label l3 = new Label("Count");
    private Label l4 = new Label("Product List");
    private TextField nameTextField = new TextField();
    private TextField priceTextField = new TextField();
    private TextField countTextField = new TextField();

    private SimpleStringProperty nameText = new SimpleStringProperty("");
    private SimpleStringProperty priceText = new SimpleStringProperty("");
    private SimpleStringProperty countText = new SimpleStringProperty("");

    public Button addButton = new Button("Add");
    public Button deleteButton = new Button("Delete");
    public int selectedProductIndex = 0;
    public Product selectedProduct = null;

    ObservableList<String> options = FXCollections.observableArrayList(
            "Binary",
            "Beans",
            "XStream"
    );

    private ComboBox comboBox = new ComboBox(options);

    public void bindData (ModelShop model) {

        lv.setItems(model.products);
    }

    public ViewShop() {

        lv.setCellFactory(e -> {
            ListCell<Product> cell = new ListCell<Product>() {
                @Override protected void updateItem(Product myObject, boolean b) {
                    super.updateItem(myObject, myObject == null || b);
                    if (myObject != null) {
                        setText(myObject.getName() + " ( " + myObject.getPrice()+ " €  , " + myObject.getQuantity()+" Stück)");
                    } else {
                        setText("");
                    }
                }
            };
            return cell;
        });
        l4.setStyle("-fx-font-size:16pt;");
        l4.setPadding(new Insets(10,10,10,10));
        lv.getSelectionModel().selectedItemProperty().addListener(
                (ov, old_val, new_val) -> selectedProduct = lv.getSelectionModel().getSelectedItem());
        topBox2.getChildren().add(l4);
        topBox.getChildren().addAll(comboBox, loadB, saveB);
        topBox.setAlignment(Pos.CENTER);
        topBox2.setAlignment(Pos.CENTER);
        this.add(topBox2, 1, 1);
        this.add(topBox, 2, 1);
        this.add(lv, 1, 2);
        VBox v = new VBox();
        this.add(v, 2, 2);
        v.getChildren().addAll(l1, nameTextField,
                                l2, priceTextField,
                                l3, countTextField,
                                addButton, deleteButton);

        addButton.setStyle("-fx-background-color: #c3c4c4, linear-gradient(#d6d6d6 50%, lightgreen 100%), radial-gradient(center 50% -40%, radius 200%, #e6e6e6 45%, rgba(230,230,230,0) 50%); -fx-background-radius: 30; -fx-background-insets: 0,1,1;  -fx-text-fill: black; -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 3, 0.0 , 0 , 1 );");
        addButton.setMinSize(200, 30);
        deleteButton.setMinSize(200, 30);
        deleteButton.setStyle("-fx-background-color: #c3c4c4, linear-gradient(#d6d6d6 50%, lightcoral 100%), radial-gradient(center 50% -40%, radius 200%, #e6e6e6 45%, rgba(230,230,230,0) 50%); -fx-background-radius: 30; -fx-background-insets: 0,1,1;  -fx-text-fill: black; -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 3, 0.0 , 0 , 1 );");
        v.setSpacing(5.0);
        v.setStyle("-fx-background-color: lightgrey;");
        l1.setStyle("-fx-font-family: Avenir Ultra Light; -fx-font-size: 16;");
        l1.setPadding(new Insets(0,0,0,80));
        l2.setStyle("-fx-font-family: Avenir Ultra Light; -fx-font-size: 16;");
        l2.setPadding(new Insets(0,0,0,83));
        l3.setStyle("-fx-font-family: Avenir Ultra Light; -fx-font-size: 16;");
        l3.setPadding(new Insets(0,0,0,80));

        // input field bindings
        nameTextField.textProperty().bindBidirectional(nameText);
        priceTextField.textProperty().bindBidirectional(priceText);
        countTextField.textProperty().bindBidirectional(countText);
    }

    public String getProductName(){
        return nameText.get();
    }

    public String getProductPrice(){
        return priceText.get();
    }

    public String getProductCount(){
        return countText.get();
    }

    public void addEventHandler(EventHandler<ActionEvent> eventHandler) {
        addButton.addEventHandler(ActionEvent.ACTION, eventHandler);
        deleteButton.addEventHandler(ActionEvent.ACTION, eventHandler);
    }
}
