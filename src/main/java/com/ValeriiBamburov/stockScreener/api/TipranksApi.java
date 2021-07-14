package com.ValeriiBamburov.stockScreener.api;

import java.util.ArrayList;
import java.util.List;

import com.ValeriiBamburov.stockScreener.model.Stock;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

import static com.ValeriiBamburov.stockScreener.common.Constants.*;

public class TipranksApi implements StockScreenerApi {

    private String baseUrl;

    public TipranksApi(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Override
    public List<Stock> getStocks(int page) {
        Response response = given().headers(CONTENT_TYPE, ContentType.JSON, ACCEPT, ContentType.JSON).
                when().get(baseUrl + String.format(GET_STOCKS_FORMAT, page)).
                then().extract().response();

        List<String> tickers = response.jsonPath().getList(ALL_TICKERS_JSONPATH);
        List<Stock> stocks = new ArrayList<>();

        for (String ticker : tickers ) {
            Integer smartScore = null;
            Double forecast = null;
            Double bestForecast = null;
            Double currentPrice = null;
            try {
                currentPrice = response.jsonPath().param("t", ticker).getDouble(String.format(CURRENT_PRICE_JSONPATH_FORMAT, "t"));
            } catch (Exception e) {
                System.out.println("Price for " + ticker + " wasn't found.");
            }
            try {
                smartScore = response.jsonPath().param("t", ticker).getInt(String.format(TIPRANKS_SCORE_JSONPATH_FORMAT, "t"));
            } catch (Exception e) {
                // ignore. Smart score can be null
            }
            try {
                forecast = Math.round(((response.jsonPath().param("t", ticker).getDouble(String.format(PRICE_TARGET_JSONPATH_FORMAT, "t")) - currentPrice) / currentPrice) * 10000.0) / 100.0;
            } catch (Exception e) {
                // ignore. Forecast can be null
            }
            try {
                bestForecast = Math.round(((response.jsonPath().param("t", ticker).getDouble(String.format(BEST_PRICE_TARGET_JSONPATH_FORMAT, "t")) - currentPrice) / currentPrice) * 10000.0) / 100.0;
            } catch (Exception e) {
                // ignore. Best forecast can be null
            }

            // don't add if no data found
            if (forecast != null || bestForecast != null || smartScore != null || currentPrice !=null) {
                stocks.add(new Stock(ticker, forecast, bestForecast, smartScore, currentPrice));
            }
        }
        return stocks;
    }
}
