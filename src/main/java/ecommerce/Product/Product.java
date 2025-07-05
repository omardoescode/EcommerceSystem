package main.java.ecommerce.Product;

import java.util.Optional;

public interface Product {
	String getIdentifier();

	String getName();

	int getQuantity();

	int getPrice();

	int getTotalPrice();

	void setQuantity(int quantity);

	void setPrice(int quantity);

	boolean isAvailable();

	Optional<Product> asFeature(Product feature);
}
