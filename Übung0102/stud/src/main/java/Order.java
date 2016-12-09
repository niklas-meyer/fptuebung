import fpt.com.*;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by NiklasM on 05.11.16.
 */
public class Order implements fpt.com.Order {

    public ObservableList<Product> orderedProducts = FXCollections.observableArrayList(

    );
    private SimpleIntegerProperty quantity;
    private SimpleFloatProperty sum;


    public boolean delete(fpt.com.Product p) {
        return false;
    }

    public boolean add(fpt.com.Product e) {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Product findProductById(long id) {
        return null;
    }

    @Override
    public Product findProductByName(String name) {
        return null;
    }

    @Override
    public double getSum() {
        return sum.get();
    }

    @Override
    public int getQuantity() {
        return quantity.get();
    }

    @Override
    public Iterator<fpt.com.Product> iterator() {
        return null;
    }
}
