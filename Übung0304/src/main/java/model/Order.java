package model;

import fpt.com.Product;

/**
 * Created by Surya on 01.11.2016.
 */

//bestellte Anzahl der Waren und Preis aller Waren
public class Order extends ProductList implements fpt.com.Order {


    @Override
    public double getSum() {
        double sum = 0;
        for (Product p : this) {
            sum += p.getPrice();
        }
        return sum;
    }

    @Override

    public int getQuantity() {
        int quantity = 0;

        for (Product p : this) {
            quantity += p.getQuantity();
        }
        return quantity;
    }

}
