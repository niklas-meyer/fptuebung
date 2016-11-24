import fpt.com.*;
//import fpt.com.Product;
import javafx.event.Event;
import javafx.event.EventHandler;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.function.Consumer;
import java.nio.file.Path;
import java.nio.file.Paths;
/**


/**
 * Created by NiklasM on 09.11.16.
 */
public class ControllerShop implements EventHandler {

    ModelShop model;
    ViewShop view;
    ProductList loadedProducts = new ProductList();
    ArrayList<SerializableStrategy> list = new ArrayList<>();
    SerializablePattern serializablePattern;

    public ControllerShop() {
        list.add(new BinaryStrategy());
        list.add(new XMLStrategy());
        list.add(new XStreamStrategy());

        // Standard: Binary strategy
        serializablePattern = new SerializablePattern(new BinaryStrategy());
    }


    public void link(ModelShop m, ViewShop v) {
        this.model = m;
        this.view = v;
        view.addEventHandler(this);
        view.bindData(model);
    }

    // Handling button-events
    public void handle(Event event) {
         /* Adding product  */
        if (event.getSource().equals(view.addButton)) {

            Product p = new Product();
            p.setName(view.getProductName());
            String productPrice = view.getProductPrice();
            boolean creationFailed = false;
            if (productPrice != null) {
                try {
                    p.setPrice(Double.parseDouble(productPrice));
                } catch (final NumberFormatException ex) {
                    creationFailed = true;
                    System.out.print("Price-Input has to be a number");
                }
            }
            String productCount = view.getProductCount();
            if (productCount != null) {
                try {
                    p.setQuantity(Integer.parseInt(productCount));
                } catch (final NumberFormatException ex) {
                    creationFailed = true;
                    System.out.print("Quantity-Input has to be a number");
                }

            }
            try{
                p.setId(IDGenerator.getId());
            }catch(IDOverflowException ex){
                creationFailed = true;
                System.out.print("ID is greater than 999999");
            }


            if (!creationFailed)
                model.doAdd(1, p);
        }

        /* Deleting product  */
        if (event.getSource().equals(view.deleteButton)) {
            if (view.getSelectedProduct() != null)
                model.doRemove(view.getProductList().getItems().indexOf(view.getSelectedProduct()));
        }

        /* Saving productlist */
        if (event.getSource().equals(view.saveB) && view.comboBox.getSelectionModel().getSelectedItem() != null) {

            /* Binary */
            if (view.comboBox.getSelectionModel().getSelectedItem().toString().equals("Binary")) {
                serializablePattern.setStrategy(new BinaryStrategy());
                Path p = Paths.get("products.ser");
                serializablePattern.write(model.products.subList(0, model.products.size()),p);
            }
            /* Beans */
            if (view.comboBox.getSelectionModel().getSelectedItem().toString().equals("Beans")) {
                serializablePattern.setStrategy(new XMLStrategy());
                Path p = Paths.get("products.xml");
                serializablePattern.write(model.products.subList(0, model.products.size()),p);
            }
             /* XStreams */
            if (view.comboBox.getSelectionModel().getSelectedItem().toString().equals("XStream")) {
                serializablePattern.setStrategy(new XStreamStrategy());
                Path p = Paths.get("xproducts.xml");
                serializablePattern.write(model.products.subList(0, model.products.size()), p);
            }
        }

        /* Loading productlist */
        if (event.getSource().equals(view.loadB) && view.comboBox.getSelectionModel().getSelectedItem() != null) {

            if (view.comboBox.getSelectionModel().getSelectedItem().toString().equals("Binary")) {
                serializablePattern.setStrategy(new BinaryStrategy());
                Path p = Paths.get("products.ser");
                serializablePattern.open(p);
                Product product = null;
                while ((product = serializablePattern.readObject()) != null) {
                    loadedProducts.add(product);
                }
            }

            if (view.comboBox.getSelectionModel().getSelectedItem().toString().equals("Beans")) {
                serializablePattern.setStrategy(new XMLStrategy());
                Path p = Paths.get("products.xml");
                Product product = null;
                while( (product = serializablePattern.readObject()) != null ){
                    loadedProducts.add(product);
                }
            }

            if (view.comboBox.getSelectionModel().getSelectedItem().toString().equals("XStream")) {
                serializablePattern.setStrategy(new XStreamStrategy());
                Path p = Paths.get("xproducts.xml");
                serializablePattern.open(p);
                Product product = null;
                while( (product = serializablePattern.readObject()) != null ){
                    loadedProducts.add(product);
                }
            }

            model.products.clear();
            model.productList = loadedProducts;
            model.doSet(1, model.productList);

        }
    }
}