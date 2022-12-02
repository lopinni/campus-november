package pl.britenet.campusapiapp.model.builder;

import pl.britenet.campusapiapp.model.Order;

import java.util.Date;

public class OrderBuilder {

    private final Order order;

    public OrderBuilder(Order order) {
        this.order = order;
    }

    public OrderBuilder setId(int id) {
        this.order.setId(id);
        return this;
    }

    public OrderBuilder setUserId(int userId) {
        this.order.setUserId(userId);
        return this;
    }

    public OrderBuilder setOrderDate(Date orderDate) {
        this.order.setOrderDate(orderDate);
        return this;
    }

    public OrderBuilder setTotalPrice(double totalPrice) {
        this.order.setTotalPrice(totalPrice);
        return this;
    }

    public OrderBuilder setShippingAddress(String shippingAddress) {
        this.order.setShippingAddress(shippingAddress);
        return this;
    }

    public OrderBuilder setDiscount(double discount) {
        this.order.setDiscount(discount);
        return this;
    }

    public OrderBuilder setStatus(String status) {
        this.order.setStatus(status);
        return this;
    }

    public Order getOrder() {
        return order;
    }
}
