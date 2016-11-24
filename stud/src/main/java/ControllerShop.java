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
import java.util.ArrayList;
import java.util.function.Consumer;

/**


/**
 * Created by NiklasM on 09.11.16.
 */
public class ControllerShop implements EventHandler {

    ModelShop model;
    ViewShop view;
    ProductList loadedProducts = new ProductList();
    ArrayList<SerializableStrategy> list = new ArrayList<>();
    IDGenerator idGenerator;

    public ControllerShop() {
        list.add(new BinaryStrategy());
        list.add(new XMLStrategy());
        list.add(new XStreamStrategy());
        idGenerator = new IDGenerator();
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
                p.setId(idGenerator.getId());
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
        if (event.getSource().equals(view.saveB)) {
            if (view.comboBox.getSelectionModel().getSelectedItem().toString().equals("Binary")) {
                try {
                    list.get(0).open(null, new FileOutputStream("products.ser"));
                    for (Product p : model.products) {
                        list.get(0).writeObject(p);
                    }
                    list.get(0).close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (view.comboBox.getSelectionModel().getSelectedItem().toString().equals("Beans")) {
                try {
                    list.get(1).open(null, new FileOutputStream("products.xml"));
                    for (Product p : model.products) {
                        list.get(1).writeObject(p);
                    }
                    list.get(1).close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (view.comboBox.getSelectionModel().getSelectedItem().toString().equals("XStream")) {
                try {
                    list.get(2).open(null, new FileOutputStream("XStreamProducts.xml"));
                    list.get(2).writeObject(model.productList.get(0));

                    list.get(2).close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


        }
        /* Loading productlist */
        if (event.getSource().equals(view.loadB)) {
            if (view.comboBox.getSelectionModel().getSelectedItem().toString().equals("Binary")) {
                try {
                    list.get(0).open(new FileInputStream("products.ser"), null);
                    do{
                        loadedProducts.add(list.get(1).readObject());
                    }while(list.get(0).readObject() != null);
                    list.get(0).close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (view.comboBox.getSelectionModel().getSelectedItem().toString().equals("Beans")) {
                try {
                    list.get(1).open(new FileInputStream("products.xml"), null);
                        do{
                            loadedProducts.add(list.get(1).readObject());
                        }while(list.get(1).readObject() != null);

                    list.get(1).close();


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (view.comboBox.getSelectionModel().getSelectedItem().toString().equals("XStream")) {
                System.out.print("XSTREAM");
                try {
                    list.get(2).open(new FileInputStream("XstreamProducts.xml"), null);
                    do{
                        loadedProducts.add(list.get(2).readObject());
                    }while(list.get(2).readObject() != null);

                    list.get(2).close();


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            model.products.clear();
            model.productList = loadedProducts;
            model.doSet(1, model.productList);

        }
    }
}