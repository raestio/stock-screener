package com.rasto.stockscreener.service.impl;

import com.rasto.stockscreener.core.alphavantage.TimeSeriesSearch;
import com.rasto.stockscreener.core.alphavantage.output.StockSearchResponse;
import com.rasto.stockscreener.dto.StockSearchDTO;
import com.rasto.stockscreener.service.ConvertingService;
import com.rasto.stockscreener.service.StockSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockSearchServiceImpl implements StockSearchService {

    @Autowired
    private TimeSeriesSearch timeSeriesSearch;

    @Autowired
    private ConvertingService convertingService;

    @Override
    public List<StockSearchDTO> searchStockByKeyword(String keyword) {
        StockSearchResponse stockSearchResponse = timeSeriesSearch.search(keyword);
        List<StockSearchDTO> bestMatches = convertingService.convert(stockSearchResponse.getBestMatches(), StockSearchDTO.class);
        return bestMatches;
    }
}
