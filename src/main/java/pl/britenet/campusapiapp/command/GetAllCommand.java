package pl.britenet.campusapiapp.command;

import pl.britenet.campusapiapp.constant.CommandName;
import pl.britenet.campusapiapp.constant.TableName;
import pl.britenet.campusapiapp.database.DatabaseService;
import pl.britenet.campusapiapp.model.*;
import pl.britenet.campusapiapp.service.*;

import java.util.Scanner;

public class GetAllCommand extends Command {

    private final DatabaseService databaseService;

    public GetAllCommand(DatabaseService databaseService) {
        super(CommandName.GET_ALL);
        this.databaseService = databaseService;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        String tableName = this.messageWithScanner("Table name:", scanner);
        switch (tableName) {
            case TableName.CATEGORY -> {
                CategoryService categoryService = new CategoryService(databaseService);
                for (Category category : categoryService.getAll()) {
                    System.out.printf(category.toString());
                }
                System.out.println("Printed all records.");
            }
            case TableName.PRODUCT -> {
                ProductService productService = new ProductService(databaseService);
                for (Product product : productService.getAll()) {
                    System.out.printf(product.toString());
                }
                System.out.println("Printed all records.");
            }
            case TableName.PRODUCT_CATEGORY -> {
                ProductCategoryService productCategoryService = new ProductCategoryService(databaseService);
                for (ProductCategory productCategory : productCategoryService.getAll()) {
                    System.out.println(productCategory.toString());
                }
                System.out.println("Printed all records.");
            }
            case TableName.USER -> {
                UserService userService = new UserService(databaseService);
                for (User user : userService.getAll()) {
                    System.out.printf(user.toString());
                }
                System.out.println("Printed all records.");
            }
            case TableName.ORDER -> {
                OrderService orderService = new OrderService(databaseService);
                for (Order order : orderService.getAll()) {
                    System.out.printf(order.toString());
                }
                System.out.println("Printed all records.");
            }
            case TableName.ORDER_PRODUCT -> {
                OrderProductService orderProductService = new OrderProductService(databaseService);
                for (OrderProduct orderProduct : orderProductService.getAll()) {
                    System.out.printf(orderProduct.toString());
                }
                System.out.println("Printed all records.");
            }
            case TableName.CART -> {
                CartService cartService = new CartService(databaseService);
                for (Cart cart : cartService.getAll()) {
                    System.out.printf(cart.toString());
                }
                System.out.println("Printed all records.");
            }
            case TableName.CART_PRODUCT -> {
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
