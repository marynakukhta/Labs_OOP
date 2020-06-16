package com.kukhta.database;

import com.kukhta.entities.AutoClass;
import com.kukhta.entities.Automobile;
import com.kukhta.utils.SqlFileLoader;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AutomobileDao {
    public static Automobile getById(int id) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.initDB();
        Statement statement = connection.createStatement();

        String query = "SELECT * FROM Automobiles WHERE id=" + id;

        ResultSet resultSet = statement.executeQuery(query);

        Automobile automobile = null;

        if (resultSet.next()) {
            int seats = resultSet.getInt("seats");
            String name = resultSet.getString("name");
            Date lastInspectionDate = resultSet.getDate("last_inspection_date");
            AutoClass autoClass = AutoClass.valueOf(resultSet.getString("class"));
            automobile = new Automobile(id, name, seats, lastInspectionDate, autoClass);
        }

        resultSet.close();
        connection.close();

        return automobile;
    }

    public static List<Automobile> getFree() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.initDB();
        Statement statement = connection.createStatement();

        String query = SqlFileLoader.load("get_free_automobiles.sql");

        ResultSet resultSet = statement.executeQuery(query);

        List<Automobile> automobiles = new ArrayList<>();

        while (resultSet.next()) {
            automobiles.add(new Automobile(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getInt("seats"),
                    resultSet.getDate("last_inspection_date"),
                    AutoClass.valueOf(resultSet.getString("class"))
            ));
        }

        resultSet.close();
        connection.close();

        return automobiles;
    }

    public static List<Automobile> getAutomobiles() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.initDB();
        Statement statement = connection.createStatement();

        String query = "SELECT * FROM Automobiles ORDER BY id";

        ResultSet resultSet = statement.executeQuery(query);

        List<Automobile> automobiles = new ArrayList<>();

        while (resultSet.next()) {
            automobiles.add(new Automobile(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getInt("seats"),
                    resultSet.getDate("last_inspection_date"),
                    AutoClass.valueOf(resultSet.getString("class"))
            ));
        }

        resultSet.close();
        connection.close();

        return automobiles;
    }

    public static void inspect(int id) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.initDB();

        Date currentDate = new Date(new java.util.Date().getTime());

        String query = "UPDATE Automobiles SET last_inspection_date=? WHERE id=?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setDate(1, currentDate);
        statement.setInt(2, id);

        statement.executeUpdate();
        connection.close();
    }

    public static void insert(Automobile automobile) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.initDB();

        String query = "INSERT INTO Automobiles (id, name, seats, last_inspection_date, class) VALUES(DEFAULT, ?, ?, ?, ?::auto_class)";

        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, automobile.getName());
        statement.setInt(2, automobile.getSeats());
        statement.setObject(3, automobile.getLastInspectionDate());
        statement.setString(4, automobile.getAutoClass().name());

        System.out.println("AUTOMOBILE ADDING...\n" + query);

        statement.executeUpdate();
        connection.close();
    }
}
