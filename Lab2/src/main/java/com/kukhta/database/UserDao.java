package com.kukhta.database;

import com.kukhta.entities.User;
import com.kukhta.entities.UserRole;
import com.kukhta.utils.SqlFileLoader;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    public static User getById(int id) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.initDB();
        Statement statement = connection.createStatement();

        String query = "SELECT * FROM Users WHERE id=" + id;

        ResultSet resultSet = statement.executeQuery(query);

        User user = null;

        if (resultSet.next()) {
            user = new User(id,
                    resultSet.getString("email"),
                    resultSet.getString("password"),
                    resultSet.getInt("car_id"),
                    resultSet.getString("name"),
                    resultSet.getString("surname"),
                    UserRole.valueOf(resultSet.getString("role")));
        }

        resultSet.close();
        connection.close();

        return user;
    }

    public static int getBest() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.initDB();
        Statement statement = connection.createStatement();

        String query = SqlFileLoader.load("get_best.sql");

        ResultSet resultSet = statement.executeQuery(query);

        int res = 0;

        while (resultSet.next()) {
            res = resultSet.getInt("id");
        }

        resultSet.close();
        connection.close();

        return res;
    }

    public static List<User> getUsers() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.initDB();
        Statement statement = connection.createStatement();

        String query = "SELECT * FROM Users ORDER BY id";

        ResultSet resultSet = statement.executeQuery(query);

        List<User> users = new ArrayList<>();

        while (resultSet.next()) {
            users.add(new User(resultSet.getInt("id"),
                    resultSet.getString("email"),
                    resultSet.getString("password"),
                    resultSet.getInt("car_id"),
                    resultSet.getString("name"),
                    resultSet.getString("surname"),
                    UserRole.valueOf(resultSet.getString("role"))));
        }

        resultSet.close();
        connection.close();

        return users;
    }

    public static User getByLogin(String email, String password) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.initDB();

        String query = "SELECT * FROM Users WHERE email=? and password=?";

        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, email);
        statement.setString(2, password);
        ResultSet resultSet = statement.executeQuery();

        User user = null;

        if (resultSet.next()) {
            int id = resultSet.getInt("id");
            int carId = resultSet.getInt("car_id");
            String name = resultSet.getString("name");
            String surname = resultSet.getString("surname");
            UserRole role = UserRole.valueOf(resultSet.getString("role"));
            user = new User(id, email, password, carId, name, surname, role);
        }

        resultSet.close();
        connection.close();

        return user;
    }

    public static void insert(User user) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.initDB();

        String query = "INSERT INTO Users (id, email, password, car_id, name, surname, role) VALUES(DEFAULT, ?, ?, ?, ?, ?, ?::user_role)";

        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, user.getEmail());
        statement.setString(2, user.getPassword());
        statement.setInt(3, user.getCarId());
        statement.setString(4, user.getName());
        statement.setString(5, user.getSurname());
        statement.setString(6, user.getRole().name());

        statement.executeUpdate();
        connection.close();
    }
}
