package pl.britenet.campusapiapp.command;

import pl.britenet.campusapiapp.Constants;
import pl.britenet.campusapiapp.database.DatabaseService;
import pl.britenet.campusapiapp.service.ReportService;

import java.util.List;
import java.util.Scanner;

public class ReportCommand extends Command {

    private final DatabaseService databaseService;

    public ReportCommand(DatabaseService databaseService) {
        super(Constants.COMMAND_NAME_REPORT);
        this.databaseService = databaseService;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        String tableName = this.messageWithScanner("Report name:", scanner);
        ReportService reportService = new ReportService(databaseService);
        try {
            switch (tableName) {
                case Constants.REPORT_NAME_INCOMEBYMONTH -> {
                    System.out.println("Month\tIncome");
                    for (List<String> row : reportService.getIncomeByMonth()) {
                        for (String data : row) {
                            System.out.print(data + "\t\t");
                        }
                        System.out.print("\n");
                    }
                }
                case Constants.REPORT_NAME_SALESBYMONTH -> {
                    System.out.println("Month\tProduct_count");
                    for (List<String> row : reportService.getSalesByMonth()) {
                        for (String data : row) {
                            System.out.print(data + "\t\t");
                        }
                        System.out.print("\n");
                    }
                }
                case Constants.REPORT_NAME_SALESBYYEARANDMONTH -> {
                    System.out.println("Year\t\tMonth\tProduct_count");
                    for (List<String> row : reportService.getSalesByYearAndMonth()) {
                        for (String data : row) {
                            System.out.print(data + "\t\t");
                        }
                        System.out.print("\n");
                    }
                }
                case Constants.REPORT_NAME_TOPXCUSTOMERS -> {
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
                case Constants.REPORT_NAME_BOTTOMXCUSTOMERS -> {
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
