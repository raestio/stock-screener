package com.rasto.stockscreener.rest.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rasto.stockscreener.dto.StockDataDTO;

import java.util.List;

public class StockTimeSeriesResponse {

    @JsonProperty("stocksData")
    private List<StockDataDTO> stocksData;

    public StockTimeSeriesResponse() {

    }

    public StockTimeSeriesResponse(List<StockDataDTO> stocksData) {
        this.stocksData = stocksData;
    }

    public List<StockDataDTO> getStocksData() {
        return stocksData;
    }

    public void setStocksData(List<StockDataDTO> stocksData) {
        this.stocksData = stocksData;
    }
}
