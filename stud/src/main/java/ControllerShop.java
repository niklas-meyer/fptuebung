import fpt.com.*;
//import fpt.com.Product;
import javafx.event.Event;
import javafx.event.EventHandler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.function.Consumer;

/**
 * Created by NiklasM on 09.11.16.
 */
public class ControllerShop implements EventHandler{

    ModelShop model;
    ViewShop view;

    public void link (ModelShop m, ViewShop v){
        this.model = m;
        this.view = v;
        view.addEventHandler(this);
        view.bindData(model);
   }

    @Override
    public void handle(Event event) {
        if(event.getSource().equals(view.addButton)) {
            /*
                    Add Button:
             */
            Product p = new Product();
            p.setName(view.getProductName());
            String productPrice = view.getProductPrice();
            boolean creationFailed = false;
            if(productPrice != null){
                try {
                    p.setPrice(Double.parseDouble(productPrice));
                } catch (final NumberFormatException ex) {
                    creationFailed = true;
                    System.out.print("Feld 2 enthält keine Zahl");
                }
            }
            String productCount = view.getProductCount();
            if(productCount != null) {
                try {
                    p.setQuantity(Integer.parseInt(productCount));
                } catch (final NumberFormatException ex) {
                    creationFailed = true;
                    System.out.print("Feld 3 enthält keine Zahl");
                }

            }
            if(!creationFailed)
                 model.doAdd(1, p);
        }
        if(event.getSource().equals(view.deleteButton)) {
            if(view.getSelectedProduct() != null){
                int index = view.getProductList().getItems().indexOf(view.selectedProduct);
                model.doRemove(index);
            }


    }
    }
}



