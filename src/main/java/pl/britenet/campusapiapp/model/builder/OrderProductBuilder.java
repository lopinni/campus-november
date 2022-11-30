package pl.britenet.campusapiapp.model.builder;

import pl.britenet.campusapiapp.model.Order;
import pl.britenet.campusapiapp.model.OrderProduct;
import pl.britenet.campusapiapp.model.Product;

public class OrderProductBuilder {

    private final OrderProduct orderProduct;

    public OrderProductBuilder(OrderProduct orderProduct) {
        this.orderProduct = orderProduct;
    }

    public OrderProductBuilder setOrderId(int orderId) {
        this.orderProduct.setOrderId(orderId);
        return this;
    }

    public OrderProductBuilder setProductId(int productId) {
        this.orderProduct.setProductId(productId);
        return this;
    }

    public OrderProductBuilder setQuantity(int quantity) {
        this.orderProduct.setQuantity(quantity);
        return this;
    }

    public OrderProductBuilder setProductPrice(double productPrice) {
        this.orderProduct.setProductPrice(productPrice);
        return this;
    }

    public OrderProductBuilder setOrder(Order order) {
        this.orderProduct.setOrder(order);
        return this;
    }

    public OrderProductBuilder setProduct(Product product) {
        this.orderProduct.setProduct(product);
        return this;
    }

    public OrderProduct getOrderProduct() {
        return orderProduct;
    }
}
