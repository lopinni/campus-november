package pl.britenet.campusapiapp.model.builder;

import pl.britenet.campusapiapp.model.Cart;

import java.util.Date;

public class CartBuilder {

    private final Cart cart;

    public CartBuilder(Cart cart) {
        this.cart = cart;
    }

    public CartBuilder setId(int id) {
        this.cart.setId(id);
        return this;
    }

    public CartBuilder setUserId(int userId) {
        this.cart.setUserId(userId);
        return this;
    }

    public CartBuilder setOrderDate(Date orderDate) {
        this.cart.setOrderDate(orderDate);
        return this;
    }

    public CartBuilder setTotalPrice(double totalPrice) {
        this.cart.setTotalPrice(totalPrice);
        return this;
    }

    public CartBuilder setDiscount(double discount) {
        this.cart.setDiscount(discount);
        return this;
    }

    public Cart getCart() {
        return cart;
    }
}
