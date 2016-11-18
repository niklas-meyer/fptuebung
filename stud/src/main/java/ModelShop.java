
import javafx.collections.FXCollections;
import javafx.collections.ModifiableObservableListBase;
import javafx.collections.ObservableList;

import java.util.stream.Collectors;

/**
 * Created by NiklasM on 09.11.16.
 */
public class ModelShop extends ModifiableObservableListBase {
    public ProductList productList = new ProductList();
    public int index = 0;
    ObservableList<Product> products = FXCollections.observableArrayList();

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
        products.add((Product)element);
        productList.add((Product)element);
        index++;

    }

    @Override
    protected Object doSet(int index, Object element) {
        products.addAll(((ProductList) element).stream().map(p -> (Product) p).collect(Collectors.toList()));
        return this;
    }

    @Override
    protected Object doRemove(int index) {
        //we need an if, so that the delete button will not throw an exception if there are no elements to delete
            if(products.size()>0) {
                products.remove(productList.findProductById(index));
                productList.delete(productList.get(index));
            }
            return null;

    }
}
