package com.rasto.stockscreener.core.alphavantage;

import com.rasto.stockscreener.core.alphavantage.input.Function;
import com.rasto.stockscreener.core.alphavantage.input.Keyword;
import com.rasto.stockscreener.core.alphavantage.output.StockSearchResponse;
import org.patriques.ApiConnector;
import org.patriques.TimeSeries;

public class TimeSeriesSearch extends TimeSeries {
    private ApiConnector apiConnector;

    public TimeSeriesSearch(ApiConnector apiConnector) {
        super(apiConnector);
        this.apiConnector = apiConnector;
    }

    public StockSearchResponse search(String keyword) {
        String json = apiConnector.getRequest(new Keyword(keyword), Function.SYMBOL_SEARCH);
        return StockSearchResponse.from(json);
    }
}
