package de.intexde.ffa.utilities;

import java.sql.*;

public class MySQL {

    Connection connection;

    private String Host = "";
    private String Database = "";
    private String User = "";
    private String Password = "";

    public MySQL(String Host, String Database, String User, String Password) {
        this.Host = Host;
        this.Database = Database;
        this.User = User;
        this.Password = Password;

        connect();
    }

    public MySQL(String Host, String Database, String User) {
        this.Host = Host;
        this.Database = Database;
        this.User = User;

        connect();
    }

    public void connect() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://" + Host + ":3306/" + Database + "?autoReconnect=true", User, Password);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public void close() {
        try {
            if(connection != null) {
                connection.close();
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public void update(String query) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            statement.close();
        } catch (SQLException exception) {
            connect();
            exception.printStackTrace();
        }
    }

    public ResultSet query(String query) {
        ResultSet resultSet = null;

        try {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
        } catch (SQLException exception) {
            connect();
            exception.printStackTrace();
        }


        return resultSet;
    }
}
