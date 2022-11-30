package pl.britenet.campusapiapp.command;

import pl.britenet.campusapiapp.Constants;
import pl.britenet.campusapiapp.database.DatabaseService;
import pl.britenet.campusapiapp.model.*;
import pl.britenet.campusapiapp.service.*;

import java.util.List;
import java.util.Scanner;

public class GetPaginatedCommand extends Command{

    private final DatabaseService databaseService;

    public GetPaginatedCommand(DatabaseService databaseService) {
        super(Constants.COMMAND_NAME_GETPAGINATED);
        this.databaseService = databaseService;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        String tableName = this.messageWithScanner("Table name:", scanner);
        try {
            int rownum = Integer.parseInt(this.messageWithScanner("How many records per page?", scanner));
            if (rownum < 1) throw new NumberFormatException();
            switch (tableName) {
                case Constants.TABLE_NAME_CATEGORY -> {
                    CategoryService categoryService = new CategoryService(databaseService);
                    for (List<Category> categoryList : categoryService.getPaginated(rownum)) {
                        for (Category category : categoryList) {
                            System.out.printf(category.toString());
                        }
                        if (this.messageWithScanner(
                                "- Press ENTER for next page, type 'end' to stop printing records -", scanner
                        ).equals("end")) break;
                    }
                    System.out.println("Printed all records.");
                }
                case Constants.TABLE_NAME_PRODUCT -> {
                    ProductService productService = new ProductService(databaseService);
                    for (List<Product> productList : productService.getPaginated(rownum)) {
                        for (Product product : productList) {
                            System.out.printf(product.toString());
                        }
                        if (this.messageWithScanner(
                                "- Press ENTER for next page, type 'end' to stop printing records -", scanner
                        ).equals("end")) break;
                    }
                    System.out.println("Printed all records.");
                }
                case Constants.TABLE_NAME_PRODUCTCATEGORY -> {
                    ProductCategoryService productCategoryService = new ProductCategoryService(databaseService);
                    for (List<ProductCategory> productCategoryList : productCategoryService.getPaginated(rownum)) {
                        for (ProductCategory productCategory : productCategoryList) {
                            System.out.printf(productCategory.toString());
                        }
                        if (this.messageWithScanner(
                                "- Press ENTER for next page, type 'end' to stop printing records -", scanner
                        ).equals("end")) break;
                    }
                    System.out.println("Printed all records.");
                }
                case Constants.TABLE_NAME_USER -> {
                    UserService userService = new UserService(databaseService);
                    for (List<User> userList : userService.getPaginated(rownum)) {
                        for (User user : userList) {
                            System.out.printf(user.toString());
                        }
                        if (this.messageWithScanner(
                                "- Press ENTER for next page, type 'end' to stop printing records -", scanner
                        ).equals("end")) break;
                    }
                    System.out.println("Printed all records.");
                }
                case Constants.TABLE_NAME_ORDER -> {
                    OrderService orderService = new OrderService(databaseService);
                    for (List<Order> orderList : orderService.getPaginated(rownum)) {
                        for (Order order : orderList) {
                            System.out.printf(order.toString());
                        }
                        if (this.messageWithScanner(
                                "- Press ENTER for next page, type 'end' to stop printing records -", scanner
                        ).equals("end")) break;
                    }
                    System.out.println("Printed all records.");
                }
                case Constants.TABLE_NAME_ORDERPRODUCT -> {
                    OrderProductService orderProductService = new OrderProductService(databaseService);
                    for (List<OrderProduct> orderProductList : orderProductService.getPaginated(rownum)) {
                        for (OrderProduct orderProduct : orderProductList) {
                            System.out.printf(orderProduct.toString());
                        }
                        if (this.messageWithScanner(
                                "- Press ENTER for next page, type 'end' to stop printing records -", scanner
                        ).equals("end")) break;
                    }
                    System.out.println("Printed all records.");
                }
                case Constants.TABLE_NAME_CART -> {
                    CartService cartService = new CartService(databaseService);
                    for (List<Cart> cartList : cartService.getPaginated(rownum)) {
                        for (Cart cart : cartList) {
                            System.out.printf(cart.toString());
                        }
                        if (this.messageWithScanner(
                                "- Press ENTER for next page, type 'end' to stop printing records -", scanner
                        ).equals("end")) break;
                    }
                    System.out.println("Printed all records.");
                }
                case Constants.TABLE_NAME_CARTPRODUCT -> {
                    CartProductService cartProductService = new CartProductService(databaseService);
                    for (List<CartProduct> cartProductList : cartProductService.getPaginated(rownum)) {
                        for (CartProduct cartProduct : cartProductList) {
                            System.out.printf(cartProduct.toString());
                        }
                        if (this.messageWithScanner(
                                "- Press ENTER for next page, type 'end' to stop printing records -", scanner
                        ).equals("end")) break;
                    }
                    System.out.println("Printed all records.");
                }
                default -> System.out.println("Cannot find table with that name.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid number of records");
        }
    }
}
