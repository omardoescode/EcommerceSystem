package main.java.ecommerce.Cart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
		for (int i = 0; i < items.size(); i++) {
			items.merge(item.productIdentifier(), item.quantity(), Integer::sum);
		}
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
}
