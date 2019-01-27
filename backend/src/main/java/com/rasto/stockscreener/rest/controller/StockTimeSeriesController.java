package com.rasto.stockscreener.rest.controller;

import com.rasto.stockscreener.dto.StockDataDTO;
import com.rasto.stockscreener.rest.response.StockTimeSeriesResponse;
import com.rasto.stockscreener.service.StockTimeSeriesService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/stocks/time-series")
public class StockTimeSeriesController {

    private StockTimeSeriesService stockTimeSeriesService;

    @GetMapping(path = "/monthly")
    @ResponseBody
    public StockTimeSeriesResponse getMonthly(@RequestParam("symbol") String symbol) {
        List<StockDataDTO> stockDataDTOList = stockTimeSeriesService.getMonthly(symbol);
        return new StockTimeSeriesResponse(stockDataDTOList);
    }

    @GetMapping(path = "/weekly")
    @ResponseBody
    public StockTimeSeriesResponse getWeekly(@RequestParam("symbol") String symbol) {
        List<StockDataDTO> stockDataDTOList = stockTimeSeriesService.getWeekly(symbol);
        return new StockTimeSeriesResponse(stockDataDTOList);
    }

    @GetMapping(path = "/daily")
    @ResponseBody
    public StockTimeSeriesResponse getDaily(@RequestParam("symbol") String symbol) {
        List<StockDataDTO> stockDataDTOList = stockTimeSeriesService.getDaily(symbol);
        return new StockTimeSeriesResponse(stockDataDTOList);
    }
}
