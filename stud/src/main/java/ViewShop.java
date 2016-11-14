import fpt.com.*;
import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
 * Created by NiklasM on 09.11.16.
 */
public class ViewShop extends GridPane {


    private ListView<String> lv = new ListView<>();
    private Label l1 = new Label("Name");
    private Label l2 = new Label("Price");
    private Label l3 = new Label("Count");
    private TextField t1 = new TextField();
    private TextField t2 = new TextField();
    private TextField t3 = new TextField();
    private ObservableList<String> list = FXCollections.observableArrayList();
    public Button addButton = new Button("Add");
    public Button deleteButton = new Button("Delete");
    public int selectedProductIndex = 0;

    public void bindData (ModelShop model) {
        lv.setItems(model.productNames);
    }

    public ViewShop() {


        lv.getSelectionModel().selectedItemProperty().addListener(
                (ov, old_val, new_val) -> {
                    selectedProductIndex = lv.getSelectionModel().getSelectedIndex();
                });
        this.add(lv, 1, 1);
        VBox v = new VBox();
        this.add(v, 2, 1);
        v.getChildren().addAll(l1, t1, l2, t2, l3, t3, addButton, deleteButton);
        addButton.setStyle("-fx-background-color: #00AA22;");
        addButton.setMinSize(200, 30);
        deleteButton.setStyle("-fx-background-color: #AA2200");
        deleteButton.setMinSize(200, 30);
        v.setSpacing(5.0);
        v.setStyle("-fx-background-color: #AAAAAA;");
        l1.setStyle("-fx-font-family: Avenir Ultra Light; -fx-font-size: 16;");
        l1.setPadding(new Insets(0,0,0,80));
        l2.setStyle("-fx-font-family: Avenir Ultra Light; -fx-font-size: 16;");
        l2.setPadding(new Insets(0,0,0,83));
        l3.setStyle("-fx-font-family: Avenir Ultra Light; -fx-font-size: 16;");
        l3.setPadding(new Insets(0,0,0,80));


    }



    public Product getProduct() {
        Product p = new Product();
        p.setName(t1.getText());
        if(!t2.getText().isEmpty()){
            try {
                p.setPrice(Double.parseDouble(t2.getText()));
            } catch (final NumberFormatException ex) {
                System.out.print("Feld 2 enthält keine Zahl");
            }
        }
        if(!t3.getText().isEmpty()) {
            try {
                p.setQuantity(Integer.parseInt(t3.getText()));
            } catch (final NumberFormatException ex) {
                System.out.print("Feld 3 enthält keine Zahl");
            }

        }
        return p;
    }

    public void addEventHandler(EventHandler<ActionEvent> eventHandler) {
        addButton.addEventHandler(ActionEvent.ACTION, eventHandler);
        deleteButton.addEventHandler(ActionEvent.ACTION, eventHandler);
    }
}
