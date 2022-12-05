package pl.britenet.campusapiapp.service;

import pl.britenet.campusapiapp.database.DatabaseService;
import pl.britenet.campusapiapp.model.Order;
import pl.britenet.campusapiapp.model.builder.OrderBuilder;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicBoolean;

public class OrderService {

    private final DatabaseService databaseService;

    public OrderService(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    public Order getOrder(int id) {
        String sql = String.format("SELECT * FROM `order` WHERE id = %d", id);

        return this.databaseService.performSQL(sql, resultSet -> {
            try {
                if (resultSet.next()) {
                    return new OrderBuilder(new Order())
                            .setId(resultSet.getInt("id"))
                            .setUserId(resultSet.getInt("userid"))
                            .setOrderDate(resultSet.getDate("orderdate"))
                            .setTotalPrice(resultSet.getDouble("totalprice"))
                            .setShippingAddress(resultSet.getString("shippingaddress"))
                            .setDiscount(resultSet.getDouble("discount"))
                            .setStatus(resultSet.getString("status"))
                            .getOrder();

                }
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
            return null;
        });
    }

    public List<Order> getAll() {
        String sql = "SELECT * FROM `order`";

        return this.databaseService.performSQL(sql, resultSet -> {
            try {
                ArrayList<Order> orderList = new ArrayList<>();
                while (resultSet.next()) {
                    orderList.add(new OrderBuilder(new Order())
                            .setId(resultSet.getInt("id"))
                            .setUserId(resultSet.getInt("userid"))
                            .setOrderDate(resultSet.getDate("orderdate"))
                            .setTotalPrice(resultSet.getDouble("totalprice"))
                            .setShippingAddress(resultSet.getString("shippingaddress"))
                            .setDiscount(resultSet.getDouble("discount"))
                            .setStatus(resultSet.getString("status"))
                            .getOrder());
                }
                return orderList;
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        });
    }

    public List<List<Order>> getPaginated(int rownum) {
        String sql = "SELECT * FROM `order`";
        AtomicBoolean isRunning = new AtomicBoolean(true);

        return this.databaseService.performSQL(sql, resultSet -> {
            try {
                ArrayList<List<Order>> paginatedList = new ArrayList<>();
                while (isRunning.get()) {
                    ArrayList<Order> orderList = new ArrayList<>();
                    for (int i=1; i<=rownum; i++) {
                        if(resultSet.next()) {
                            orderList.add(new OrderBuilder(new Order())
                                    .setId(resultSet.getInt("id"))
                                    .setUserId(resultSet.getInt("userid"))
                                    .setOrderDate(resultSet.getDate("orderdate"))
                                    .setTotalPrice(resultSet.getDouble("totalprice"))
                                    .setShippingAddress(resultSet.getString("shippingaddress"))
                                    .setDiscount(resultSet.getDouble("discount"))
                                    .setStatus(resultSet.getString("status"))
                                    .getOrder());
                        } else isRunning.set(false);
                    }
                    if(!orderList.isEmpty()) paginatedList.add(orderList);
                }
                return paginatedList;
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        });
    }

    public void insertOrder(Order order) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        String dml = String.format(
                Locale.US,
                "INSERT INTO `order` (userid, orderdate, totalprice, shippingaddress, discount, status) " +
                        "VALUES (%d, '%s', %f, '%s', %f, '%s')",
                order.getUserId(),
                formatter.format(order.getOrderDate()),
                order.getTotalPrice(),
                order.getShippingAddress(),
                order.getDiscount(),
                order.getStatus()
        );
        this.databaseService.performDML(dml);
    }

    public void updateOrder(Order order) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        String dml = String.format(
                Locale.US,
                "UPDATE `order` SET " +
                        "userid = %d, " +
                        "orderdate = '%s', " +
                        "totalprice = %f, " +
                        "shippingaddress = '%s', " +
                        "discount = %f, " +
                        "status = '%s' " +
                        "WHERE id = %d",
                order.getUserId(),
                formatter.format(order.getOrderDate()),
                order.getTotalPrice(),
                order.getShippingAddress(),
                order.getDiscount(),
                order.getStatus(),
                order.getId()
        );
        this.databaseService.performDML(dml);
    }

    public void deleteOrder(int id) {
        String dml = String.format("DELETE FROM `order` WHERE id = %d", id);
        this.databaseService.performDML(dml);
    }
}
