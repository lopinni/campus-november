package pl.britenet.campusapiapp.command;

import pl.britenet.campusapiapp.Constants;
import pl.britenet.campusapiapp.database.DatabaseService;
import pl.britenet.campusapiapp.model.*;
import pl.britenet.campusapiapp.service.*;

import java.util.Scanner;

public class GetCommand extends Command {

    private DatabaseService databaseService;

    public GetCommand(DatabaseService databaseService) {
        super(Constants.COMMAND_NAME_GET);
        this.databaseService = databaseService;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Table name:");
        String tableName = scanner.nextLine();
        try {
            switch (tableName) {
                case Constants.TABLE_NAME_CATEGORY -> {
                    CategoryService categoryService = new CategoryService(databaseService);
                    System.out.println("Select record ID:");
                    Category category = categoryService.getCategory(
                            Integer.parseInt(scanner.nextLine())
                    );
                    System.out.printf(category.toString());
                }
                case Constants.TABLE_NAME_PRODUCT -> {
                    ProductService productService = new ProductService(databaseService);
                    System.out.println("Select record ID:");
                    Product product = productService.getProduct(
                            Integer.parseInt(scanner.nextLine())
                    );
                    System.out.printf(product.toString());
                }
                case Constants.TABLE_NAME_PRODUCTCATEGORY -> {
                    ProductCategoryService productCategoryService = new ProductCategoryService(databaseService);
                    System.out.println("Select record IDs (product id, category id):");
                    ProductCategory productCategory = productCategoryService.getProductCategory(
                            Integer.parseInt(scanner.nextLine()),
                            Integer.parseInt(scanner.nextLine())
                    );
                    System.out.printf(productCategory.toString());
                }
                case Constants.TABLE_NAME_USER -> {
                    UserService userService = new UserService(databaseService);
                    System.out.println("Select record ID:");
                    User user = userService.getUser(
                            Integer.parseInt(scanner.nextLine())
                    );
                    System.out.printf(user.toString());
                }
                case Constants.TABLE_NAME_ORDER -> {
                    OrderService orderService = new OrderService(databaseService);
                    System.out.println("Select record ID:");
                    Order order = orderService.getOrder(
                            Integer.parseInt(scanner.nextLine())
                    );
                    System.out.printf(order.toString());
                }
                case Constants.TABLE_NAME_ORDERPRODUCT -> {
                    OrderProductService orderProductService = new OrderProductService(databaseService);
                    System.out.println("Select record IDs (order id, product id):");
                    OrderProduct orderProduct = orderProductService.getOrderProduct(
                            Integer.parseInt(scanner.nextLine()),
                            Integer.parseInt(scanner.nextLine())
                    );
                    System.out.printf(orderProduct.toString());
                }
                case Constants.TABLE_NAME_CART -> {
                    CartService cartService = new CartService(databaseService);
                    System.out.println("Select record ID:");
                    Cart cart = cartService.getCart(
                            Integer.parseInt(scanner.nextLine())
                    );
                    System.out.printf(cart.toString());
                }
                case Constants.TABLE_NAME_CARTPRODUCT -> {
                    CartProductService cartProductService = new CartProductService(databaseService);
                    System.out.println("Select record IDs (cart id, product id):");
                    CartProduct cartProduct = cartProductService.getCartProduct(
                            Integer.parseInt(scanner.nextLine()),
                            Integer.parseInt(scanner.nextLine())
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
