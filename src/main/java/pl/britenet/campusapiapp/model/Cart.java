package pl.britenet.campusapiapp.model;

import java.util.Date;

public final class Cart {

    private int id;
    private int userId;
    private Date orderDate;
    private double totalPrice;
    private double discount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "Cart {" +
                " id=" + id +
                ", userId=" + userId +
                ", orderDate=" + orderDate +
                ", totalPrice=" + totalPrice +
                ", discount=" + discount +
                " }\n";
    }
}
