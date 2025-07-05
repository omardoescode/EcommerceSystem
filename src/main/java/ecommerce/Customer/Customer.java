package main.java.ecommerce.Customer;

import java.util.List;
import java.util.ArrayList;

import main.java.ecommerce.Cart.Cart;
import main.java.ecommerce.Order.Order;

public class Customer {
	private String username;
	private String address;
	private double balance;
	private Cart cart;
	private List<Order> orders;

	public Customer(String username, String address, double balance) {
		this.username = username;
		this.address = address;
		this.balance = balance;
		this.cart = new Cart();
		this.orders = new ArrayList<>();
	}

	public String getUsername() {
		return username;
	}

	public String getAddress() {
		return address;
	}

	public double getBalance() {
		return balance;
	}

	public void updateBalance(double balance) {
		this.balance = balance;
	}

	public boolean canAfford(double balance) {
		return balance > this.balance;
	}

	public Cart getCart() {
		return cart;
	}

	public void addOrder(Order ord) {
		orders.add(ord);
	}
}
