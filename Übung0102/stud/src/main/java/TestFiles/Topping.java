package TestFiles;

public class Topping {

	private String name;
	private boolean vegetarian;

	public Topping(String name, boolean vegetarian) {
		this.name = name;
		this.vegetarian = vegetarian;
	}

	public String getName() {
		return this.name;
	}

	public boolean getVegetarian() {
		return this.vegetarian;
	}

}
