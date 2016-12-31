package view;

import model.Product;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;

public class ViewShop extends BorderPane {



    private ChoiceBox<String> strategy = new ChoiceBox<String>();
    private Button buttonLA = new Button("Load...");
    private Button buttonSA = new Button("Save");


    private ListView<Product> liste = new ListView<Product>();


    private Label nameLa = new Label("Name:");
    private TextField name = new TextField();
    private Label priceLa = new Label("Price:");
    private TextField price = new TextField();
    private Label countLa = new Label("Count:");
    private TextField count = new TextField();


    private Button button = new Button("Add");
    private Button button2 = new Button("Delete");






    public ViewShop() {
        Pane box = new Pane();
        box.setPrefSize(200, 400);

        nameLa.setLayoutX(18);
        nameLa.setLayoutY(15);
        priceLa.setLayoutX(18);
        priceLa.setLayoutY(55);
        countLa.setLayoutX(18);
        countLa.setLayoutY(95);

        name.setLayoutX(18);
        name.setLayoutY(30);
        price.setLayoutX(18);
        price.setLayoutY(70);
        count.setLayoutX(18);
        count.setLayoutY(110);

        button.setLayoutX(14);
        button.setLayoutY(150);
        button2.setLayoutX(125);
        button2.setLayoutY(150);

        box.getChildren().addAll(name, price, count, nameLa, priceLa, countLa, button, button2);
        setRight(box);
        setCenter(liste);

        strategy.setItems(FXCollections.observableArrayList("Binary Strategie",
                                                            "Beans XML Strategie",
                                                            "XStream XML Strategie",
                                                            "OpenJPA",
                                                            "JDBC"));
        strategy.getSelectionModel().select(0);
        HBox hbox = new HBox();
        hbox.setSpacing(5);
        hbox.setPadding(new Insets(10, 10, 10, 10));
        hbox.getChildren().addAll(strategy, buttonLA, buttonSA);
        setTop(hbox);
        liste.setCellFactory(c -> {
            ListCell<Product> cell = new ListCell<Product>() {
                @Override
                protected void updateItem(Product myObject, boolean b) {
                    super.updateItem(myObject, myObject == null || b);
                    if (myObject != null) {
                        setText(myObject.toString());
                    } else {
                        setText("");
                    }
                }

            };
            return cell;
        });

    }

    public void addEventHandler(EventHandler<ActionEvent> eventHandler) {
        button.addEventHandler(ActionEvent.ACTION, eventHandler);
        button2.addEventHandler(ActionEvent.ACTION, eventHandler);
        buttonLA.addEventHandler(ActionEvent.ACTION, eventHandler);
        buttonSA.addEventHandler(ActionEvent.ACTION, eventHandler);
        strategy.addEventHandler(ActionEvent.ACTION, eventHandler);
    }

    public ListView<Product> getList() {
        return liste;
    }

    public void setList(ObservableList<Product> list) {
        this.liste.setItems(list);
    }

    public String getNameInput() {
        return name.getText();
    }

    public String getPriceInput() {
        return price.getText();
    }

    public String getCountInput() {
        return count.getText();
    }

    public int getSelectedIndex() {
        return liste.getSelectionModel().getSelectedIndex();
    }

    public int getChoice() {
        return strategy.getSelectionModel().getSelectedIndex();
    }

}
