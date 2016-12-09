package controller;

/**
 * Created by Tobis on 23.11.2016.
*/
import model.Product;
import model.ModelShop;
import view.ViewCustomer;
import view.*;

public class ControllerCustomer {

    public void link(ModelShop model, ViewCustomer view) {

        view.setList(model);



    }

}
