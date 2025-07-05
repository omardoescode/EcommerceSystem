package main.java.ecommerce.Product;

import java.util.Optional;

public class ShippableProduct implements Product, Shippable {
	final private Product product;
	private double weight;

	ShippableProduct(Product product, double weight) {
		this.product = product;
		this.weight = weight;
	}

	public String getIdentifier() {
		return product.getIdentifier();
	}

	public String getName() {
		return product.getName();
	}

	public int getQuantity() {
		return product.getQuantity();
	}

	public int getPrice() {
		return product.getPrice();
	}

	public int getTotalPrice() {
		return product.getTotalPrice();
	}

	public void setQuantity(int quantity) {
		product.setQuantity(quantity);
	}

	public void setPrice(int price) {
		product.setPrice(price);
	}

	public boolean isAvailable() {
		return this.product.isAvailable();
	}

	public <T extends Feature> Optional<T> asFeature(Class<T> featureType) {
		if (featureType.isInstance(this)) {
			return Optional.of(featureType.cast(this));
		}
		return this.product.asFeature(featureType);
	}

	public double getWeight() {
		return this.weight;
	}
}
