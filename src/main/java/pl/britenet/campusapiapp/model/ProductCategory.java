package pl.britenet.campusapiapp.model;

public final class ProductCategory {

    private int productId;
    private int categoryId;

    private Product product;
    private Category category;

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "ProductCategory {\n\t" +
                "productId=" + productId +
                "\n\tcategoryId=" + categoryId +
                "\n\tproduct=" + product +
                "\tcategory=" + category +
                "}\n";
    }
}
