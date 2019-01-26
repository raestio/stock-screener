package com.rasto.stockscreener.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalTime;
import java.util.Currency;

public class StockSearchDTO {

    @JsonProperty("symbol")
    private String symbol;

    @JsonProperty("name")
    private String name;

    @JsonProperty("type")
    private String type;

    @JsonProperty("region")
    private String region;

    @JsonProperty("marketOpen")
    private LocalTime marketOpen;

    @JsonProperty("marketClose")
    private LocalTime marketClose;

    @JsonProperty("timezone")
    private String timezone;

    @JsonProperty("currency")
    private Currency currency;

    @JsonProperty("matchScore")
    private Double matchScore;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public LocalTime getMarketOpen() {
        return marketOpen;
    }

    public void setMarketOpen(LocalTime marketOpen) {
        this.marketOpen = marketOpen;
    }

    public LocalTime getMarketClose() {
        return marketClose;
    }

    public void setMarketClose(LocalTime marketClose) {
        this.marketClose = marketClose;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Double getMatchScore() {
        return matchScore;
    }

    public void setMatchScore(Double matchScore) {
        this.matchScore = matchScore;
    }
}
