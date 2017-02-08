package model;
import java.io.Serializable;
import fpt.com.*;
import fpt.com.Product;

import javafx.collections.*;

public class ModelShop extends ModifiableObservableListBase<model.Product> {

    public ProductList productlist;

    public ModelShop(){
        productlist = new ProductList();
    }

    @Override
    protected void doAdd(int arg0, model.Product arg1) {
        productlist.add(arg0, arg1);
    }

    @Override
    protected model.Product doRemove(int arg0) {
        return (model.Product) productlist.remove(arg0);
    }

    @Override
    protected model.Product doSet(int arg0, model.Product arg1) {
        return (model.Product) productlist.set(arg0, arg1);
    }

    @Override
    public model.Product get(int arg0) {
        return (model.Product) productlist.get(arg0);
    }

    @Override
    public int size() {
        return productlist.size();
    }


    public boolean delete(Product product) {
        return productlist.delete(product);
    }
    public Product findProductById(long id) {
        return (model.Product) productlist.findProductById(id);
    }

    public Product findProductByName(String name) {
        return (model.Product) productlist.findProductByName(name);
    }

    public boolean add(Product e) {
        return productlist.add(e);
    }

}
