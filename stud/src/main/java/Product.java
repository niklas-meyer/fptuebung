import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;

/**
 * Created by NiklasM on 05.11.16.
 */
public class Product implements fpt.com.Product {

    private SimpleLongProperty id = new SimpleLongProperty();
    private SimpleDoubleProperty price = new SimpleDoubleProperty();
    private int quantity;
    private SimpleStringProperty name= new SimpleStringProperty();

    public long getId() {
        return id.get();
    }

    @Override
    public void setId(long id) {
        this.id.set(id);
    }

    @Override
    public double getPrice() {
        return price.get();
    }

    @Override
    public void setPrice(double price) {
        this.price.set(price);

    }

    @Override
    public int getQuantity() {
        return quantity;
    }

    @Override
    public void setQuantity(int quantity) {
        this.quantity=quantity;

    }

    @Override
    public String getName() {
        return name.get();
    }

    @Override
    public void setName(String name) {
        this.name.set(name);

    }

    @Override
    public ObservableValue<String> nameProperty() {
        return null;
    }

    @Override
    public ObservableValue<Number> priceProperty() {
        return null;
    }

    @Override
    public ObservableValue<Number> quantityProperty() {
        return null;
    }
}
