import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.thoughtworks.xstream.converters.SingleValueConverter;
import com.thoughtworks.xstream.converters.SingleValueConverterWrapper;
import com.thoughtworks.xstream.converters.extended.PropertyEditorCapableConverter;
import com.thoughtworks.xstream.converters.extended.ToAttributedValueConverter;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.Serializable;

/**
 * Created by NiklasM on 05.11.16.
 */
@XStreamAlias("Waren")

public class Product implements fpt.com.Product,java.io.Externalizable {

    //XStream Annotations hier durch Converter ersetzt
    private SimpleLongProperty id = new SimpleLongProperty();
    public SimpleDoubleProperty price = new SimpleDoubleProperty();
    public int quantity;
    private SimpleStringProperty name= new SimpleStringProperty();

    public Product(){
        try{
            setId(IDGenerator.getId());
        }catch(IDOverflowException e){
            e.printStackTrace();
        }
    }

    public Product(String name, Double price, Integer quantity){
        super();
        setName(name);
        setPrice(price);
        setQuantity(quantity);
    }

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
    public void setPrice(double price)  {
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
        return name;

    }

    @Override
    public ObservableValue<Number> priceProperty() {
        return price;
    }

    @Override
    public ObservableValue<Number> quantityProperty() {
        return new SimpleIntegerProperty(quantity);
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(this);
    }


    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        price.set(in.readDouble());
        name.set((in.readObject().toString()));
        id.set(in.readLong());
    }
}
