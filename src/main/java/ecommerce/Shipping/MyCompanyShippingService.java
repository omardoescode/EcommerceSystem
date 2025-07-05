package main.java.ecommerce.Shipping;

import java.util.ArrayList;

import main.java.ecommerce.Product.Product;
import main.java.ecommerce.Product.Shippable;
import main.java.ecommerce.Customer.Customer;

class MyCompanyShippingService implements ShippingService {
	private final Customer customer;
	private double fees_per_kilogram;

	MyCompanyShippingService(Customer customer, double fees_per_kilogram) {
		if (fees_per_kilogram < 0)
			fees_per_kilogram = 0;
		this.fees_per_kilogram = fees_per_kilogram;
		this.customer = customer;
	}

	public double shipping_fees(ArrayList<Product> products) throws IllegalArgumentException {
		double totalWeight = products.stream()
				.map(product -> product.asFeature(Shippable.class).orElseThrow(
						() -> new IllegalArgumentException("Product " + product.getName() + " is not shippable")))
				.mapToDouble(Shippable::getWeight).sum();

		return totalWeight;
	}

	public void ship(ArrayList<Product> products) throws IllegalArgumentException {
		// Validate that products are shippable
		for (var product : products)
			if (product.asFeature(Shippable.class).isEmpty())
				throw new IllegalArgumentException("Product " + product.getName() + " is not shippable");

		// TOOD: Report these products to admin, to ship them
	}
}
