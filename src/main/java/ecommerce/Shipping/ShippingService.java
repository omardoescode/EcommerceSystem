package main.java.ecommerce.Shipping;

import java.util.ArrayList;

import main.java.ecommerce.Product.Product;

public interface ShippingService {
	double shipping_fees(ArrayList<Product> products) throws IllegalArgumentException; // return shipping fees

	void ship(ArrayList<Product> products) throws IllegalArgumentException; // return shipping fees

}
