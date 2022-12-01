package pl.britenet.campusapiapp.database;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ResultParser<T> {

    T parse(ResultSet resultSet) throws SQLException;

}