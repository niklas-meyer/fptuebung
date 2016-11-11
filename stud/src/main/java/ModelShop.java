import fpt.com.*;
import javafx.collections.ModifiableObservableListBase;

/**
 * Created by NiklasM on 09.11.16.
 */
public class ModelShop extends ModifiableObservableListBase {
    ProductList productList = new ProductList();

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
        productList.add((Product)element);


    }

    @Override
    protected Object doSet(int index, Object element) {
        return null;
    }

    @Override
    protected Object doRemove(int index) {
        return null;
    }
}
