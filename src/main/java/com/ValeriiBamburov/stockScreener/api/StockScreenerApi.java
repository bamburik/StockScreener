package com.ValeriiBamburov.stockScreener.api;

import com.ValeriiBamburov.stockScreener.model.Stock;

import java.util.List;

public interface StockScreenerApi {
    List<Stock> getStocks(int page);
}
