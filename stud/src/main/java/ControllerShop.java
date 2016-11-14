import fpt.com.*;
import fpt.com.Product;
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
            model.doAdd(1, view.getProduct());
            model.productList.listIterator().forEachRemaining(product -> System.out.print(product.getName()));
        }
        if(event.getSource().equals(view.deleteButton)) {
            model.doRemove(view.selectedProductIndex);
        }

    }
}



