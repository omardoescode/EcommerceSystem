package main.java.ecommerce.Shipping;

import java.util.ArrayList;

import main.java.ecommerce.Inventory.Inventory;
import main.java.ecommerce.Order.Order;
import main.java.ecommerce.Product.Product;
import main.java.ecommerce.Product.Shippable;

public class MyCompanyShippingService implements ShippingService {
	private double fees_per_kilogram;
	private final Inventory inv;

	public MyCompanyShippingService(double fees_per_kilogram, Inventory inv) {
		if (fees_per_kilogram < 0)
			fees_per_kilogram = 0;
		this.fees_per_kilogram = fees_per_kilogram;
		this.inv = inv;
	}

	public double shipping_fees(Order ord) throws IllegalArgumentException {
		var products = inv.getOrderProducts(ord);
		double totalWeight = products.stream()
				.map(product -> product.asFeature(Shippable.class).orElseThrow(
						() -> new IllegalArgumentException("Product " + product.getName() + " is not shippable")))
				.mapToDouble(Shippable::getWeight).sum();

		return totalWeight;
	}

	public void ship(Order ord, String location) throws IllegalArgumentException {
		var products = inv.getOrderProducts(ord);

		// Validate that products are shippable
		for (var product : products)
			if (product.asFeature(Shippable.class).isEmpty())
				throw new IllegalArgumentException("Product " + product.getName() + " is not shippable");

		// TODO: Report these products to admin, to ship them
		System.out.println("[MyCompanyShippingService] Shipping to " + location + " has begun");
	}
}
