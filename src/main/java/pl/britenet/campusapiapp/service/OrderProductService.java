package pl.britenet.campusapiapp.service;

import pl.britenet.campusapiapp.database.DatabaseService;
import pl.britenet.campusapiapp.model.Order;
import pl.britenet.campusapiapp.model.OrderProduct;
import pl.britenet.campusapiapp.model.Product;
import pl.britenet.campusapiapp.model.builder.OrderBuilder;
import pl.britenet.campusapiapp.model.builder.OrderProductBuilder;
import pl.britenet.campusapiapp.model.builder.ProductBuilder;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicBoolean;

public class OrderProductService {

    private final DatabaseService databaseService;

    public OrderProductService(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    public OrderProduct getOrderProduct(int orderId, int productId) {
        String sql = String.format(
                "SELECT * FROM orderproduct op " +
                        "JOIN `order` o ON op.orderid = o.id JOIN product p ON op.productid = p.id " +
                        "WHERE op.orderid = %d AND op.productid = %d;",
                orderId, productId
        );

        return this.databaseService.performSQL(sql, resultSet -> {
            try {
                if (resultSet.next()) {
                    return new OrderProductBuilder(new OrderProduct())
                            .setOrderId(resultSet.getInt("orderid"))
                            .setProductId(resultSet.getInt("productid"))
                            .setQuantity(resultSet.getInt("quantity"))
                            .setProductPrice(resultSet.getDouble("productprice"))
                            .setOrder(new OrderBuilder(new Order())
                                    .setId(resultSet.getInt("orderid"))
                                    .setUserId(resultSet.getInt("userid"))
                                    .setOrderDate(resultSet.getDate("orderdate"))
                                    .setTotalPrice(resultSet.getDouble("totalprice"))
                                    .setShippingAddress(resultSet.getString("shippingaddress"))
                                    .setDiscount(resultSet.getDouble("discount"))
                                    .setStatus(resultSet.getString("status"))
                                    .getOrder())
                            .setProduct(new ProductBuilder(new Product())
                                    .setId(resultSet.getInt("productid"))
                                    .setName(resultSet.getString("name"))
                                    .setPrice(resultSet.getDouble("price"))
                                    .setDescription(resultSet.getString("description"))
                                    .setImagePath(resultSet.getString("imagepath"))
                                    .getProduct())
                            .getOrderProduct();
                }
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
            return null;
        });
    }

    public List<OrderProduct> getAll() {
        String sql =
                "SELECT * FROM orderproduct op " +
                        "JOIN `order` o ON op.orderid = o.id JOIN product p ON op.productid = p.id;";

        return this.databaseService.performSQL(sql, resultSet -> {
            try {
                ArrayList<OrderProduct> orderProductList = new ArrayList<>();
                while (resultSet.next()) {
                    orderProductList.add(new OrderProductBuilder(new OrderProduct())
                            .setOrderId(resultSet.getInt("orderid"))
                            .setProductId(resultSet.getInt("productid"))
                            .setQuantity(resultSet.getInt("quantity"))
                            .setProductPrice(resultSet.getDouble("productprice"))
                            .setOrder(new OrderBuilder(new Order())
                                    .setId(resultSet.getInt("orderid"))
                                    .setUserId(resultSet.getInt("userid"))
                                    .setOrderDate(resultSet.getDate("orderdate"))
                                    .setTotalPrice(resultSet.getDouble("totalprice"))
                                    .setShippingAddress(resultSet.getString("shippingaddress"))
                                    .setDiscount(resultSet.getDouble("discount"))
                                    .setStatus(resultSet.getString("status"))
                                    .getOrder())
                            .setProduct(new ProductBuilder(new Product())
                                    .setId(resultSet.getInt("productid"))
                                    .setName(resultSet.getString("name"))
                                    .setPrice(resultSet.getDouble("price"))
                                    .setDescription(resultSet.getString("description"))
                                    .setImagePath(resultSet.getString("imagepath"))
                                    .getProduct())
                            .getOrderProduct());
                }
                return orderProductList;
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        });
    }

    public List<List<OrderProduct>> getPaginated(int rownum) {
        String sql =
                "SELECT * FROM orderproduct op " +
                        "JOIN `order` o ON op.orderid = o.id JOIN product p ON op.productid = p.id;";

        AtomicBoolean isRunning = new AtomicBoolean(true);

        return this.databaseService.performSQL(sql, resultSet -> {
            try {
                ArrayList<List<OrderProduct>> paginatedList = new ArrayList<>();
                while (isRunning.get()) {
                    ArrayList<OrderProduct> orderProductList = new ArrayList<>();
                    for (int i=1; i<=rownum; i++) {
                        if(resultSet.next()) {
                            orderProductList.add(new OrderProductBuilder(new OrderProduct())
                                    .setOrderId(resultSet.getInt("orderid"))
                                    .setProductId(resultSet.getInt("productid"))
                                    .setQuantity(resultSet.getInt("quantity"))
                                    .setProductPrice(resultSet.getDouble("productprice"))
                                    .setOrder(new OrderBuilder(new Order())
                                            .setId(resultSet.getInt("orderid"))
                                            .setUserId(resultSet.getInt("userid"))
                                            .setOrderDate(resultSet.getDate("orderdate"))
                                            .setTotalPrice(resultSet.getDouble("totalprice"))
                                            .setShippingAddress(resultSet.getString("shippingaddress"))
                                            .setDiscount(resultSet.getDouble("discount"))
                                            .setStatus(resultSet.getString("status"))
                                            .getOrder())
                                    .setProduct(new ProductBuilder(new Product())
                                            .setId(resultSet.getInt("productid"))
                                            .setName(resultSet.getString("name"))
                                            .setPrice(resultSet.getDouble("price"))
                                            .setDescription(resultSet.getString("description"))
                                            .setImagePath(resultSet.getString("imagepath"))
                                            .getProduct())
                                    .getOrderProduct());
                        } else isRunning.set(false);
                    }
                    if(!orderProductList.isEmpty()) paginatedList.add(orderProductList);
                }
                return paginatedList;
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        });
    }

    public void insertOrderProduct(OrderProduct orderProduct) {
        String dml = String.format(
                Locale.US,
                "INSERT INTO orderproduct VALUES (%d, %d, %d, %f)",
                orderProduct.getOrderId(),
                orderProduct.getProductId(),
                orderProduct.getQuantity(),
                orderProduct.getProductPrice()
        );
        this.databaseService.performDML(dml);
    }

    public void updateOrderProduct(int orderId, int productId, OrderProduct orderProduct) {
        String dml = String.format(
                Locale.US,
                "UPDATE orderproduct SET orderid = %d, productid = %d, quantity = %d, productprice = %f " +
                        "WHERE orderid = %d AND productid = %d",
                orderProduct.getOrderId(),
                orderProduct.getProductId(),
                orderProduct.getQuantity(),
                orderProduct.getProductPrice(),
                orderId, productId
        );
        this.databaseService.performDML(dml);
    }

    public void deleteOrderProduct(int orderId, int productId) {
        String dml = String.format(
                "DELETE FROM orderproduct WHERE orderid = %d AND productid = %d",
                orderId, productId
        );
        this.databaseService.performDML(dml);
    }
}
