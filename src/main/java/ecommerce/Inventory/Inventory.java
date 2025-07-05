package main.java.ecommerce.Inventory;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import main.java.ecommerce.Order.Order;
import main.java.ecommerce.Order.OrderItem;
import main.java.ecommerce.Product.Expirable;
import main.java.ecommerce.Product.ExpirableProduct;
import main.java.ecommerce.Product.Product;

public class Inventory {
	private final Map<String, Product> products;

	public Inventory() {
		products = new HashMap<>();
	}

	public void addProduct(Product prod) {
		products.put(prod.getIdentifier(), prod);
	}

	public void addBatch(List<Product> batch) {
		batch.stream().forEach(this::addProduct);
	}

	public List<Product> getProducts() {
		return new ArrayList<>(products.values());
	}

	public Product getProduct(String id) {
		return products.get(id);
	}

	public void removeExpired() {
		var iterator = products.entrySet().iterator();
		while (iterator.hasNext()) {
			var entry = iterator.next();
			var product = entry.getValue();

			product.asFeature(Expirable.class).ifPresent(expirable -> {
				if (expirable.isExpired()) {
					iterator.remove();
				}
			});
		}
	}

	public List<Product> getOrderProducts(Order ord) throws RuntimeException {
		List<Product> products = new ArrayList<>();

		for (var item : ord.getItems()) {
			var id = item.productIdentifier();
			Product prod = getProduct(id);

			if (prod == null)
				throw new RuntimeException("Product not found: " + item.productIdentifier());

			products.add(prod);
		}

		return products;
	}

	public void process(Order ord) throws RuntimeException {
		for (var item : ord.getItems()) {
			var prod = getProduct(item.productIdentifier());
			if (prod == null)
				throw new RuntimeException("Failed to process order " + ord.getOrderId() + ": Non existent product");
			if (prod.getQuantity() < item.quantity())
				throw new RuntimeException("Failed to process order " + ord.getOrderId()
						+ ": Insufficient stock in inventory for product " + prod.getName());
			prod.setQuantity(prod.getQuantity() - item.quantity());
		}
	}

	public void report(PrintStream out) {
		for (var entry : products.entrySet()) {
			var product = entry.getValue();
			out.println("Product ID: " + product.getIdentifier());
			out.println("  Name: " + product.getName());
			out.println("  Quantity: " + product.getQuantity());
			out.println("  Price: " + product.getPrice());

			product.asFeature(Expirable.class).ifPresent(exp -> out.println("  Is Expired: " + exp.isExpired()));

			product.asFeature(main.java.ecommerce.Product.Shippable.class)
					.ifPresent(ship -> out.println("  Shipping Weight: " + ship.getWeight()));

			out.println(); // extra newline between products
		}
	}
}
