package main.java.ecommerce.Product;

import java.util.Optional;

public class BaseProduct implements Product {
	private String identifier;
	private String name;
	private int quantity;
	private double price;

	BaseProduct(String identifier, String name, int quantity, double price) {
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

	public double getPrice() {
		return price;
	}

	public double getTotalPrice() {
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

	public <T extends Feature> Optional<T> asFeature(Class<T> featureType) {
		return Optional.empty();
	}
}
