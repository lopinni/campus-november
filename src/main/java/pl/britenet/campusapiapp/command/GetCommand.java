package pl.britenet.campusapiapp.command;

import pl.britenet.campusapiapp.Constants;
import pl.britenet.campusapiapp.database.DatabaseService;
import pl.britenet.campusapiapp.model.*;
import pl.britenet.campusapiapp.service.*;

import java.util.Scanner;

public class GetCommand extends Command {

    private final DatabaseService databaseService;

    public GetCommand(DatabaseService databaseService) {
        super(Constants.COMMAND_NAME_GET);
        this.databaseService = databaseService;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        String tableName = this.messageWithScanner("Table name:", scanner);
        try {
            switch (tableName) {
                case Constants.TABLE_NAME_CATEGORY -> {
                    CategoryService categoryService = new CategoryService(databaseService);
                    Category category = categoryService.getCategory(Integer.parseInt(this.messageWithScanner(
                            "Select record ID:", scanner
                    )));
                    System.out.printf(category.toString());
                }
                case Constants.TABLE_NAME_PRODUCT -> {
                    ProductService productService = new ProductService(databaseService);
                    Product product = productService.getProduct(Integer.parseInt(this.messageWithScanner(
                            "Select record ID:", scanner
                    )));
                    System.out.printf(product.toString());
                }
                case Constants.TABLE_NAME_PRODUCTCATEGORY -> {
                    ProductCategoryService productCategoryService = new ProductCategoryService(databaseService);
                    ProductCategory productCategory = productCategoryService.getProductCategory(
                            Integer.parseInt(this.messageWithScanner("Select product ID:", scanner)),
                            Integer.parseInt(this.messageWithScanner("Select category ID:", scanner))
                    );
                    System.out.printf(productCategory.toString());
                }
                case Constants.TABLE_NAME_USER -> {
                    UserService userService = new UserService(databaseService);
                    User user = userService.getUser(Integer.parseInt(this.messageWithScanner(
                            "Select record ID:", scanner
                    )));
                    System.out.printf(user.toString());
                }
                case Constants.TABLE_NAME_ORDER -> {
                    OrderService orderService = new OrderService(databaseService);
                    Order order = orderService.getOrder(Integer.parseInt(this.messageWithScanner(
                            "Select record ID:", scanner
                    )));
                    System.out.printf(order.toString());
                }
                case Constants.TABLE_NAME_ORDERPRODUCT -> {
                    OrderProductService orderProductService = new OrderProductService(databaseService);
                    OrderProduct orderProduct = orderProductService.getOrderProduct(
                            Integer.parseInt(this.messageWithScanner("Select order ID:", scanner)),
                            Integer.parseInt(this.messageWithScanner("Select product ID:", scanner))
                    );
                    System.out.printf(orderProduct.toString());
                }
                case Constants.TABLE_NAME_CART -> {
                    CartService cartService = new CartService(databaseService);
                    Cart cart = cartService.getCart(Integer.parseInt(this.messageWithScanner(
                            "Select record ID:", scanner
                    )));
                    System.out.printf(cart.toString());
                }
                case Constants.TABLE_NAME_CARTPRODUCT -> {
                    CartProductService cartProductService = new CartProductService(databaseService);
                    CartProduct cartProduct = cartProductService.getCartProduct(
                            Integer.parseInt(this.messageWithScanner("Select cart ID:", scanner)),
                            Integer.parseInt(this.messageWithScanner("Select product ID:", scanner))
                    );
                    System.out.printf(cartProduct.toString());
                }
                default -> System.out.println("Cannot find table with that name.");
            }
        } catch (NullPointerException | NumberFormatException e) {
            System.out.println("Record with that ID does not exist.");
        }
    }
}
