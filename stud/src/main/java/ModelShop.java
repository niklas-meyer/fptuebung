
import javafx.collections.FXCollections;
import javafx.collections.ModifiableObservableListBase;
import javafx.collections.ObservableList;

/**
 * Created by NiklasM on 09.11.16.
 */
public class ModelShop extends ModifiableObservableListBase {
    public ProductList productList = new ProductList();
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

    }

    @Override
    protected Object doSet(int index, Object element) {
        for(fpt.com.Product p: (ProductList)element) {
            products.add((Product)p);
        }
        return this;
    }

    @Override
    protected Object doRemove(int index) {
        try {
            products.remove(index);
            productList.delete(products.get(index));

            return products.get(index);
        }catch (java.lang.IndexOutOfBoundsException ex){
            return null;
        }

    }
}
