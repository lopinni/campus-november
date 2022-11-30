package pl.britenet.campusapiapp.service;

import pl.britenet.campusapiapp.database.DatabaseService;
import pl.britenet.campusapiapp.model.Category;
import pl.britenet.campusapiapp.model.User;
import pl.britenet.campusapiapp.model.builder.CategoryBuilder;
import pl.britenet.campusapiapp.model.builder.UserBuilder;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicBoolean;

public class UserService {

    private final DatabaseService databaseService;

    public UserService(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    public User getUser(int id) {
        String sql = String.format("SELECT * FROM user WHERE id = %d", id);

        return this.databaseService.performSQL(sql, resultSet -> {
            try {
                if (resultSet.next()) {
                    return new UserBuilder(new User())
                            .setId(resultSet.getInt("id"))
                            .setLogin(resultSet.getString("login"))
                            .setPassword(resultSet.getString("password"))
                            .setName(resultSet.getString("name"))
                            .setSurname(resultSet.getString("surname"))
                            .setCity(resultSet.getString("city"))
                            .setStreet(resultSet.getString("street"))
                            .setCountry(resultSet.getString("country"))
                            .setZipCode(resultSet.getString("zipcode"))
                            .setProfilePicturePath(resultSet.getString("profilepicturepath"))
                            .getUser();

                }
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
            return null;
        });
    }

    public List<User> getAll() {
        String sql = "SELECT * FROM user";

        return this.databaseService.performSQL(sql, resultSet -> {
            try {
                ArrayList<User> userList = new ArrayList<>();
                while (resultSet.next()) {
                    userList.add(new UserBuilder(new User())
                            .setId(resultSet.getInt("id"))
                            .setLogin(resultSet.getString("login"))
                            .setPassword(resultSet.getString("password"))
                            .setName(resultSet.getString("name"))
                            .setSurname(resultSet.getString("surname"))
                            .setCity(resultSet.getString("city"))
                            .setStreet(resultSet.getString("street"))
                            .setCountry(resultSet.getString("country"))
                            .setZipCode(resultSet.getString("zipcode"))
                            .setProfilePicturePath(resultSet.getString("profilepicturepath"))
                            .getUser());
                }
                return userList;
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        });
    }

    public List<List<User>> getPaginated(int rownum) {
        String sql = "SELECT * FROM user";
        AtomicBoolean isRunning = new AtomicBoolean(true);

        return this.databaseService.performSQL(sql, resultSet -> {
            try {
                ArrayList<List<User>> paginatedList = new ArrayList<>();
                while (isRunning.get()) {
                    ArrayList<User> userList = new ArrayList<>();
                    for (int i=1; i<=rownum; i++) {
                        if(resultSet.next()) {
                            userList.add(new UserBuilder(new User())
                                    .setId(resultSet.getInt("id"))
                                    .setLogin(resultSet.getString("login"))
                                    .setPassword(resultSet.getString("password"))
                                    .setName(resultSet.getString("name"))
                                    .setSurname(resultSet.getString("surname"))
                                    .setCity(resultSet.getString("city"))
                                    .setStreet(resultSet.getString("street"))
                                    .setCountry(resultSet.getString("country"))
                                    .setZipCode(resultSet.getString("zipcode"))
                                    .setProfilePicturePath(resultSet.getString("profilepicturepath"))
                                    .getUser());
                        } else isRunning.set(false);
                    }
                    if(!userList.isEmpty()) paginatedList.add(userList);
                }
                return paginatedList;
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        });
    }

    public void insertUser(User user) {
        String dml = String.format(
                Locale.US,
                "INSERT INTO user (login, password, name, surname, city, street, country, zipcode, profilepicturepath)" +
                        "VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s')",
                user.getLogin(),
                user.getPassword(),
                user.getName(),
                user.getSurname(),
                user.getCity(),
                user.getStreet(),
                user.getCountry(),
                user.getZipCode(),
                user.getProfilePicturePath()
        );
        this.databaseService.performDML(dml);
    }

    public void updateUser(User user) {
        String dml = String.format(
                Locale.US,
                "UPDATE user SET " +
                        "login = '%s', " +
                        "password = '%s', " +
                        "name = '%s', " +
                        "surname = '%s', " +
                        "city = '%s', " +
                        "street = '%s', " +
                        "country = '%s', " +
                        "zipcode = '%s', " +
                        "profilepicturepath = '%s' " +
                        "WHERE id = %d",
                user.getLogin(),
                user.getPassword(),
                user.getName(),
                user.getSurname(),
                user.getCity(),
                user.getStreet(),
                user.getCountry(),
                user.getZipCode(),
                user.getProfilePicturePath(),
                user.getId()
        );
        this.databaseService.performDML(dml);
    }

    public void deleteUser(int id) {
        String dml = String.format("DELETE FROM user WHERE id = %d", id);
        this.databaseService.performDML(dml);
    }

}
