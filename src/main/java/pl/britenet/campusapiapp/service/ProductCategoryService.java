package pl.britenet.campusapiapp.service;

import pl.britenet.campusapiapp.database.DatabaseService;
import pl.britenet.campusapiapp.model.Category;
import pl.britenet.campusapiapp.model.Product;
import pl.britenet.campusapiapp.model.ProductCategory;
import pl.britenet.campusapiapp.model.builder.CategoryBuilder;
import pl.britenet.campusapiapp.model.builder.ProductBuilder;
import pl.britenet.campusapiapp.model.builder.ProductCategoryBuilder;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicBoolean;

public class ProductCategoryService {

    private final DatabaseService databaseService;

    public ProductCategoryService(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    public ProductCategory getProductCategory(int productId, int categoryId) {
        String sql = String.format(
                "SELECT pc.productid, p.name AS pname, p.price, p.description, p.imagepath, pc.categoryid, c.name AS cname, c.imagepath " +
                        "FROM productcategory pc JOIN product p ON pc.productid = p.id JOIN category c ON pc.categoryid = c.id " +
                        "WHERE pc.productid = %d AND pc.categoryid = %d;",
                productId, categoryId
        );

        return this.databaseService.performSQL(sql, resultSet -> {
            try {
                if (resultSet.next()) {
                    return new ProductCategoryBuilder(new ProductCategory())
                            .setProductId(resultSet.getInt("productid"))
                            .setCategoryId(resultSet.getInt("categoryid"))
                            .setProduct(new ProductBuilder(new Product())
                                    .setId(resultSet.getInt("productid"))
                                    .setName(resultSet.getString("pname"))
                                    .setPrice(resultSet.getDouble("price"))
                                    .setDescription(resultSet.getString("description"))
                                    .setImagePath(resultSet.getString("imagepath"))
                                    .getProduct())
                            .setCategory(new CategoryBuilder(new Category())
                                    .setId(resultSet.getInt("categoryid"))
                                    .setName(resultSet.getString("cname"))
                                    .setImagePath(resultSet.getString("imagepath"))
                                    .getCategory())
                            .getProductCategory();

                }
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
            return null;
        });
    }

    public List<ProductCategory> getAll() {
        String sql =
                "SELECT pc.productid, p.name AS pname, p.price, p.description, p.imagepath, pc.categoryid, c.name AS cname, c.imagepath " +
                        "FROM productcategory pc JOIN product p ON pc.productid = p.id JOIN category c ON pc.categoryid = c.id";

        return this.databaseService.performSQL(sql, resultSet -> {
            try {
                ArrayList<ProductCategory> productCategoryList = new ArrayList<>();
                while (resultSet.next()) {
                    productCategoryList.add(new ProductCategoryBuilder(new ProductCategory())
                            .setProductId(resultSet.getInt("productid"))
                            .setCategoryId(resultSet.getInt("categoryid"))
                            .setProduct(new ProductBuilder(new Product())
                                    .setId(resultSet.getInt("productid"))
                                    .setName(resultSet.getString("pname"))
                                    .setPrice(resultSet.getDouble("price"))
                                    .setDescription(resultSet.getString("description"))
                                    .setImagePath(resultSet.getString("imagepath"))
                                    .getProduct())
                            .setCategory(new CategoryBuilder(new Category())
                                    .setId(resultSet.getInt("categoryid"))
                                    .setName(resultSet.getString("cname"))
                                    .setImagePath(resultSet.getString("imagepath"))
                                    .getCategory())
                            .getProductCategory());
                }
                return productCategoryList;
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        });
    }

    public List<List<ProductCategory>> getPaginated(int rownum) {
        String sql =
                "SELECT pc.productid, p.name AS pname, p.price, p.description, p.imagepath, pc.categoryid, c.name AS cname, c.imagepath " +
                        "FROM productcategory pc JOIN product p ON pc.productid = p.id JOIN category c ON pc.categoryid = c.id";

        AtomicBoolean isRunning = new AtomicBoolean(true);

        return this.databaseService.performSQL(sql, resultSet -> {
            try {
                ArrayList<List<ProductCategory>> paginatedList = new ArrayList<>();
                while (isRunning.get()) {
                    ArrayList<ProductCategory> productCategoryList = new ArrayList<>();
                    for (int i=1; i<=rownum; i++) {
                        if(resultSet.next()) {
                            productCategoryList.add(new ProductCategoryBuilder(new ProductCategory())
                                    .setProductId(resultSet.getInt("productid"))
                                    .setCategoryId(resultSet.getInt("categoryid"))
                                    .setProduct(new ProductBuilder(new Product())
                                            .setId(resultSet.getInt("productid"))
                                            .setName(resultSet.getString("pname"))
                                            .setPrice(resultSet.getDouble("price"))
                                            .setDescription(resultSet.getString("description"))
                                            .setImagePath(resultSet.getString("imagepath"))
                                            .getProduct())
                                    .setCategory(new CategoryBuilder(new Category())
                                            .setId(resultSet.getInt("categoryid"))
                                            .setName(resultSet.getString("cname"))
                                            .setImagePath(resultSet.getString("imagepath"))
                                            .getCategory())
                                    .getProductCategory());
                        } else isRunning.set(false);
                    }
                    if(!productCategoryList.isEmpty()) paginatedList.add(productCategoryList);
                }
                return paginatedList;
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        });
    }

    public void insertProductCategory(ProductCategory productCategory) {
        String dml = String.format(
                Locale.US,
                "INSERT INTO productcategory VALUES (%d, %d)",
                productCategory.getCategoryId(),
                productCategory.getProductId()
        );
        this.databaseService.performDML(dml);
    }

    public void updateProductCategory(int productId, int categoryId, ProductCategory productCategory) {
        String dml = String.format(
                Locale.US,
                "UPDATE productcategory SET categoryid = %d, productid = %d WHERE categoryid = %d AND productid = %d",
                productCategory.getCategoryId(),
                productCategory.getProductId(),
                categoryId,
                productId
        );
        this.databaseService.performDML(dml);
    }

    public void deleteProductCategory(int productId, int categoryId) {
        String dml = String.format(
                "DELETE FROM productcategory WHERE categoryid = %d AND productid = %d",
                categoryId, productId
        );
        System.out.println(dml);
        this.databaseService.performDML(dml);
    }
}
