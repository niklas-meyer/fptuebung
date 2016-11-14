import fpt.com.*;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by NiklasM on 05.11.16.
 */
public class ProductList extends java.util.ArrayList<fpt.com.Product> implements fpt.com.ProductList {
    private int counter = 0;
    public ProductList(){

    }
    @Override
    public boolean add(fpt.com.Product e) {
            this.add(counter,e);
            counter++;
            return true;
    }


    public boolean delete(fpt.com.Product product) {
        if(this.remove(product)){
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public int size() {
        return this.size();
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
    public Iterator<fpt.com.Product> iterator() {
        return null;
    }
}
