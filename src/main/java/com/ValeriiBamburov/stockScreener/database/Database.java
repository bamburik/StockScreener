package com.ValeriiBamburov.stockScreener.database;

import com.ValeriiBamburov.stockScreener.config.Config;
import com.ValeriiBamburov.stockScreener.model.Stock;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    private static Connection connection;

    public static void connect() throws SQLException, IOException, ClassNotFoundException {
        Class.forName(Config.sqlServerDriverClassName());
        connection = DriverManager.getConnection(Config.databaseUrl()+";DatabaseName="+Config.databaseName(), Config.databaseUserName(), Config.databasePassword());
    }

    public static void insertStock(Stock stock, String executionDate) throws SQLException {
        connection.createStatement().executeUpdate(String.format(
                "INSERT INTO [Data] (Ticker, Forecast, Best_Forecast, Mark, Price, Execution_Date)" +
                        "VALUES ('%s', %s, %s, %s, %s, '%s')",
                stock.getName(), stock.getForecast(), stock.getBestForecast(), stock.getSmartScore(), stock.getCurrentPrice(), executionDate));
    }

    public static void disconnect() throws SQLException {
        connection.close();
    }
}
