import fpt.com.*;
//import fpt.com.Product;
import javafx.event.Event;
import javafx.event.EventHandler;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.function.Consumer;

/**


/**
 * Created by NiklasM on 09.11.16.
 */
public class ControllerShop implements EventHandler{

    ModelShop model;
    ViewShop view;
    ArrayList<SerializableStrategy> list = new ArrayList<>();

    public ControllerShop(){
        list.add(new BinaryStrategy());
        list.add(new XMLStrategy());
    }



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
                       //ID nicht vergessen
        }
        if(event.getSource().equals(view.deleteButton)) {
                        if(view.getSelectedProduct != null){
                                model.doRemove(view.getS)
                                   }
                }
                if(event.getSource().equals(view.saveB)){
                        if(view.comboBox.getSelectionModel().getSelectedItem().toString().equals("Binary")){
                               try {
                                        list.get(0).open(null, new FileOutputStream("products.ser"));
                                        for(Product p: model.products){
                                                list.get(0).writeObject(p);
                                            }
                                        list.get(0).close();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                            }
                        if(view.comboBox.getSelectionModel().getSelectedItem().toString().equals("Beans")){
                                try {
                                        list.get(1).open(null, new FileOutputStream("products.xml"));
                                        for(Product p: model.products){
                                                list.get(1).writeObject(p);
                                            }
                                        list.get(1).close();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                            }

               }
}
 }

