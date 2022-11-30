package pl.britenet.campusapiapp.model.builder;

import pl.britenet.campusapiapp.model.Product;

public class ProductBuilder {

    private final Product product;

    public ProductBuilder(Product product) {
        this.product = product;
    }

    public ProductBuilder setId(int id) {
        this.product.setId(id);
        return this;
    }

    public ProductBuilder setName(String name) {
        this.product.setName(name);
        return this;
    }

    public ProductBuilder setPrice(double price) {
        this.product.setPrice(price);
        return this;
    }

    public ProductBuilder setDescription(String description) {
        this.product.setDescription(description);
        return this;
    }

    public ProductBuilder setImagePath(String imagePath) {
        this.product.setImagePath(imagePath);
        return this;
    }

    public Product getProduct() {
        return product;
    }
}
