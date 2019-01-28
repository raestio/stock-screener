package com.rasto.stockscreener.restclient.core.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rasto.stockscreener.model.StockSearch;

import java.util.ArrayList;
import java.util.List;

public class StockSearchResponse {

    @JsonProperty("bestMatches")
    List<StockSearch> stocks = new ArrayList<>();

    public StockSearchResponse() {

    }

    public StockSearchResponse(List<StockSearch> stocks) {
        this.stocks = stocks;
    }

    public List<StockSearch> getStocks() {
        return stocks;
    }

    public void setStocks(List<StockSearch> stocks) {
        this.stocks = stocks;
    }
}
