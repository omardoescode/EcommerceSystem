package main.java.ecommerce.Cart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.io.PrintStream;

public class Cart {
	private final Map<String, Integer> items;

	public Cart() {
		items = new HashMap<>();
	}

	public ArrayList<CartItem> getItems() {
		ArrayList<CartItem> result = new ArrayList<>();
		for (Map.Entry<String, Integer> entry : items.entrySet())
			result.add(new CartItem(entry.getKey(), entry.getValue()));
		return result;
	}

	public void addItem(CartItem item) {
		items.merge(item.productIdentifier(), item.quantity(), Integer::sum);
	}

	public void removeItem(String itemId) {
		items.remove(itemId);
	}

	public void removeItem(String itemId, int quantity) {
		Integer existingQuantity = items.get(itemId);
		if (existingQuantity == null)
			return;

		if (existingQuantity > quantity)
			items.put(itemId, existingQuantity - quantity);
		else
			items.remove(itemId);
	}

	public boolean isEmpty() {
		return items.isEmpty();
	}

	public void clear() {
		items.clear();
	}

	public void report(PrintStream out) {
		for (Map.Entry<String, Integer> entry : items.entrySet()) {
			out.printf("Product ID: %s, Quantity: %d%n", entry.getKey(), entry.getValue());
		}
	}
}
