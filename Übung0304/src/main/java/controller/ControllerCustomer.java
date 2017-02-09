package controller;

/**
 * Created by Tobis on 23.11.2016.
*/
import fpt.com.db.StringPropertyValueHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import model.Order;
import model.Product;
import model.ModelShop;
import view.ViewCustomer;
import view.*;

public class ControllerCustomer {
    ModelShop modelShop;
    ViewCustomer viewCustomer;

    public void link(ModelShop model, ViewCustomer view) {
        this.viewCustomer = view;
        this.modelShop = model;
        view.setList(model);

        view.addEventHandler((ActionEvent e) -> {
            if (e.toString().contains("Buy")) {
                view.createDialogueBox();
            }
            if (e.toString().contains("Add To Order")) {
                Product product = view.getSelectedProduct();
                view.addToOrderList(product);
            }
            if (e.toString().contains("Delete Order")) {
                view.deleteOrderList();
            }
            if(e.toString().contains("Ask Support")){
                view.createChatWindow();
            }
        });

    }


    public void sendLoginMessage(String userName, String password){
        String authentification = "AUTH:";
        authentification += userName + ":" + password;
        Order currentOrder = viewCustomer.getOrder();
        viewCustomer.sendMessageToServer(authentification,currentOrder);
    }

    public void displayInfo(String infoText){
        viewCustomer.setInfoText(infoText);
    }


}
