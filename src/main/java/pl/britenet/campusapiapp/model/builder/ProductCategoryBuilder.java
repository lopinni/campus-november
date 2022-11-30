package pl.britenet.campusapiapp.model.builder;

import pl.britenet.campusapiapp.model.Category;
import pl.britenet.campusapiapp.model.Product;
import pl.britenet.campusapiapp.model.ProductCategory;

public class ProductCategoryBuilder {

    private final ProductCategory productCategory;

    public ProductCategoryBuilder(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    public ProductCategoryBuilder setProductId(int productId) {
        this.productCategory.setProductId(productId);
        return this;
    }

    public ProductCategoryBuilder setCategoryId(int categoryId) {
        this.productCategory.setCategoryId(categoryId);
        return this;
    }

    public ProductCategoryBuilder setProduct(Product product) {
        this.productCategory.setProduct(product);
        return this;
    }

    public ProductCategoryBuilder setCategory(Category category) {
        this.productCategory.setCategory(category);
        return this;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }
}
