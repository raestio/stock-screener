package com.rasto.stockscreener.configuration;

import com.rasto.stockscreener.core.alphavantage.TimeSeriesSearch;
import com.rasto.stockscreener.util.HasLogger;
import org.patriques.AlphaVantageConnector;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AlphaVantageAPIConfiguration implements HasLogger {

    public static final int TIMEOUT = 3000;

    @Value("${alphavantage.api.key}")
    private String alphaVantageApiKey;

    @Bean
    public AlphaVantageConnector alphaVantageConnector() {
        AlphaVantageConnector apiConnector = new AlphaVantageConnector(alphaVantageApiKey, TIMEOUT);
        getLogger(AlphaVantageAPIConfiguration.class).info("AlphaVantageConnector created successfully.");
        return apiConnector;
    }

    @Bean
    public TimeSeriesSearch timeSeries(AlphaVantageConnector alphaVantageConnector) {
        TimeSeriesSearch timeSeries = new TimeSeriesSearch(alphaVantageConnector);
        getLogger(AlphaVantageAPIConfiguration.class).info("TimeSeriesSearch created successfully.");
        return timeSeries;
    }
}
