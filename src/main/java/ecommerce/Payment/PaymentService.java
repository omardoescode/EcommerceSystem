package main.java.ecommerce.Payment;

import main.java.ecommerce.Customer.Customer;
import main.java.ecommerce.Order.Order;

public interface PaymentService {
	void pay(Customer customer, double payment) throws RuntimeException;
}
