package fpt.com;

import java.io.Serializable;

import javafx.beans.value.ObservableValue;

/**
 * This interface is used in exercise one.
 * 
 * @author Jens Kapitza
 * 
 */
public interface Product extends Serializable {

	/**
	 * 
	 * @return The id for the product.
	 */
	long getId();

	/**
	 * set the id
	 * 
	 * @param id
	 *            the id for the product
	 */
	void setId(long id);

	/**
	 * @return The price of the product
	 */
	double getPrice();

	/**
	 * Alters the price of the product.
	 * 
	 * @param price
	 *            The new price
	 */
	void setPrice(double price);

	/**
	 * @return The amount of the model.Product remaining
	 */
	int getQuantity();

	/**
	 * Alters the quantity of the model.Product.
	 * 
	 * @param quantity
	 *            The new quantity of the model.Product
	 */
	void setQuantity(int quantity);

	/**
	 * @return The name of the model.Product
	 */
	String getName();

	/**
	 * set the name for the product
	 * 
	 * @param name
	 *            the product name
	 */
	void setName(String name);
	
	// new since JavaFX

	ObservableValue<String> nameProperty();

	ObservableValue<Number> priceProperty();

	ObservableValue<Number> quantityProperty();
}
