package com.rasto.stockscreener.rest.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rasto.stockscreener.dto.StockDTO;

import java.util.ArrayList;
import java.util.List;

public class StockFavouritesResponse {

    @JsonProperty("favouriteStocks")
    List<StockDTO> favouriteStocks = new ArrayList<>();

    public StockFavouritesResponse() {

    }

    public StockFavouritesResponse(List<StockDTO> favouriteStocks) {
        this.favouriteStocks = favouriteStocks;
    }

    public List<StockDTO> getFavouriteStocks() {
        return favouriteStocks;
    }

    public void setFavouriteStocks(List<StockDTO> favouriteStocks) {
        this.favouriteStocks = favouriteStocks;
    }
}
