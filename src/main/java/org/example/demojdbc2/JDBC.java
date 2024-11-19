package org.example.demojdbc2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBC {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/ecommerce";

        String user = "root";

        String password = "Lion5$belt123";

        try {
//            Registering the java class
            Class.forName("com.mysql.cj.jdbc.Driver");

//            Create connection

            Connection connection = DriverManager.getConnection(url, user, password);

//            Create statement

            Statement statement = connection.createStatement();



            int insert = statement.executeUpdate("INSERT INTO users VALUES (1, 'Ned', 'Stark', 'nedboy', 'ned@gmail.com', '00001')");

            System.out.println(insert + " row inserted");

            connection.close();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);

        }
    }
}
