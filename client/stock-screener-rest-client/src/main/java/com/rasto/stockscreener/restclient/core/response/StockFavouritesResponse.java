package com.rasto.stockscreener.restclient.core.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rasto.stockscreener.model.Stock;

import java.util.ArrayList;
import java.util.List;

public class StockFavouritesResponse {

    @JsonProperty("favouriteStocks")
    List<Stock> favouriteStocks = new ArrayList<>();

    public StockFavouritesResponse() {

    }

    public StockFavouritesResponse(List<Stock> favouriteStocks) {
        this.favouriteStocks = favouriteStocks;
    }

    public List<Stock> getFavouriteStocks() {
        return favouriteStocks;
    }

    public void setFavouriteStocks(List<Stock> favouriteStocks) {
        this.favouriteStocks = favouriteStocks;
    }
}
