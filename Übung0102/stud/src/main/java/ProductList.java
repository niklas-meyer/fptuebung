import fpt.com.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.Consumer;

/**
 * Created by NiklasM on 05.11.16.
 */
public class ProductList extends java.util.ArrayList<fpt.com.Product> implements fpt.com.ProductList {
    private int counter = 0;
    public ProductList(){

    }

    public int getCounter(){
        return this.counter;
    }
    @Override
    public boolean add(fpt.com.Product e) {
            this.add(counter,e);
            counter++;
            return true;
    }


    public boolean delete(fpt.com.Product product) {
        if(this.remove(product)){
            counter--;
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public int size() {
        return this.toArray().length;
    }


    @Override
    public Product findProductById(long id) {
        return (Product)this.get((int)id);
    }

    @Override
    public Product findProductByName(String name) {
        Product searchedProduct = null;
        for(fpt.com.Product p : this){
            if(p.getName().equals(name.toString())){
                searchedProduct =(Product) p;
            }
        }
        return searchedProduct;
    }

    @Override
    public Iterator<fpt.com.Product> iterator() {
        return null;
    }
}
