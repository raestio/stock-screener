package com.rasto.stockscreener.restclient.core.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rasto.stockscreener.model.StockData;

import java.util.List;

public class StockTimeSeriesResponse {

    @JsonProperty("stocksData")
    private List<StockData> stocksData;

    public StockTimeSeriesResponse() {

    }

    public StockTimeSeriesResponse(List<StockData> stocksData) {
        this.stocksData = stocksData;
    }

    public List<StockData> getStocksData() {
        return stocksData;
    }

    public void setStocksData(List<StockData> stocksData) {
        this.stocksData = stocksData;
    }
}
