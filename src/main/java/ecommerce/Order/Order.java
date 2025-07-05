package main.java.ecommerce.Order;

import java.util.List;

public class Order {
	private String orderId;
	private String customerUsername;
	private final List<OrderItem> items;
	private OrderStatus status;

	public Order(String orderId, String customerUsername, List<OrderItem> items) {
		this.orderId = orderId;
		this.customerUsername = customerUsername;
		this.items = items;
		this.status = OrderStatus.PENDING;
	}

	void setStatus(OrderStatus status) {
		this.status = status;
	}

	public String getCustomerUsername() {
		return customerUsername;
	}

	public String getOrderId() {
		return orderId;
	}

	public List<OrderItem> getItems() {
		return items;
	}

	public OrderStatus getStatus() {
		return status;
	}
}
