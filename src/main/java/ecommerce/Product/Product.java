package main.java.ecommerce.Product;

import java.util.Optional;

public interface Product {
	String getIdentifier();

	String getName();

	int getQuantity();

	double getPrice();

	double getTotalPrice();

	void setQuantity(int quantity);

	void setPrice(int quantity);

	boolean isAvailable();

	<T extends Feature> Optional<T> asFeature(Class<T> featureType);
}
