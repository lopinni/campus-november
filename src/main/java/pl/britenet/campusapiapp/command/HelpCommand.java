package pl.britenet.campusapiapp.command;

import pl.britenet.campusapiapp.Constants;

import java.util.List;

public class HelpCommand extends Command {

    private List<Command> commandList;

    public HelpCommand(List<Command> commandList) {
        super(Constants.COMMAND_NAME_HELP);
        this.commandList = commandList;
    }

    @Override
    public void execute() {
        System.out.println("----- Commands: -----");
        for (Command command : commandList) {
            System.out.print(command.getName());
            System.out.print("\t");
            switch (command.getName()) {
                case Constants.COMMAND_NAME_CLEAR -> System.out.print(" - clear console");
                case Constants.COMMAND_NAME_DELETE -> System.out.print(" - delete one row in selected table");
                case Constants.COMMAND_NAME_EXIT -> System.out.print(" - exit application");
                case Constants.COMMAND_NAME_GET -> System.out.print("\t - print one row from selected table");
                case Constants.COMMAND_NAME_GETALL -> System.out.print(" - print all rows from selected table");
                case Constants.COMMAND_NAME_GETPAGINATED -> System.out.print(
                        " - print rows from selected table in batches of selected length"
                );
                case Constants.COMMAND_NAME_HELP -> System.out.print(" - print all command and table info");
                case Constants.COMMAND_NAME_INSERT -> System.out.print(" - create new record in selected table");
                case Constants.COMMAND_NAME_UPDATE -> System.out.print(" - edit one row in selected table");
            }
            System.out.print("\n");
        }
        System.out.println("----- Tables: -----");
        System.out.printf("""
            %s \t\t\t (id, user id, order date, total price, discount)
            %s \t (cart id, product id, quantity, product price)
            %s \t\t (id, name, image path)
            %s \t\t\t (id, user id, order date, total price, shipping address, discount)
            %s \t (order id, product id, quantity, product price)
            %s \t\t (id, name, price, description, image path)
            %s \t(product id, category id)
            %s \t\t\t (id, login, password, name, surname, city, street, country, zip code, profile picture path)
            """,
                Constants.TABLE_NAME_CART,
                Constants.TABLE_NAME_CARTPRODUCT,
                Constants.TABLE_NAME_CATEGORY,
                Constants.TABLE_NAME_ORDER,
                Constants.TABLE_NAME_ORDERPRODUCT,
                Constants.TABLE_NAME_PRODUCT,
                Constants.TABLE_NAME_PRODUCTCATEGORY,
                Constants.TABLE_NAME_USER
        );
    }
}
