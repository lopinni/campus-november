package pl.britenet.campusapiapp.model;

public final class OrderProduct {

    private int orderId;
    private int productId;
    private int quantity;
    private double productPrice;

    private Order order;
    private Product product;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "OrderProduct {\n\t" +
                "orderId=" + orderId +
                "\n\tproductId=" + productId +
                "\n\tquantity=" + quantity +
                "\n\tproductPrice=" + productPrice +
                "\n\torder=" + order +
                "\tproduct=" + product +
                "}\n";
    }
}
