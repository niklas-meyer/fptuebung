package model;

import java.io.Externalizable;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import javafx.beans.value.ObservableValue;
import javafx.beans.property.*;

/**
 * Created by Surya on 01.11.2016.
 */

//Name, ID, Stückzahl & Preis

public class Product implements fpt.com.Product, Externalizable {

	private long id;
    private SimpleDoubleProperty price = new SimpleDoubleProperty();
    private SimpleIntegerProperty quantity = new SimpleIntegerProperty();
    private SimpleStringProperty name = new SimpleStringProperty();
   
    @Override
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
