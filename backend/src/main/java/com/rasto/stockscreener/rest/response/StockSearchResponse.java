package com.rasto.stockscreener.rest.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rasto.stockscreener.dto.StockSearchDTO;

import java.util.ArrayList;
import java.util.List;

public class StockSearchResponse {

    @JsonProperty("bestMatches")
    List<StockSearchDTO> stocks = new ArrayList<>();

    public StockSearchResponse() {

    }

    public StockSearchResponse(List<StockSearchDTO> stocks) {
        this.stocks = stocks;
    }

    public List<StockSearchDTO> getStocks() {
        return stocks;
    }

    public void setStocks(List<StockSearchDTO> stocks) {
        this.stocks = stocks;
    }
}
