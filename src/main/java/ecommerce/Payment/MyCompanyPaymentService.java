package main.java.ecommerce.Payment;

import main.java.ecommerce.Customer.Customer;

public class MyCompanyPaymentService implements PaymentService {
	private double profits;

	public MyCompanyPaymentService() {
		profits = 0;
	}

	public double getProfits() {
		return profits;
	}

	public void pay(Customer customer, double payment) {
		if (!customer.canAfford(payment))
			throw new RuntimeException("Invalid payment");
		customer.updateBalance(customer.getBalance() - payment);
		profits += payment;
	}
}
