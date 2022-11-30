package pl.britenet.campusapiapp.command;

import pl.britenet.campusapiapp.Constants;
import pl.britenet.campusapiapp.database.DatabaseService;
import pl.britenet.campusapiapp.model.*;
import pl.britenet.campusapiapp.model.builder.*;
import pl.britenet.campusapiapp.service.*;

import java.sql.Date;
import java.util.Scanner;

public class InsertCommand extends Command {

    private DatabaseService databaseService;

    public InsertCommand(DatabaseService databaseService) {
        super(Constants.COMMAND_NAME_INSERT);
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
                    CategoryBuilder categoryBuilder = new CategoryBuilder(new Category());
                    System.out.println("Insert new values (name, image path):");
                    categoryBuilder
                            .setName(scanner.nextLine())
                            .setImagePath(scanner.nextLine());
                    categoryService.insertCategory(categoryBuilder.getCategory());
                    System.out.println("Row inserted.");
                }
                case Constants.TABLE_NAME_PRODUCT -> {
                    ProductService productService = new ProductService(databaseService);
                    ProductBuilder productBuilder = new ProductBuilder(new Product());
                    System.out.println("Insert new values (name, price, description, image path):");
                    productBuilder
                            .setName(scanner.nextLine())
                            .setPrice(Double.parseDouble(scanner.nextLine()))
                            .setDescription(scanner.nextLine())
                            .setImagePath(scanner.nextLine());
                    productService.insertProduct(productBuilder.getProduct());
                    System.out.println("Row inserted.");
                }
                case Constants.TABLE_NAME_PRODUCTCATEGORY -> {
                    ProductCategoryService productCategoryService = new ProductCategoryService(databaseService);
                    ProductCategoryBuilder productCategoryBuilder = new ProductCategoryBuilder(new ProductCategory());
                    System.out.println("Insert new values (product id, category id):");
                    productCategoryBuilder
                            .setProductId(Integer.parseInt(scanner.nextLine()))
                            .setCategoryId(Integer.parseInt(scanner.nextLine()));
                    productCategoryService.insertProductCategory(productCategoryBuilder.getProductCategory());
                    System.out.println("Row inserted.");
                }
                case Constants.TABLE_NAME_USER -> {
                    UserService userService = new UserService(databaseService);
                    UserBuilder userBuilder = new UserBuilder(new User());
                    System.out.println("Insert new values " +
                            "(login, password, name, surname, city, street, country, zip code, profile picture path):");
                    userBuilder
                            .setLogin(scanner.nextLine())
                            .setPassword(scanner.nextLine())
                            .setName(scanner.nextLine())
                            .setSurname(scanner.nextLine())
                            .setCity(scanner.nextLine())
                            .setStreet(scanner.nextLine())
                            .setCountry(scanner.nextLine())
                            .setZipCode(scanner.nextLine())
                            .setProfilePicturePath(scanner.nextLine());
                    userService.insertUser(userBuilder.getUser());
                    System.out.println("Row inserted.");
                }
                case Constants.TABLE_NAME_ORDER -> {
                    OrderService orderService = new OrderService(databaseService);
                    OrderBuilder orderBuilder = new OrderBuilder(new Order());
                    System.out.println("Insert new values " +
                            "(user id, order date, total price, shipping address, discount):");
                    orderBuilder
                            .setUserId(Integer.parseInt(scanner.nextLine()))
                            .setOrderDate(Date.valueOf(scanner.nextLine()))
                            .setTotalPrice(Double.parseDouble(scanner.nextLine()))
                            .setShippingAddress(scanner.nextLine())
                            .setDiscount(Double.parseDouble(scanner.nextLine()));
                    orderService.insertOrder(orderBuilder.getOrder());
                    System.out.println("Row inserted.");
                }
                case Constants.TABLE_NAME_ORDERPRODUCT -> {
                    OrderProductService orderProductService = new OrderProductService(databaseService);
                    OrderProductBuilder orderProductBuilder = new OrderProductBuilder(new OrderProduct());
                    System.out.println("Insert new values (order id, product id, quantity, product price):");
                    orderProductBuilder
                            .setOrderId(Integer.parseInt(scanner.nextLine()))
                            .setProductId(Integer.parseInt(scanner.nextLine()))
                            .setQuantity(Integer.parseInt(scanner.nextLine()))
                            .setProductPrice(Double.parseDouble(scanner.nextLine()));
                    orderProductService.insertOrderProduct(orderProductBuilder.getOrderProduct());
                    System.out.println("Row inserted.");
                }
                case Constants.TABLE_NAME_CART -> {
                    CartService cartService = new CartService(databaseService);
                    CartBuilder cartBuilder = new CartBuilder(new Cart());
                    System.out.println("Insert new values (user id, order date, total price, discount):");
                    cartBuilder
                            .setUserId(Integer.parseInt(scanner.nextLine()))
                            .setOrderDate(Date.valueOf(scanner.nextLine()))
                            .setTotalPrice(Double.parseDouble(scanner.nextLine()))
                            .setDiscount(Double.parseDouble(scanner.nextLine()));
                    cartService.insertCart(cartBuilder.getCart());
                    System.out.println("Row inserted.");
                }
                case Constants.TABLE_NAME_CARTPRODUCT -> {
                    CartProductService cartProductService = new CartProductService(databaseService);
                    CartProductBuilder cartProductBuilder = new CartProductBuilder(new CartProduct());
                    System.out.println("Insert new values (cart id, product id, quantity, product price):");
                    cartProductBuilder
                            .setCartId(Integer.parseInt(scanner.nextLine()))
                            .setProductId(Integer.parseInt(scanner.nextLine()))
                            .setQuantity(Integer.parseInt(scanner.nextLine()))
                            .setProductPrice(Double.parseDouble(scanner.nextLine()));
                    cartProductService.insertCartProduct(cartProductBuilder.getCartProduct());
                    System.out.println("Row inserted.");
                }
                default -> System.out.println("Cannot find table with that name.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Incorrect data type on last column.");
        } catch (IllegalStateException e) {
            System.out.println("SQL error.");
        }
    }
}
