package TestFiles;

import java.util.ArrayList;

public class Pizza {

	private String name;
	private double price;
	private int size;
	private ArrayList<Topping> toppings;

	public Pizza(String name, double price, int size,
			ArrayList<Topping> toppings) {
		this.name = name;
		this.price = price;
		this.size = size;
		this.toppings = toppings;
	}

	public String getName() {
		return this.name;
	}

	public double getPrice() {
		return this.price;
	}

	public int getSize() {
		return this.size;
	}

	public ArrayList<Topping> getToppings() {
		return this.toppings;
	}

	public void printPizza() {
		System.out.println("Name: " + this.name);
		System.out.println("Price: " + this.price);
		System.out.println("Size: " + this.size);
		System.out.println("Toppings:");
		for (int i = 0; i < this.toppings.size(); i++) {
			System.out.println(i + 1 + "." + this.toppings.get(i).getName());
		}
	}

}
