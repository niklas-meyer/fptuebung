import fpt.com.*;
import fpt.com.Product;

import java.util.ArrayList;

/**
 * Created by NiklasM on 05.11.16.
 */
public class ProductList extends java.util.ArrayList<fpt.com.Product> implements fpt.com.ProductList {

    public ArrayList<Product> products = new ArrayList<Product>();
    @Override
    public boolean add(Product e) {
        if(products.add(e)){
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public boolean delete(Product product) {
        if(products.remove(product)){
            return true;
        }
        else {
            return false;
        }
    }



    @Override
    public Product findProductById(long id) {
        return null;
    }

    @Override
    public Product findProductByName(String name) {
        return null;
    }
}
