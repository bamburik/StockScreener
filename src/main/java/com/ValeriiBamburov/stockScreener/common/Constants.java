package com.ValeriiBamburov.stockScreener.common;

public class Constants {
    public static final String GET_STOCKS_FORMAT = "/api/Screener/GetStocks/?page=%d";
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String ACCEPT = "Accept";

    // JsonPaths
    public static final String ALL_TICKERS_JSONPATH = "data.ticker";
    public static final String CURRENT_PRICE_JSONPATH_FORMAT = "extraData.findAll{s -> s.ticker == %s}[0].prices[-1].p";
    public static final String TIPRANKS_SCORE_JSONPATH_FORMAT = "extraData.findAll{s -> s.ticker == %s}[0].research.tipRanksScore";
    public static final String PRICE_TARGET_JSONPATH_FORMAT = "extraData.findAll{s -> s.ticker == %s}[0].research.priceTarget";
    public static final String BEST_PRICE_TARGET_JSONPATH_FORMAT = "extraData.findAll{s -> s.ticker == %s}[0].research.bestPriceTarget";
}
