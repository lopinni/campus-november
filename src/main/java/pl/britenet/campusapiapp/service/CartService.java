package pl.britenet.campusapiapp.service;

import pl.britenet.campusapiapp.database.DatabaseService;
import pl.britenet.campusapiapp.model.Cart;
import pl.britenet.campusapiapp.model.builder.CartBuilder;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicBoolean;

public class CartService {
    private final DatabaseService databaseService;

    public CartService(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    public Cart getCart(int id) {
        String sql = String.format("SELECT * FROM cart WHERE id = %d", id);

        return this.databaseService.performSQL(sql, resultSet -> {
            try {
                if (resultSet.next()) {
                    return new CartBuilder(new Cart())
                            .setId(resultSet.getInt("id"))
                            .setUserId(resultSet.getInt("userid"))
                            .setOrderDate(resultSet.getDate("orderdate"))
                            .setTotalPrice(resultSet.getDouble("totalprice"))
                            .setDiscount(resultSet.getDouble("discount"))
                            .getCart();
                }
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
            return null;
        });
    }

    public List<Cart> getAll() {
        String sql = "SELECT * FROM cart";

        return this.databaseService.performSQL(sql, resultSet -> {
            try {
                ArrayList<Cart> cartList = new ArrayList<>();
                while (resultSet.next()) {
                    cartList.add(new CartBuilder(new Cart())
                            .setId(resultSet.getInt("id"))
                            .setUserId(resultSet.getInt("userid"))
                            .setOrderDate(resultSet.getDate("orderdate"))
                            .setTotalPrice(resultSet.getDouble("totalprice"))
                            .setDiscount(resultSet.getDouble("discount"))
                            .getCart());
                }
                return cartList;
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        });
    }

    public List<List<Cart>> getPaginated(int rownum) {
        String sql = "SELECT * FROM cart";
        AtomicBoolean isRunning = new AtomicBoolean(true);

        return this.databaseService.performSQL(sql, resultSet -> {
            try {
                ArrayList<List<Cart>> paginatedList = new ArrayList<>();
                while (isRunning.get()) {
                    ArrayList<Cart> cartList = new ArrayList<>();
                    for (int i=1; i<=rownum; i++) {
                        if(resultSet.next()) {
                            cartList.add(new CartBuilder(new Cart())
                                    .setId(resultSet.getInt("id"))
                                    .setUserId(resultSet.getInt("userid"))
                                    .setOrderDate(resultSet.getDate("orderdate"))
                                    .setTotalPrice(resultSet.getDouble("totalprice"))
                                    .setDiscount(resultSet.getDouble("discount"))
                                    .getCart());
                        } else isRunning.set(false);
                    }
                    if(!cartList.isEmpty()) paginatedList.add(cartList);
                }
                return paginatedList;
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        });
    }

    public void insertCart(Cart cart) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        String dml = String.format(
                Locale.US,
                "INSERT INTO cart (userid, orderdate, totalprice, discount) " +
                        "VALUES (%d, '%s', %f, %f)",
                cart.getUserId(),
                formatter.format(cart.getOrderDate()),
                cart.getTotalPrice(),
                cart.getDiscount()
        );
        System.out.println(dml);
        this.databaseService.performDML(dml);
    }

    public void updateCart(Cart cart) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        String dml = String.format(
                Locale.US,
                "UPDATE cart " +
                        "SET userid = %d, orderdate = '%s', totalprice = %f, discount = %f" +
                        "WHERE id = %d",
                cart.getUserId(),
                formatter.format(cart.getOrderDate()),
                cart.getTotalPrice(),
                cart.getDiscount(),
                cart.getId()
        );
        this.databaseService.performDML(dml);
    }

    public void deleteCart(int id) {
        String dml = String.format("DELETE FROM cart WHERE id = %d", id);
        this.databaseService.performDML(dml);
    }
}
