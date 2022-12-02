package pl.britenet.campusapiapp.command;

import pl.britenet.campusapiapp.constant.CommandName;
import pl.britenet.campusapiapp.constant.TableName;
import pl.britenet.campusapiapp.database.DatabaseService;
import pl.britenet.campusapiapp.service.*;

import java.util.Scanner;

public class DeleteCommand extends Command{

    private final DatabaseService databaseService;

    public DeleteCommand(DatabaseService databaseService) {
        super(CommandName.DELETE);
        this.databaseService = databaseService;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        String tableName = this.messageWithScanner("Table name:", scanner);
        try {
            switch (tableName) {
                case TableName.CATEGORY -> {
                    CategoryService categoryService = new CategoryService(databaseService);
                    categoryService.deleteCategory(Integer.parseInt(this.messageWithScanner(
                            "Select record ID:", scanner
                    )));
                    System.out.println("Row deleted.");
                }
                case TableName.PRODUCT -> {
                    ProductService productService = new ProductService(databaseService);
                    productService.deleteProduct(Integer.parseInt(this.messageWithScanner(
                            "Select record ID:", scanner
                    )));
                    System.out.println("Row deleted.");
                }
                case TableName.PRODUCT_CATEGORY -> {
                    ProductCategoryService productCategoryService = new ProductCategoryService(databaseService);
                    System.out.println("Select record IDs (product id, category id):");
                    productCategoryService.deleteProductCategory(
                            Integer.parseInt(this.messageWithScanner("Select product ID:", scanner)),
                            Integer.parseInt(this.messageWithScanner("Select category ID:", scanner))
                    );
                    System.out.println("Row deleted.");
                }
                case TableName.USER -> {
                    UserService userService = new UserService(databaseService);
                    userService.deleteUser(Integer.parseInt(this.messageWithScanner(
                            "Select record ID:", scanner
                    )));
                    System.out.println("Row deleted.");
                }
                case TableName.ORDER -> {
                    OrderService orderService = new OrderService(databaseService);
                    orderService.deleteOrder(Integer.parseInt(this.messageWithScanner(
                            "Select record ID:", scanner
                    )));
                    System.out.println("Row deleted.");
                }
                case TableName.ORDER_PRODUCT -> {
                    OrderProductService orderProductService = new OrderProductService(databaseService);
                    orderProductService.deleteOrderProduct(
                            Integer.parseInt(this.messageWithScanner("Select order ID:", scanner)),
                            Integer.parseInt(this.messageWithScanner("Select product ID:", scanner))
                    );
                    System.out.println("Row deleted.");
                }
                case TableName.CART -> {
                    CartService cartService = new CartService(databaseService);
                    cartService.deleteCart(Integer.parseInt(this.messageWithScanner(
                            "Select record ID:", scanner
                    )));
                    System.out.println("Row deleted.");
                }
                case TableName.CART_PRODUCT -> {
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
