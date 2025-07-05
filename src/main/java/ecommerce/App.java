package ecommerce;

import main.java.ecommerce.Customer.Customer;
import main.java.ecommerce.Inventory.Inventory;
import main.java.ecommerce.Order.MyCompanyOrderService;
import main.java.ecommerce.Order.OrderService;
import main.java.ecommerce.Payment.PaymentService;
import main.java.ecommerce.Payment.MyCompanyPaymentService;
import main.java.ecommerce.Shipping.MyCompanyShippingService;
import main.java.ecommerce.Product.ProductBuilder;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import main.java.ecommerce.Cart.Cart;
import main.java.ecommerce.Cart.CartItem;

/**
 * Hello world!
 */
public class App {
	public static void main(String[] args) {
		// This is an example of how to use this API
		// Now, this is how an admin will add products
		var inventory = new Inventory();

		var cheese = new ProductBuilder().setName("Cheese").setQuantity(10).setPrice(100).build();
		var milk = new ProductBuilder().setName("Milk").setQuantity(50).setPrice(100).build();
		var expired_milk = new ProductBuilder().setName("Expired Milk").setQuantity(50).setPrice(100)
				.setExpirable(Date.from(LocalDate.of(2025, 1, 1).atStartOfDay(ZoneId.systemDefault()).toInstant()))
				.build();
		var tv = new ProductBuilder().setName("TV").setQuantity(50).setPrice(100).setShippable(100.0).build();

		// let's add these to inventory
		inventory.addProduct(cheese);
		inventory.addProduct(milk);
		inventory.addProduct(expired_milk);
		inventory.addProduct(tv);

		// Let's look at inventory after these orders
		System.out.println("-------- Inventory ---------");
		inventory.report(System.out);

		// Now, we have some expired stuff, let's remvoe them
		inventory.removeExpired();

		System.out.println("-------- Inventory (removing expired milk) ---------");
		inventory.report(System.out);

		// Now a customer will login (on some other layer, or take from DB if existent)
		var customer = new Customer("omardoescode", "Cairo, Egypt", 5000);

		// Now, the company services initialize
		var paymentservice = new MyCompanyPaymentService();
		var shippingservice = new MyCompanyShippingService(10, inventory);
		MyCompanyOrderService orderService = new MyCompanyOrderService(paymentservice, shippingservice, inventory);

		// Now the customer will add some stuff to his cart
		Cart cart = customer.getCart();
		cart.addItem(new CartItem(cheese.getIdentifier(), 10));
		cart.addItem(new CartItem(cheese.getIdentifier(), 5)); // add the same item
		cart.addItem(new CartItem(milk.getIdentifier(), 20));

		// Now we can print the cart information
		System.out.println("-------- Inventory ---------");
		cart.report(System.out);

		// Now, it's about time to do the order
		orderService.checkout(customer, false);

		// Let's place another order
		cart.addItem(new CartItem(tv.getIdentifier(), 10));
		orderService.checkout(customer, true); // Let's ship this TV

		// Let's see the order history of the customers
		System.out.println("-------- Orders -------");
		for (var ord : customer.getOrderHistory()) {
			System.out.printf("- Order: %s\n", ord.getOrderId());
			for (var item : ord.getItems())
				System.out.printf("\t- Product ID: %s, Quantity: %d\n", item.productIdentifier(), item.quantity());
		}

		// Let's have the admin look at the profits
		System.out.println("-------- Profit ---------");
		System.out.printf("[admin] Profits are %f\n", paymentservice.getProfits());

		// Let's look at inventory after these orders
		System.out.println("-------- Inventory after orders ---------");
		inventory.report(System.out);
	}
}
