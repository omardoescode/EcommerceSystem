package main.java.ecommerce.Product;

import java.util.Optional;

public class BaseProduct implements Product {
	private String identifier;
	private String name;
	private int quantity;
	private int price;

	BaseProduct(String identifier, String name, int quantity, int price) {
		this.identifier = identifier;
		this.name = name;
		this.quantity = quantity;
		this.price = price;
	}

	public String getIdentifier() {
		return identifier;
	}

	public String getName() {
		return name;
	}

	public int getQuantity() {
		return quantity;
	}

	public int getPrice() {
		return price;
	}

	public int getTotalPrice() {
		return price * quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public boolean isAvailable() {
		return this.quantity > 0;
	}

	public Optional<Product> asFeature(Product feature) {
		return Optional.empty();
	}
}
