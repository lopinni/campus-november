package pl.britenet.campusapiapp.service;

import pl.britenet.campusapiapp.database.DatabaseService;
import pl.britenet.campusapiapp.model.Category;
import pl.britenet.campusapiapp.model.builder.CategoryBuilder;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicBoolean;

public class CategoryService {

    private final DatabaseService databaseService;

    public CategoryService(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    public Category getCategory(int id) {
        String sql = String.format("SELECT * FROM category WHERE id = %d", id);

        return this.databaseService.performSQL(sql, resultSet -> {
            try {
                if (resultSet.next()) {
                    return new CategoryBuilder(new Category())
                            .setId(resultSet.getInt("id"))
                            .setName(resultSet.getString("name"))
                            .setImagePath(resultSet.getString("imagepath"))
                            .getCategory();
                }
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
            return null;
        });
    }

    public List<Category> getAll() {
        String sql = "SELECT * FROM category";

        return this.databaseService.performSQL(sql, resultSet -> {
            try {
                ArrayList<Category> categoryList = new ArrayList<>();
                while (resultSet.next()) {
                    categoryList.add(new CategoryBuilder(new Category())
                            .setId(resultSet.getInt("id"))
                            .setName(resultSet.getString("name"))
                            .setImagePath(resultSet.getString("imagepath"))
                            .getCategory());
                }
                return categoryList;
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        });
    }

    public List<List<Category>> getPaginated(int rownum) {
        String sql = "SELECT * FROM category";
        AtomicBoolean isRunning = new AtomicBoolean(true);

        return this.databaseService.performSQL(sql, resultSet -> {
            try {
                ArrayList<List<Category>> paginatedList = new ArrayList<>();
                while (isRunning.get()) {
                    ArrayList<Category> categoryList = new ArrayList<>();
                    for (int i=1; i<=rownum; i++) {
                        if(resultSet.next()) {
                            categoryList.add(new CategoryBuilder(new Category())
                                    .setId(resultSet.getInt("id"))
                                    .setName(resultSet.getString("name"))
                                    .setImagePath(resultSet.getString("imagepath"))
                                    .getCategory());
                        } else isRunning.set(false);
                    }
                    if(!categoryList.isEmpty()) paginatedList.add(categoryList);
                }
                return paginatedList;
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        });
    }

    public void insertCategory(Category category) {
        String dml = String.format(
                Locale.US,
                "INSERT INTO category (name, imagepath) VALUES ('%s', '%s')",
                category.getName(),
                category.getImagePath()
        );
        this.databaseService.performDML(dml);
    }

    public void updateCategory(Category category) {
        String dml = String.format(
                Locale.US,
                "UPDATE category SET name = '%s', imagepath = '%s' WHERE id = %d",
                category.getName(),
                category.getImagePath(),
                category.getId()
        );
        this.databaseService.performDML(dml);
    }

    public void deleteCategory(int id) {
        String dml = String.format("DELETE FROM category WHERE id = %d", id);
        this.databaseService.performDML(dml);
    }
}