package main.java.ecommerce.Order;

import main.java.ecommerce.Customer.Customer;
import main.java.ecommerce.Inventory.Inventory;

public interface OrderService {
	/**
	 * Checkout a customer's cart, and add the order to his order history
	 * 
	 * @param customer The customer whose cart is to checkout
	 * @param ship     Whether the customer has requested shipping
	 */
	void checkout(Customer customer, boolean ship) throws RuntimeException;
}
