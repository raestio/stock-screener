package com.rasto.stockscreener.service.impl;

import com.rasto.stockscreener.core.alphavantage.TimeSeriesSearch;
import com.rasto.stockscreener.dto.StockDataDTO;
import com.rasto.stockscreener.service.ConvertingService;
import com.rasto.stockscreener.service.StockTimeSeriesService;
import org.patriques.output.timeseries.Daily;
import org.patriques.output.timeseries.Monthly;
import org.patriques.output.timeseries.Weekly;
import org.patriques.output.timeseries.data.StockData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockTimeSeriesServiceImpl implements StockTimeSeriesService {

    @Autowired
    private TimeSeriesSearch timeSeriesSearch;

    @Autowired
    private ConvertingService convertingService;

    @Override
    public List<StockDataDTO> getMonthly(String symbol) {
        Monthly monthly = timeSeriesSearch.monthly(symbol);
        List<StockData> stockDataList = monthly.getStockData();
        List<StockDataDTO> stockDataDTOs = convertingService.convert(stockDataList, StockDataDTO.class);
        return stockDataDTOs;
    }

    @Override
    public List<StockDataDTO> getWeekly(String symbol) {
        Weekly weekly = timeSeriesSearch.weekly(symbol);
        List<StockData> stockDataList = weekly.getStockData();
        List<StockDataDTO> stockDataDTOs = convertingService.convert(stockDataList, StockDataDTO.class);
        return stockDataDTOs;
    }

    @Override
    public List<StockDataDTO> getDaily(String symbol) {
        Daily daily = timeSeriesSearch.daily(symbol);
        List<StockData> stockDataList = daily.getStockData();
        List<StockDataDTO> stockDataDTOs = convertingService.convert(stockDataList, StockDataDTO.class);
        return stockDataDTOs;
    }
}
