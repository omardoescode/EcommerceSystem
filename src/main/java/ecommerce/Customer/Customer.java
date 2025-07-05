package main.java.ecommerce.Customer;

public class Customer {
	private String customer_id;
	private String name;
	private String address;
	private double balance;

	Customer(String customer_id, String name, String address, double balance) {
		this.customer_id = customer_id;
		this.name = name;
		this.address = this.address;
		this.balance = balance;
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
