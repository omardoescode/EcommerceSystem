package main.java.ecommerce.Order;

import java.util.function.Supplier;
import java.util.List;

import main.java.ecommerce.Cart.Cart;
import main.java.ecommerce.Product.Product;
import main.java.ecommerce.Customer.Customer;
import main.java.ecommerce.Inventory.Inventory;
import main.java.ecommerce.Payment.PaymentService;
import main.java.ecommerce.Shipping.ShippingService;

public class MyCompanyOrderService implements OrderService {
	private int orderId = 0;
	private final PaymentService paymentService;
	private final ShippingService shippingService;
	private final Inventory inv;

	public MyCompanyOrderService(PaymentService paymentService, ShippingService shippingService, Inventory inv) {
		this.shippingService = shippingService;
		this.paymentService = paymentService;
		this.inv = inv;
	}

	@Override
	public void checkout(Customer customer, boolean ship) throws RuntimeException {
		Cart cart = customer.getCart();
		var items = cart.getItems();

		// Convert to List<OrderItem>
		var orderItems = items.stream()
				.map(cartItem -> new OrderItem(cartItem.productIdentifier(), cartItem.quantity())).toList();

		// Place the order
		String newOrderId = Integer.toString(orderId++);
		Order ord = new Order(newOrderId, customer.getUsername(), orderItems);
		ord.setStatus(OrderStatus.PROCESSING);

		// Make sure inventory has all items
		double payment = inv.getOrderProducts(ord).stream().mapToDouble(Product::getTotalPrice).sum();

		// Deduct the payment money
		if (ship)
			payment += shippingService.shipping_fees(ord);
		paymentService.pay(customer, payment);

		// Deduct the items from the inventory & cart
		inv.process(ord);
		cart.clear();

		// Add order to history
		ord.setStatus(OrderStatus.CONFIRMED);
		customer.addOrder(ord);

		// shipping if needed
		if (ship)
			shippingService.ship(ord, customer.getAddress());
	}
}
