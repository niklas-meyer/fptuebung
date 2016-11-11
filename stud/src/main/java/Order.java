import fpt.com.*;
import fpt.com.Product;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by NiklasM on 05.11.16.
 */
public class Order  extends java.util.ArrayList<fpt.com.Product> implements fpt.com.Order {
    private ArrayList<Product> orderedProducts;
    private SimpleIntegerProperty quantity;
    private SimpleFloatProperty sum;

    @Override
    public boolean add(Product e) {
        return false;
    }

    @Override
    public boolean delete(Product p) {
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
    public Iterator<Product> iterator() {
        return null;
    }
}
