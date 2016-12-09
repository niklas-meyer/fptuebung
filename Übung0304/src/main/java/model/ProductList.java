package model;

import fpt.com.Product;

public class ProductList extends java.util.ArrayList<Product> implements
        fpt.com.ProductList {

    /**
     *  Created by Tobis on 23.11.2016.
     */
    private static final long serialVersionUID = -318470093051559450L;

    @Override
    public boolean delete(Product product) {
        return super.remove(product);
    }

    @Override
    public Product findProductById(long id) {
        for (Product p : this)
            if (p.getId() == id)
                return p;
        return null;
    }

    @Override
    public Product findProductByName(String name) {
        for (Product p : this)
            if (p.getName() == name)
                return p;
        return null;
    }

}
