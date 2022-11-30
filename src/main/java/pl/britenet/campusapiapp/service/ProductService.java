package pl.britenet.campusapiapp.service;

import pl.britenet.campusapiapp.database.DatabaseService;
import pl.britenet.campusapiapp.model.Category;
import pl.britenet.campusapiapp.model.Product;
import pl.britenet.campusapiapp.model.builder.CategoryBuilder;
import pl.britenet.campusapiapp.model.builder.ProductBuilder;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicBoolean;

public class ProductService {

    private final DatabaseService databaseService;

    public ProductService(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    public Product getProduct(int id) {
        String sql = String.format("SELECT * FROM product WHERE id = %d", id);

        return this.databaseService.performSQL(sql, resultSet -> {
            try {
                if (resultSet.next()) {
                    return new ProductBuilder(new Product())
                            .setId(resultSet.getInt("id"))
                            .setName(resultSet.getString("name"))
                            .setDescription(resultSet.getString("description"))
                            .setImagePath(resultSet.getString("imagepath"))
                            .setPrice(resultSet.getDouble("price"))
                            .getProduct();

                }
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
            return null;
        });
    }

    public List<Product> getAll() {
        String sql = "SELECT * FROM product";

        return this.databaseService.performSQL(sql, resultSet -> {
            try {
                ArrayList<Product> productList = new ArrayList<>();
                while (resultSet.next()) {
                    productList.add(new ProductBuilder(new Product())
                            .setId(resultSet.getInt("id"))
                            .setName(resultSet.getString("name"))
                            .setDescription(resultSet.getString("description"))
                            .setImagePath(resultSet.getString("imagepath"))
                            .setPrice(resultSet.getDouble("price"))
                            .getProduct());
                }
                return productList;
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        });
    }

    public List<List<Product>> getPaginated(int rownum) {
        String sql = "SELECT * FROM product";
        AtomicBoolean isRunning = new AtomicBoolean(true);

        return this.databaseService.performSQL(sql, resultSet -> {
            try {
                ArrayList<List<Product>> paginatedList = new ArrayList<>();
                while (isRunning.get()) {
                    ArrayList<Product> productList = new ArrayList<>();
                    for (int i=1; i<=rownum; i++) {
                        if(resultSet.next()) {
                            productList.add(new ProductBuilder(new Product())
                                    .setId(resultSet.getInt("id"))
                                    .setName(resultSet.getString("name"))
                                    .setDescription(resultSet.getString("description"))
                                    .setImagePath(resultSet.getString("imagepath"))
                                    .setPrice(resultSet.getDouble("price"))
                                    .getProduct());
                        } else isRunning.set(false);
                    }
                    if(!productList.isEmpty()) paginatedList.add(productList);
                }
                return paginatedList;
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        });
    }

    public void insertProduct(Product product) {
        String dml = String.format(
                Locale.US,
                "INSERT INTO product (name, price, description, imagepath) VALUES ('%s', %f, '%s', '%s')",
                product.getName(),
                product.getPrice(),
                product.getDescription(),
                product.getImagePath()
        );
        this.databaseService.performDML(dml);
    }

    public void updateProduct(Product product) {
        String dml = String.format(
                Locale.US,
                "UPDATE product SET name = '%s', price = %f, description = '%s', imagepath = '%s' WHERE id = %d",
                product.getName(),
                product.getPrice(),
                product.getDescription(),
                product.getImagePath(),
                product.getId()
        );
        this.databaseService.performDML(dml);
    }

    public void deleteProduct(int id) {
        String dml = String.format("DELETE FROM product WHERE id = %d", id);
        this.databaseService.performDML(dml);
    }
}
