package com.ValeriiBamburov.stockScreener.config;

import com.ValeriiBamburov.stockScreener.StockScreenerMain;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class Config {

    public static String databaseUrl() throws IOException {
        return getProperty("db.url");
    }

    public static String databaseName() throws IOException {
        return getProperty("db.name");
    }

    public static String databaseUserName() throws IOException {
        return getProperty("db.user");
    }

    public static String databasePassword() throws IOException {
        return getProperty("db.password");
    }

    public static String sqlServerDriverClassName() throws IOException {
        return getProperty("sqlServerDriverClassName");
    }

    public static String baseUrl() throws IOException {
        return getProperty("baseUrl");
    }

    public static String getProperty(String property) throws IOException {
        InputStream input = StockScreenerMain.class.getClassLoader().getResourceAsStream("config.properties");
        Properties properties = new Properties();
        properties.load(input);
        return properties.getProperty(property);
    }
}
