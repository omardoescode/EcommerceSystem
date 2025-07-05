package main.java.ecommerce.Shipping;

import java.util.List;

import main.java.ecommerce.Order.Order;
import main.java.ecommerce.Product.Product;

public interface ShippingService {
	double shipping_fees(Order ord) throws IllegalArgumentException; // return shipping fees

	void ship(Order ord, String location) throws IllegalArgumentException; // return shipping fees
}
