package pl.britenet.campusapiapp.command;

import pl.britenet.campusapiapp.constant.CommandName;
import pl.britenet.campusapiapp.constant.TableName;
import pl.britenet.campusapiapp.database.DatabaseService;
import pl.britenet.campusapiapp.model.*;
import pl.britenet.campusapiapp.model.builder.*;
import pl.britenet.campusapiapp.service.*;

import java.sql.Date;
import java.util.Scanner;

public class InsertCommand extends Command {

    private final DatabaseService databaseService;

    public InsertCommand(DatabaseService databaseService) {
        super(CommandName.INSERT);
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
                    CategoryBuilder categoryBuilder = new CategoryBuilder(new Category());
                    categoryBuilder
                            .setName(this.messageWithScanner("Insert new name:", scanner))
                            .setImagePath(this.messageWithScanner("Insert new image path:", scanner));
                    categoryService.insertCategory(categoryBuilder.getCategory());
                    System.out.println("Row inserted.");
                }
                case TableName.PRODUCT -> {
                    ProductService productService = new ProductService(databaseService);
                    ProductBuilder productBuilder = new ProductBuilder(new Product());
                    productBuilder
                            .setName(this.messageWithScanner("Insert new name:", scanner))
                            .setPrice(Double.parseDouble(this.messageWithScanner("Insert new price:", scanner)))
                            .setDescription(this.messageWithScanner("Insert new description:", scanner))
                            .setImagePath(this.messageWithScanner("Insert new image path:", scanner));
                    productService.insertProduct(productBuilder.getProduct());
                    System.out.println("Row inserted.");
                }
                case TableName.PRODUCT_CATEGORY -> {
                    ProductCategoryService productCategoryService = new ProductCategoryService(databaseService);
                    ProductCategoryBuilder productCategoryBuilder = new ProductCategoryBuilder(new ProductCategory());
                    productCategoryBuilder
                            .setProductId(Integer.parseInt(this.messageWithScanner(
                                    "Insert new product id:", scanner
                            )))
                            .setCategoryId(Integer.parseInt(this.messageWithScanner(
                                    "Insert new category id:", scanner
                            )));
                    productCategoryService.insertProductCategory(productCategoryBuilder.getProductCategory());
                    System.out.println("Row inserted.");
                }
                case TableName.USER -> {
                    UserService userService = new UserService(databaseService);
                    UserBuilder userBuilder = new UserBuilder(new User());
                    userBuilder
                            .setLogin(this.messageWithScanner("Insert new login:", scanner))
                            .setPassword(this.messageWithScanner("Insert new password:", scanner))
                            .setName(this.messageWithScanner("Insert new name:", scanner))
                            .setSurname(this.messageWithScanner("Insert new surname:", scanner))
                            .setCity(this.messageWithScanner("Insert new city:", scanner))
                            .setStreet(this.messageWithScanner("Insert new street:", scanner))
                            .setCountry(this.messageWithScanner("Insert new country:", scanner))
                            .setZipCode(this.messageWithScanner("Insert new zip code:", scanner))
                            .setProfilePicturePath(this.messageWithScanner(
                                    "Insert new profile picture path:", scanner
                            ));
                    userService.insertUser(userBuilder.getUser());
                    System.out.println("Row inserted.");
                }
                case TableName.ORDER -> {
                    OrderService orderService = new OrderService(databaseService);
                    OrderBuilder orderBuilder = new OrderBuilder(new Order());
                    orderBuilder
                            .setUserId(Integer.parseInt(this.messageWithScanner(
                                    "Insert new user id:", scanner
                            )))
                            .setOrderDate(Date.valueOf(this.messageWithScanner(
                                    "Insert new order date:", scanner
                            )))
                            .setTotalPrice(Double.parseDouble(this.messageWithScanner(
                                    "Insert new total price:", scanner
                            )))
                            .setShippingAddress(this.messageWithScanner(
                                    "Insert new shipping address:", scanner
                            ))
                            .setDiscount(Double.parseDouble(this.messageWithScanner(
                                    "Insert new discount:", scanner
                            )))
                            .setStatus(this.messageWithScanner(
                                    "Insert new status:", scanner
                            ));
                    orderService.insertOrder(orderBuilder.getOrder());
                    System.out.println("Row inserted.");
                }
                case TableName.ORDER_PRODUCT -> {
                    OrderProductService orderProductService = new OrderProductService(databaseService);
                    OrderProductBuilder orderProductBuilder = new OrderProductBuilder(new OrderProduct());
                    orderProductBuilder
                            .setOrderId(Integer.parseInt(this.messageWithScanner(
                                    "Insert new order id:", scanner
                            )))
                            .setProductId(Integer.parseInt(this.messageWithScanner(
                                    "Insert new product id:", scanner
                            )))
                            .setQuantity(Integer.parseInt(this.messageWithScanner(
                                    "Insert new quantity:", scanner
                            )))
                            .setProductPrice(Double.parseDouble(this.messageWithScanner(
                                    "Insert new productprice:", scanner
                            )));
                    orderProductService.insertOrderProduct(orderProductBuilder.getOrderProduct());
                    System.out.println("Row inserted.");
                }
                case TableName.CART -> {
                    CartService cartService = new CartService(databaseService);
                    CartBuilder cartBuilder = new CartBuilder(new Cart());
                    cartBuilder
                            .setUserId(Integer.parseInt(this.messageWithScanner(
                                    "Insert new user id:", scanner
                            )))
                            .setOrderDate(Date.valueOf(this.messageWithScanner(
                                    "Insert new order date:", scanner
                            )))
                            .setTotalPrice(Double.parseDouble(this.messageWithScanner(
                                    "Insert new total price:", scanner
                            )))
                            .setDiscount(Double.parseDouble(this.messageWithScanner(
                                    "Insert new discount:", scanner
                            )));
                    cartService.insertCart(cartBuilder.getCart());
                    System.out.println("Row inserted.");
                }
                case TableName.CART_PRODUCT -> {
                    CartProductService cartProductService = new CartProductService(databaseService);
                    CartProductBuilder cartProductBuilder = new CartProductBuilder(new CartProduct());
                    cartProductBuilder
                            .setCartId(Integer.parseInt(this.messageWithScanner(
                                    "Insert new cart id:", scanner
                            )))
                            .setProductId(Integer.parseInt(this.messageWithScanner(
                                    "Insert new product id:", scanner
                            )))
                            .setQuantity(Integer.parseInt(this.messageWithScanner(
                                    "Insert new quantity:", scanner
                            )))
                            .setProductPrice(Double.parseDouble(this.messageWithScanner(
                                    "Insert new product price:", scanner
                            )));
                    cartProductService.insertCartProduct(cartProductBuilder.getCartProduct());
                    System.out.println("Row inserted.");
                }
                default -> System.out.println("Cannot find table with that name.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Incorrect data type on last column.");
        } catch (IllegalStateException e) {
            System.out.println(e);
        }
    }
}
