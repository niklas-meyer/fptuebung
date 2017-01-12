package model;

import java.io.Externalizable;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAliasType;
import javafx.beans.value.ObservableValue;
import javafx.beans.property.*;

import javax.persistence.*;

import org.apache.openjpa.persistence.Persistent;
import org.apache.openjpa.persistence.jdbc.Strategy;

/**
 * Created by Surya on 01.11.2016.
 */

//Name, ID, Stückzahl & Preis

@Entity()
@Table(name = "products")
@XStreamAliasType ("ware ") // OpenJPA abl e i tung en werden auch a l s ware bezeuchnet .
@XStreamAlias ("ware ")
public class Product implements fpt.com.Product, Externalizable {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY,  generator = "products_SEQ")
	private long id;
    @Persistent
    @Strategy ("fpt.com.db.DoublePropertyValueHandler")
    private SimpleDoubleProperty price = new SimpleDoubleProperty();
    @Persistent
    @Strategy ("fpt.com.db.IntegerPropertyValueHandler")
    private SimpleIntegerProperty quantity = new SimpleIntegerProperty();
    @Persistent
    @Strategy ("fpt.com.db.StringPropertyValueHandler")
    private SimpleStringProperty name = new SimpleStringProperty();


    public long getId() { return id; }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public double getPrice() { return price.get(); }

    @Override
    public void setPrice(double price) {
        this.price.set(price);
    }

    @Override
    public int getQuantity() { return quantity.get(); }

    @Override
    public void setQuantity(int quantity) {
        this.quantity.set(quantity);
    }

    @Override
    public String getName() { return name.get(); }

    @Override
    public void setName(String name) {
        this.name.set(name); }

    public String toString() {
        return name.get() + " " + price.get() + " " + quantity.get() + " " + id;
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
        return quantity;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeLong(getId());
        out.writeObject(getName());
        out.writeDouble(getPrice());
        out.writeInt(getQuantity());
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        setId(in.readLong());
        setName((String) in.readObject());
        setPrice(in.readDouble());
        setQuantity(in.readInt());
    }

}
