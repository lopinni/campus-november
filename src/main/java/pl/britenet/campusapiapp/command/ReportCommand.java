package pl.britenet.campusapiapp.command;

import pl.britenet.campusapiapp.constant.CommandName;
import pl.britenet.campusapiapp.constant.ReportName;
import pl.britenet.campusapiapp.database.DatabaseService;
import pl.britenet.campusapiapp.service.ReportService;

import java.util.List;
import java.util.Scanner;

public class ReportCommand extends Command {

    private final DatabaseService databaseService;

    public ReportCommand(DatabaseService databaseService) {
        super(CommandName.REPORT);
        this.databaseService = databaseService;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        String reportName = this.messageWithScanner("Report name:", scanner);
        ReportService reportService = new ReportService(databaseService);
        try {
            switch (reportName) {
                case ReportName.INCOME_BY_MONTH -> {
                    System.out.println("Month\tIncome");
                    for (List<String> row : reportService.getIncomeByMonth()) {
                        for (String data : row) {
                            System.out.print(data + "\t\t");
                        }
                        System.out.print("\n");
                    }
                }
                case ReportName.SALES_BY_MONTH -> {
                    System.out.println("Month\tProduct_count");
                    for (List<String> row : reportService.getSalesByMonth()) {
                        for (String data : row) {
                            System.out.print(data + "\t\t");
                        }
                        System.out.print("\n");
                    }
                }
                case ReportName.SALES_BY_YEAR_AND_MONTH -> {
                    System.out.println("Year\t\tMonth\tProduct_count");
                    for (List<String> row : reportService.getSalesByYearAndMonth()) {
                        for (String data : row) {
                            System.out.print(data + "\t\t");
                        }
                        System.out.print("\n");
                    }
                }
                case ReportName.TOP_X_CUSTOMERS -> {
                    int x = Integer.parseInt(this.messageWithScanner("Select x:", scanner));
                    System.out.println("ID\tMoney_spent");
                    if (x < 1) throw new NumberFormatException();
                    for (List<String> row : reportService.getTopXCustomers(x)) {
                        for (String data : row) {
                            System.out.print(data + "\t");
                        }
                        System.out.print("\n");
                    }
                }
                case ReportName.BOTTOM_X_CUSTOMERS -> {
                    int x = Integer.parseInt(this.messageWithScanner("Select x:", scanner));
                    System.out.println("ID\tMoney_spent");
                    if (x < 1) throw new NumberFormatException();
                    for (List<String> row : reportService.getBottomXCustomers(x)) {
                        for (String data : row) {
                            System.out.print(data + "\t");
                        }
                        System.out.print("\n");
                    }
                }
                default -> System.out.println("Cannot find report with that name.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Incorrect number of customers");
        }
    }
}
