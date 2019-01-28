package com.rasto.stockscreener.restclient;


import com.rasto.stockscreener.model.Stock;
import com.rasto.stockscreener.model.StockData;
import com.rasto.stockscreener.model.StockSearch;

import java.util.List;

public interface StockScreenerRestClient {
    List<StockSearch> searchStocksByKeyword(String keyword) throws UserNotLoggedInException;
    AuthResponse login(String username, String password) throws AuthException;
    boolean isUserLoggedIn();
    List<Stock> getFavourites() throws UserNotLoggedInException;
    List<Stock> addToFavourites(Stock stock) throws UserNotLoggedInException;
    List<Stock> removeFromFavourites(String symbol) throws UserNotLoggedInException;
    List<StockData> searchStockTimeSeries(String symbol, TimeSeriesType timeSeriesType) throws UserNotLoggedInException;
}
