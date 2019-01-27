package com.rasto.stockscreener.service;

import com.rasto.stockscreener.dto.StockDataDTO;

import java.util.List;

public interface StockTimeSeriesService {
    List<StockDataDTO> getMonthly(String symbol);
    List<StockDataDTO> getWeekly(String symbol);
    List<StockDataDTO> getDaily(String symbol);
}
