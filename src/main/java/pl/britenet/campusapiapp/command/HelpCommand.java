package pl.britenet.campusapiapp.command;

import pl.britenet.campusapiapp.constant.CommandName;
import pl.britenet.campusapiapp.constant.ReportName;
import pl.britenet.campusapiapp.constant.TableName;

import java.util.List;
import java.util.Scanner;

public class HelpCommand extends Command {

    private final List<Command> commandList;

    public HelpCommand(List<Command> commandList) {
        super(CommandName.HELP);
        this.commandList = commandList;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(
                """
                all (default) - show everything
                command       - show available commands
                table         - show tables in database
                report        - show available reports
                """
        );
        String informationType = this.messageWithScanner(
                "What information would you like to know?", scanner
        );
        switch (informationType) {
            case "command" -> commandInformation();
            case "table" -> tableInformation();
            case "report" -> reportInformation();
            default -> allInformation();
        }
    }

    private void commandInformation() {
        System.out.println("\n---------- Commands: ----------");
        for (Command command : commandList) {
            System.out.print(command.getName());
            System.out.print("\t");
            switch (command.getName()) {
                case CommandName.CLEAR -> System.out.print(" - clear console");
                case CommandName.DELETE -> System.out.print(" - delete one row in selected table");
                case CommandName.EXIT -> System.out.print(" - exit application");
                case CommandName.GET -> System.out.print("\t - print one row from selected table");
                case CommandName.GET_ALL -> System.out.print(" - print all rows from selected table");
                case CommandName.GET_PAGINATED -> System.out.print(
                        " - print rows from selected table in batches of selected length"
                );
                case CommandName.HELP -> System.out.print(" - print all command and table info");
                case CommandName.INSERT -> System.out.print(" - create new record in selected table");
                case CommandName.UPDATE -> System.out.print(" - edit one row in selected table");
                case CommandName.REPORT -> System.out.print(" - generate a report");
            }
            System.out.print("\n");
        }
    }

    private void tableInformation() {
        System.out.println("\n---------- Tables: ----------");
        System.out.printf(
            """
            %s \t\t\t (id, user id, order date, total price, discount)
            %s \t (cart id, product id, quantity, product price)
            %s \t\t (id, name, image path)
            %s \t\t\t (id, user id, order date, total price, shipping address, discount)
            %s \t (order id, product id, quantity, product price)
            %s \t\t (id, name, price, description, image path)
            %s \t(product id, category id)
            %s \t\t\t (id, login, password, name, surname, city, street, country, zip code, profile picture path)
            """,
                TableName.CART,
                TableName.CART_PRODUCT,
                TableName.CATEGORY,
                TableName.ORDER,
                TableName.ORDER_PRODUCT,
                TableName.PRODUCT,
                TableName.PRODUCT_CATEGORY,
                TableName.USER
        );
    }

    private void reportInformation() {
        System.out.println("\n---------- Reports: ----------");
        System.out.printf(
            """
            %s \t\t- show total sum of sales by month
            %s \t\t- show number of items sold by month
            %s \t- show number of items sold by year and month
            %s \t- show top x costomer sales
            %s \t- show bottom x customer sales
            """,
                ReportName.INCOME_BY_MONTH,
                ReportName.SALES_BY_MONTH,
                ReportName.SALES_BY_YEAR_AND_MONTH,
                ReportName.TOP_X_CUSTOMERS,
                ReportName.BOTTOM_X_CUSTOMERS
        );
    }

    private void allInformation() {
        commandInformation();
        tableInformation();
        reportInformation();
    }
}
