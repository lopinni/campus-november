package pl.britenet.campusapiapp.command;

import pl.britenet.campusapiapp.Constants;
import pl.britenet.campusapiapp.database.DatabaseService;
import pl.britenet.campusapiapp.service.*;

import java.util.Scanner;

public class DeleteCommand extends Command{

    private DatabaseService databaseService;

    public DeleteCommand(DatabaseService databaseService) {
        super(Constants.COMMAND_NAME_DELETE);
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
                    categoryService.deleteCategory(Integer.parseInt(scanner.nextLine()));
                    System.out.println("Row deleted.");
                }
                case Constants.TABLE_NAME_PRODUCT -> {
                    ProductService productService = new ProductService(databaseService);
                    System.out.println("Select record ID:");
                    productService.deleteProduct(Integer.parseInt(scanner.nextLine()));
                    System.out.println("Row deleted.");
                }
                case Constants.TABLE_NAME_PRODUCTCATEGORY -> {
                    ProductCategoryService productCategoryService = new ProductCategoryService(databaseService);
                    System.out.println("Select record IDs (product id, category id):");
                    productCategoryService.deleteProductCategory(
                            Integer.parseInt(scanner.nextLine()),
                            Integer.parseInt(scanner.nextLine())
                    );
                    System.out.println("Row deleted.");
                }
                case Constants.TABLE_NAME_USER -> {
                    UserService userService = new UserService(databaseService);
                    System.out.println("Select record ID:");
                    userService.deleteUser(Integer.parseInt(scanner.nextLine()));
                    System.out.println("Row deleted.");
                }
                case Constants.TABLE_NAME_ORDER -> {
                    OrderService orderService = new OrderService(databaseService);
                    System.out.println("Select record ID:");
                    orderService.deleteOrder(Integer.parseInt(scanner.nextLine()));
                    System.out.println("Row deleted.");
                }
                case Constants.TABLE_NAME_ORDERPRODUCT -> {
                    OrderProductService orderProductService = new OrderProductService(databaseService);
                    System.out.println("Select record IDs (order id, product id):");
                    orderProductService.deleteOrderProduct(
                            Integer.parseInt(scanner.nextLine()),
                            Integer.parseInt(scanner.nextLine())
                    );
                    System.out.println("Row deleted.");
                }
                case Constants.TABLE_NAME_CART -> {
                    CartService cartService = new CartService(databaseService);
                    System.out.println("Select record ID:");
                    cartService.deleteCart(Integer.parseInt(scanner.nextLine()));
                    System.out.println("Row deleted.");
                }
                case Constants.TABLE_NAME_CARTPRODUCT -> {
                    CartProductService cartProductService = new CartProductService(databaseService);
                    System.out.println("Select record IDs (cart id, product id):");
                    cartProductService.deleteCartProduct(
                            Integer.parseInt(scanner.nextLine()),
                            Integer.parseInt(scanner.nextLine())
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
