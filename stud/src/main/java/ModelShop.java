import fpt.com.*;
import fpt.com.Product;
import javafx.collections.FXCollections;
import javafx.collections.ModifiableObservableListBase;
import javafx.collections.ObservableList;

import java.util.stream.Collectors;

/**
 * Created by NiklasM on 09.11.16.
 */
public class ModelShop extends ModifiableObservableListBase {
    ProductList productList = new ProductList();
    ObservableList<String> productNames = FXCollections.observableArrayList("xyz","abc");

    public ModelShop(){


    }

    @Override
    public Object get(int index) {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    protected void doAdd(int index, Object element) {
        //productList.products.add((Product)element);
        productNames.add(((Product)element).getName()+" ("+((Product)element).getPrice()+","+((Product)element).getQuantity()+")");
    }

    @Override
    protected Object doSet(int index, Object element) {
        //productNames.addAll(((ProductList) element).products.stream().map(Product::getName).collect(Collectors.toList()));
        //return productNames;
        return null;
    }

    @Override
    protected Object doRemove(int index) {
        productNames.remove(index);
        return productNames.get(index);
    }
}
