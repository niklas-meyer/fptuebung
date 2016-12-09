package controller;

import fpt.com.Product;

/**
 * Created by Surya on 11.11.2016.
 */



public interface ProductHandler {
    void createProduct(String name, double price, int quantity);

    void deleteProduct(Product p);

    void load(int selectedStrategy);

    void save(int selectedStrategy);

}
