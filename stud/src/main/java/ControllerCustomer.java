import javafx.event.Event;

/**
 * Created by Leona on 14.11.2016.
 */
public class ControllerCustomer {

    ModelShop model;
    ViewCustomer view;
    Order order;

    public void link (Order o,ViewCustomer v){
        this.order = o;
        this.view = v;

        view.bindData(order);
    }

}



