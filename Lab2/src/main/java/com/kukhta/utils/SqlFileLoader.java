package com.kukhta.utils;

import com.kukhta.database.DBConnection;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlFileLoader {
    public static String load(String fileName) {

        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString();
        System.out.println("Current relative path is: " + s);

        File file = new File("C:/Workspace/Repos/knu-3/OOP/OOP - Lab 2/Sem 6/Lab 2/src/main/resources/scripts/" + fileName);
        StringBuilder res = new StringBuilder("");

        BufferedReader bufferedReader;

        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            String line = bufferedReader.readLine();
            while (line != null) {
                res.append(line);
                res.append(" ");
                line = bufferedReader.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return res.toString();
    }

    public static int getCountedFromScript(String filename) throws SQLException, ClassNotFoundException {
        int res = 0;
        Connection connection = DBConnection.initDB();
        Statement statement = connection.createStatement();


        String query = SqlFileLoader.load(filename);

        ResultSet resultSet = statement.executeQuery(query);

        while (resultSet.next()) {
            res = resultSet.getInt("counted");
        }

        resultSet.close();
        connection.close();

        return res;
    }
}
