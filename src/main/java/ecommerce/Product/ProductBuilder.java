package main.java.ecommerce.Product;

import java.util.Date;
import java.util.Optional;

public class ProductBuilder {
	private static int productID;
	private String name;
	private int quantity;
	private int price;
	private Date expiry;
	private Double shippingWeight;

	public ProductBuilder setName(String name) {
		this.name = name;
		return this;
	}

	public ProductBuilder setQuantity(int quantity) {
		this.quantity = quantity;
		return this;
	}

	public ProductBuilder setPrice(int price) {
		this.price = price;
		return this;
	}

	public ProductBuilder setExpirable(Date expiry) {
		this.expiry = expiry;
		return this;
	}

	public ProductBuilder setShippable(Double shippingWeight) {
		this.shippingWeight = shippingWeight;
		return this;
	}

	public Product build() {
		if (name == null || name.isBlank())
			throw new IllegalStateException("Product name must not be null or empty");

		if (price <= 0)
			throw new IllegalStateException("Product price must be greater than zero");

		if (quantity <= 0)
			throw new IllegalStateException("Product quantity must be greater than zero");

		var newProductId = Integer.toString(productID++);
		Product base = new BaseProduct(newProductId, name, price, quantity);

		if (expiry != null) {
			base = new ExpirableProduct(base, expiry);
		}

		if (shippingWeight != null) {
			base = new ShippableProduct(base, shippingWeight);
		}

		return base;
	}
}
