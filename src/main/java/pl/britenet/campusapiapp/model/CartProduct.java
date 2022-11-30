package pl.britenet.campusapiapp.model;

public final class CartProduct {

    private int cartId;
    private int productId;
    private int quantity;
    private double productPrice;

    private Cart cart;
    private Product product;

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
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

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "CartProduct {\n\t" +
                "cartId=" + cartId +
                "\n\tproductId=" + productId +
                "\n\tquantity=" + quantity +
                "\n\tproductPrice=" + productPrice +
                "\n\tcart=" + cart +
                "\tproduct=" + product +
                "}\n";
    }
}
