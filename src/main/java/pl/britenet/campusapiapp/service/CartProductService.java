package pl.britenet.campusapiapp.service;

import pl.britenet.campusapiapp.database.DatabaseService;
import pl.britenet.campusapiapp.model.*;
import pl.britenet.campusapiapp.model.builder.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicBoolean;

public class CartProductService {

    private final DatabaseService databaseService;

    public CartProductService(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    public CartProduct getCartProduct(int cartId, int productId) {
        String sql = String.format(
                "SELECT * FROM cartproduct cp JOIN cart c ON cp.cartid = c.id JOIN product p ON cp.productid = p.id " +
                        "WHERE cartid = %d AND productid = %d;",
                cartId, productId
        );

        return this.databaseService.performSQL(sql, resultSet -> {
            try {
                if (resultSet.next()) {
                    return new CartProductBuilder(new CartProduct())
                            .setCartId(resultSet.getInt("cartid"))
                            .setProductId(resultSet.getInt("productid"))
                            .setQuantity(resultSet.getInt("quantity"))
                            .setProductPrice(resultSet.getDouble("productprice"))
                            .setCart(new CartBuilder(new Cart())
                                    .setId(resultSet.getInt("cartid"))
                                    .setUserId(resultSet.getInt("userid"))
                                    .setOrderDate(resultSet.getDate("orderdate"))
                                    .setTotalPrice(resultSet.getDouble("totalprice"))
                                    .setDiscount(resultSet.getDouble("discount"))
                                    .getCart())
                            .setProduct(new ProductBuilder(new Product())
                                    .setId(resultSet.getInt("productid"))
                                    .setName(resultSet.getString("name"))
                                    .setPrice(resultSet.getDouble("price"))
                                    .setDescription(resultSet.getString("description"))
                                    .setImagePath(resultSet.getString("imagepath"))
                                    .getProduct())
                            .getCartProduct();
                }
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
            return null;
        });
    }

    public List<CartProduct> getAll() {
        String sql = "SELECT * FROM cartproduct cp JOIN cart c ON cp.cartid = c.id JOIN product p ON cp.productid = p.id;";

        return this.databaseService.performSQL(sql, resultSet -> {
            try {
                ArrayList<CartProduct> cartProductList = new ArrayList<>();
                while (resultSet.next()) {
                    cartProductList.add(new CartProductBuilder(new CartProduct())
                            .setCartId(resultSet.getInt("cartid"))
                            .setProductId(resultSet.getInt("productid"))
                            .setQuantity(resultSet.getInt("quantity"))
                            .setProductPrice(resultSet.getDouble("productprice"))
                            .setCart(new CartBuilder(new Cart())
                                    .setId(resultSet.getInt("cartid"))
                                    .setUserId(resultSet.getInt("userid"))
                                    .setOrderDate(resultSet.getDate("orderdate"))
                                    .setTotalPrice(resultSet.getDouble("totalprice"))
                                    .setDiscount(resultSet.getDouble("discount"))
                                    .getCart())
                            .setProduct(new ProductBuilder(new Product())
                                    .setId(resultSet.getInt("productid"))
                                    .setName(resultSet.getString("name"))
                                    .setPrice(resultSet.getDouble("price"))
                                    .setDescription(resultSet.getString("description"))
                                    .setImagePath(resultSet.getString("imagepath"))
                                    .getProduct())
                            .getCartProduct());
                }
                return cartProductList;
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        });
    }

    public List<List<CartProduct>> getPaginated(int rownum) {
        String sql =
                "SELECT * FROM cartproduct op " +
                        "JOIN `cart` o ON op.cartid = o.id JOIN product p ON op.productid = p.id;";

        AtomicBoolean isRunning = new AtomicBoolean(true);

        return this.databaseService.performSQL(sql, resultSet -> {
            try {
                ArrayList<List<CartProduct>> paginatedList = new ArrayList<>();
                while (isRunning.get()) {
                    ArrayList<CartProduct> cartProductList = new ArrayList<>();
                    for (int i=1; i<=rownum; i++) {
                        if(resultSet.next()) {
                            cartProductList.add(new CartProductBuilder(new CartProduct())
                                    .setCartId(resultSet.getInt("cartid"))
                                    .setProductId(resultSet.getInt("productid"))
                                    .setQuantity(resultSet.getInt("quantity"))
                                    .setProductPrice(resultSet.getDouble("productprice"))
                                    .setCart(new CartBuilder(new Cart())
                                            .setId(resultSet.getInt("cartid"))
                                            .setUserId(resultSet.getInt("userid"))
                                            .setOrderDate(resultSet.getDate("orderdate"))
                                            .setTotalPrice(resultSet.getDouble("totalprice"))
                                            .setDiscount(resultSet.getDouble("discount"))
                                            .getCart())
                                    .setProduct(new ProductBuilder(new Product())
                                            .setId(resultSet.getInt("productid"))
                                            .setName(resultSet.getString("name"))
                                            .setPrice(resultSet.getDouble("price"))
                                            .setDescription(resultSet.getString("description"))
                                            .setImagePath(resultSet.getString("imagepath"))
                                            .getProduct())
                                    .getCartProduct());
                        } else isRunning.set(false);
                    }
                    if(!cartProductList.isEmpty()) paginatedList.add(cartProductList);
                }
                return paginatedList;
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        });
    }

    public void insertCartProduct(CartProduct cartProduct) {
        String dml = String.format(
                Locale.US,
                "INSERT INTO cartproduct VALUES (%d, %d, %d, %f)",
                cartProduct.getCartId(),
                cartProduct.getProductId(),
                cartProduct.getQuantity(),
                cartProduct.getProductPrice()
        );
        this.databaseService.performDML(dml);
    }

    public void updateCartProduct(int cartId, int productId, CartProduct cartProduct) {
        String dml = String.format(
                Locale.US,
                "UPDATE cartproduct SET " +
                        "cartid = %d, " +
                        "productid = %d, " +
                        "quantity = %d, " +
                        "productprice = %f " +
                        "WHERE cartid = %d AND productid = %d",
                cartProduct.getCartId(),
                cartProduct.getProductId(),
                cartProduct.getQuantity(),
                cartProduct.getProductPrice(),
                cartId, productId
        );
        this.databaseService.performDML(dml);
    }

    public void deleteCartProduct(int cartId, int productId) {
        String dml = String.format(
                "DELETE FROM cartproduct WHERE cartid = %d AND productid = %d",
                cartId, productId
        );
        this.databaseService.performDML(dml);
    }

}
