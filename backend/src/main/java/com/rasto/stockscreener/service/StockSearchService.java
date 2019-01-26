package com.rasto.stockscreener.service;

import com.rasto.stockscreener.core.alphavantage.output.StockSearchData;

import java.util.List;

public interface StockSearchService {
    List<StockSearchData> searchStockByKeyword(String keyword);
}
