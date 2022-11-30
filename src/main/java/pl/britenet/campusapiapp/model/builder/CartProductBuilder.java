package pl.britenet.campusapiapp.model.builder;

import pl.britenet.campusapiapp.model.Cart;
import pl.britenet.campusapiapp.model.CartProduct;
import pl.britenet.campusapiapp.model.Product;

public class CartProductBuilder {

    private final CartProduct cartProduct;

    public CartProductBuilder(CartProduct cartProduct) {
        this.cartProduct = cartProduct;
    }

    public CartProductBuilder setCartId(int cartId) {
        this.cartProduct.setCartId(cartId);
        return this;
    }

    public CartProductBuilder setProductId(int productId) {
        this.cartProduct.setProductId(productId);
        return this;
    }

    public CartProductBuilder setQuantity(int quantity) {
        this.cartProduct.setQuantity(quantity);
        return this;
    }

    public CartProductBuilder setProductPrice(double productPrice) {
        this.cartProduct.setProductPrice(productPrice);
        return this;
    }

    public CartProductBuilder setCart(Cart cart) {
        this.cartProduct.setCart(cart);
        return this;
    }

    public CartProductBuilder setProduct(Product product) {
        this.cartProduct.setProduct(product);
        return this;
    }

    public CartProduct getCartProduct() {
        return cartProduct;
    }
}
