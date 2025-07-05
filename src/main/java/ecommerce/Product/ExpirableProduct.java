package main.java.ecommerce.Product;

import java.util.Date;
import java.util.Optional;

public class ExpirableProduct implements Product, Expirable {
	final private Product product;
	private Date expiry_date;

	ExpirableProduct(Product product, Date expiry_date) {
		this.product = product;
		this.expiry_date = expiry_date;
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

	public double getPrice() {
		return product.getPrice();
	}

	public double getTotalPrice() {
		return product.getTotalPrice();
	}

	public void setQuantity(int quantity) {
		product.setQuantity(quantity);
	}

	public void setPrice(int price) {
		product.setPrice(price);
	}

	public boolean isAvailable() {
		return !isExpired() && product.isAvailable();
	}

	public <T extends Feature> Optional<T> asFeature(Class<T> featureType) {
		if (featureType.isInstance(this)) {
			return Optional.of(featureType.cast(this));
		}
		return this.product.asFeature(featureType);
	}

	public boolean isExpired() {
		return new Date().after(expiry_date);
	}
}
