package pl.britenet.campusapiapp.command;

import pl.britenet.campusapiapp.Constants;
import pl.britenet.campusapiapp.database.DatabaseService;
import pl.britenet.campusapiapp.service.*;

import java.util.Scanner;

public class DeleteCommand extends Command{

    private final DatabaseService databaseService;

    public DeleteCommand(DatabaseService databaseService) {
        super(Constants.COMMAND_NAME_DELETE);
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
                    categoryService.deleteCategory(Integer.parseInt(this.messageWithScanner(
                            "Select record ID:", scanner
                    )));
                    System.out.println("Row deleted.");
                }
                case Constants.TABLE_NAME_PRODUCT -> {
                    ProductService productService = new ProductService(databaseService);
                    productService.deleteProduct(Integer.parseInt(this.messageWithScanner(
                            "Select record ID:", scanner
                    )));
                    System.out.println("Row deleted.");
                }
                case Constants.TABLE_NAME_PRODUCTCATEGORY -> {
                    ProductCategoryService productCategoryService = new ProductCategoryService(databaseService);
                    System.out.println("Select record IDs (product id, category id):");
                    productCategoryService.deleteProductCategory(
                            Integer.parseInt(this.messageWithScanner("Select product ID:", scanner)),
                            Integer.parseInt(this.messageWithScanner("Select category ID:", scanner))
                    );
                    System.out.println("Row deleted.");
                }
                case Constants.TABLE_NAME_USER -> {
                    UserService userService = new UserService(databaseService);
                    userService.deleteUser(Integer.parseInt(this.messageWithScanner(
                            "Select record ID:", scanner
                    )));
                    System.out.println("Row deleted.");
                }
                case Constants.TABLE_NAME_ORDER -> {
                    OrderService orderService = new OrderService(databaseService);
                    orderService.deleteOrder(Integer.parseInt(this.messageWithScanner(
                            "Select record ID:", scanner
                    )));
                    System.out.println("Row deleted.");
                }
                case Constants.TABLE_NAME_ORDERPRODUCT -> {
                    OrderProductService orderProductService = new OrderProductService(databaseService);
                    orderProductService.deleteOrderProduct(
                            Integer.parseInt(this.messageWithScanner("Select order ID:", scanner)),
                            Integer.parseInt(this.messageWithScanner("Select product ID:", scanner))
                    );
                    System.out.println("Row deleted.");
                }
                case Constants.TABLE_NAME_CART -> {
                    CartService cartService = new CartService(databaseService);
                    cartService.deleteCart(Integer.parseInt(this.messageWithScanner(
                            "Select record ID:", scanner
                    )));
                    System.out.println("Row deleted.");
                }
                case Constants.TABLE_NAME_CARTPRODUCT -> {
                    CartProductService cartProductService = new CartProductService(databaseService);
                    cartProductService.deleteCartProduct(
                            Integer.parseInt(this.messageWithScanner("Select cart ID:", scanner)),
                            Integer.parseInt(this.messageWithScanner("Select product ID:", scanner))
                    );
                    System.out.println("Row deleted.");
                }
                default -> System.out.println("Cannot find table with that name.");
            }
        } catch (NullPointerException | NumberFormatException e) {
            System.out.println("Record with that ID does not exist.");
        } catch (IllegalStateException e) {
            System.out.println("SQL error.");
        }
    }
}
