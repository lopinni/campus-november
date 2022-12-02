package pl.britenet.campusapiapp.service;

import pl.britenet.campusapiapp.database.DatabaseService;

import java.util.ArrayList;
import java.util.List;

public class ReportService {

    private final DatabaseService databaseService;

    public ReportService(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    public List<List<String>> getIncomeByMonth() {
        String sql =
                "SELECT MONTH(o.orderdate) AS month, SUM(o.totalprice) AS income " +
                        "FROM `order` o GROUP BY month;";

        return this.databaseService.performSQL(sql, resultSet -> {
            ArrayList<List<String>> table = new ArrayList<>();
            while (resultSet.next()) {
                ArrayList<String> row = new ArrayList<>();
                row.add(String.valueOf(resultSet.getInt("month")));
                row.add(String.valueOf(resultSet.getDouble("income")));
                table.add(row);
            }
            return table;
        });
    }

    public List<List<String>> getSalesByMonth() {
        String sql =
                "SELECT MONTH(o.orderdate) AS month, COUNT(p.id) AS product_count " +
                        "FROM product p JOIN orderproduct op ON p.id = op.productid " +
                        "JOIN `order` o ON op.orderid = o.id GROUP BY month;";

        return this.databaseService.performSQL(sql, resultSet -> {
            ArrayList<List<String>> table = new ArrayList<>();
            while (resultSet.next()) {
                ArrayList<String> row = new ArrayList<>();
                row.add(String.valueOf(resultSet.getInt("month")));
                row.add(String.valueOf(resultSet.getInt("product_count")));
                table.add(row);
            }
            return table;
        });
    }

    public List<List<String>> getSalesByYearAndMonth() {
        String sql =
                "SELECT YEAR(o.orderdate) AS year, " +
                        "MONTH(o.orderdate) AS month, " +
                        "COUNT(p.id) AS product_count FROM product p " +
                        "JOIN orderproduct op ON p.id = op.productid " +
                        "JOIN `order` o ON op.orderid = o.id " +
                        "GROUP BY year, month WITH ROLLUP;";

        return this.databaseService.performSQL(sql, resultSet -> {
            ArrayList<List<String>> table = new ArrayList<>();
            while (resultSet.next()) {
                ArrayList<String> row = new ArrayList<>();
                row.add(resultSet.getString("year"));
                row.add(String.valueOf(resultSet.getInt("month")));
                row.add(String.valueOf(resultSet.getInt("product_count")));
                table.add(row);
            }
            return table;
        });
    }

    public List<List<String>> getTopXCustomers(int x) {
        String sql = String.format(
                "SELECT u.id, SUM(o.totalprice) AS spent " +
                        "FROM user u JOIN `order` o ON u.id = o.userid " +
                        "GROUP BY u.id ORDER BY spent DESC LIMIT %d;", x);

        return this.databaseService.performSQL(sql, resultSet -> {
            ArrayList<List<String>> table = new ArrayList<>();
            while (resultSet.next()) {
                ArrayList<String> row = new ArrayList<>();
                row.add(String.valueOf(resultSet.getInt("id")));
                row.add(String.valueOf(resultSet.getDouble("spent")));
                table.add(row);
            }
            return table;
        });
    }

    public List<List<String>> getBottomXCustomers(int x) {
        String sql = String.format(
                "SELECT u.id, SUM(o.totalprice) AS spent " +
                        "FROM user u JOIN `order` o ON u.id = o.userid " +
                        "GROUP BY u.id ORDER BY spent LIMIT %d;", x);

        return this.databaseService.performSQL(sql, resultSet -> {
            ArrayList<List<String>> table = new ArrayList<>();
            while (resultSet.next()) {
                ArrayList<String> row = new ArrayList<>();
                row.add(String.valueOf(resultSet.getInt("id")));
                row.add(String.valueOf(resultSet.getDouble("spent")));
                table.add(row);
            }
            return table;
        });
    }
}
