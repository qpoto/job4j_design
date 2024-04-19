package ru.job4j.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private final Properties properties;

    public TableEditor(Properties properties) throws IOException, SQLException {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws IOException, SQLException {
        try (InputStream in = TableEditor.class.getClassLoader().getResourceAsStream("app.properties")) {
            properties.load(in);
        }
        String url = properties.getProperty("url");
        String login = properties.getProperty("login");
        String password = properties.getProperty("password");
        connection = DriverManager.getConnection(url, login, password);
    }

    private void processingRequest(String script) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(script);
        }
    }

    public void createTable(String tableName) throws SQLException {
            String script = String.format(
                    "CREATE TABLE IF NOT EXISTS %s (%s, %s);",
                    tableName,
                    "id SERIAL PRIMARY KEY",
                    "name TEXT"
            );
            processingRequest(script);
    }

    public void dropTable(String tableName) {
        try {
            String script = String.format(
                    "DROP TABLE %s;",
                    tableName
            );
            processingRequest(script);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addColumn(String tableName, String columnName, String type) {
        try {
            String script = String.format(
                    "ALTER TABLE %s ADD %s %s;",
                    tableName,
                    columnName,
                    type
            );
            processingRequest(script);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropColumn(String tableName, String columnName) {
        try {
            String script = String.format(
                    "ALTER TABLE %s ADD %s;",
                    tableName,
                    columnName
            );
            processingRequest(script);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) {
        try {
            String script = String.format(
                    "ALTER TABLE %s RENAME COLUMN %s TO %s;",
                    tableName,
                    columnName,
                    newColumnName
            );
            processingRequest(script);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public String getTableScheme(String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format("SELECT * FROM %s LIMIT 1",
                    tableName));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }
}