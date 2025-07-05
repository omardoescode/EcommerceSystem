package main.java.ecommerce.Customer;

import main.java.ecommerce.Cart.Cart;

public class Customer {
	private String customer_id;
	private String name;
	private String address;
	private double balance;
	private Cart cart;

	Customer(String customer_id, String name, String address, double balance) {
		this.customer_id = customer_id;
		this.name = name;
		this.address = address;
		this.balance = balance;
		this.cart = new Cart();
	}

	public String getIdentifier() {
		return customer_id;
	}

	public String getName() {
		return name;
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
}
