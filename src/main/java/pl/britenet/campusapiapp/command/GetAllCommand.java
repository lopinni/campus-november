package pl.britenet.campusapiapp.command;

import pl.britenet.campusapiapp.Constants;
import pl.britenet.campusapiapp.database.DatabaseService;
import pl.britenet.campusapiapp.model.*;
import pl.britenet.campusapiapp.service.*;

import java.util.Scanner;

public class GetAllCommand extends Command {

    private final DatabaseService databaseService;

    public GetAllCommand(DatabaseService databaseService) {
        super(Constants.COMMAND_NAME_GETALL);
        this.databaseService = databaseService;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        String tableName = this.messageWithScanner("Table name:", scanner);
        switch (tableName) {
            case Constants.TABLE_NAME_CATEGORY -> {
                CategoryService categoryService = new CategoryService(databaseService);
                for (Category category : categoryService.getAll()) {
                    System.out.printf(category.toString());
                }
                System.out.println("Printed all records.");
            }
            case Constants.TABLE_NAME_PRODUCT -> {
                ProductService productService = new ProductService(databaseService);
                for (Product product : productService.getAll()) {
                    System.out.printf(product.toString());
                }
                System.out.println("Printed all records.");
            }
            case Constants.TABLE_NAME_PRODUCTCATEGORY -> {
                ProductCategoryService productCategoryService = new ProductCategoryService(databaseService);
                for (ProductCategory productCategory : productCategoryService.getAll()) {
                    System.out.println(productCategory.toString());
                }
                System.out.println("Printed all records.");
            }
            case Constants.TABLE_NAME_USER -> {
                UserService userService = new UserService(databaseService);
                for (User user : userService.getAll()) {
                    System.out.printf(user.toString());
                }
                System.out.println("Printed all records.");
            }
            case Constants.TABLE_NAME_ORDER -> {
                OrderService orderService = new OrderService(databaseService);
                for (Order order : orderService.getAll()) {
                    System.out.printf(order.toString());
                }
                System.out.println("Printed all records.");
            }
            case Constants.TABLE_NAME_ORDERPRODUCT -> {
                OrderProductService orderProductService = new OrderProductService(databaseService);
                for (OrderProduct orderProduct : orderProductService.getAll()) {
                    System.out.printf(orderProduct.toString());
                }
                System.out.println("Printed all records.");
            }
            case Constants.TABLE_NAME_CART -> {
                CartService cartService = new CartService(databaseService);
                for (Cart cart : cartService.getAll()) {
                    System.out.printf(cart.toString());
                }
                System.out.println("Printed all records.");
            }
            case Constants.TABLE_NAME_CARTPRODUCT -> {
                CartProductService cartProductService = new CartProductService(databaseService);
                for (CartProduct cartProduct : cartProductService.getAll()) {
                    System.out.printf(cartProduct.toString());
                }
                System.out.println("Printed all records.");
            }
            default -> System.out.println("Cannot find table with that name.");
        }
    }
}
