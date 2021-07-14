package com.ValeriiBamburov.stockScreener;

import com.ValeriiBamburov.stockScreener.api.StockScreenerApi;
import com.ValeriiBamburov.stockScreener.api.TipranksApi;
import com.ValeriiBamburov.stockScreener.config.Config;
import com.ValeriiBamburov.stockScreener.database.Database;
import com.ValeriiBamburov.stockScreener.model.Stock;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

public class StockScreenerMain {

    static String today = new SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date(System.currentTimeMillis()));

    public static void main(String[] args) throws Exception {

        Database.connect();
        StockScreenerApi api = new TipranksApi(Config.baseUrl());

        boolean hasData = true;
        int page = 1;
        int stocksCounter = 1;
        while(hasData) {
            List<Stock> stocksList = api.getStocks(page);
            if (stocksList.size() != 0){
                for (Stock stock : stocksList) {
                    try {
                        Database.insertStock(stock, today);
                        System.out.println(stocksCounter + " - " + stock.getName());
                        stocksCounter++;
                    }
                    catch (SQLException e) {
                        System.out.println("Failed to insert " + stock.getName() + " to database.");
                        e.printStackTrace();
                    }
                }
                page++;
            }
            else {
                hasData = false;
            }
        }

        Database.disconnect();
    }
}
