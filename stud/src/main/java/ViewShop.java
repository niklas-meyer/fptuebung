import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 * Created by NiklasM on 09.11.16.
 */
public class ViewShop extends GridPane {


    private ListView<Product> lv = new ListView<>();
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

        lv.getSelectionModel().selectedItemProperty().addListener(
                (ov, old_val, new_val) -> {
                    selectedProductIndex = lv.getSelectionModel().getSelectedIndex();
                });
        this.add(lv, 1, 1);
        VBox v = new VBox();
        this.add(v, 2, 1);
        v.getChildren().addAll(l1, t1, l2, t2, l3, t3, addButton, deleteButton);
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
