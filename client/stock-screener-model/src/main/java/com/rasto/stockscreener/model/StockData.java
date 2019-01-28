package com.rasto.stockscreener.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class StockData {

    @JsonProperty("dateTime")
    private LocalDateTime dateTime;

    @JsonProperty("open")
    private Double open;

    @JsonProperty("high")
    private Double high;

    @JsonProperty("low")
    private Double low;

    @JsonProperty("close")
    private Double close;

    @JsonProperty("adjustedClose")
    private Double adjustedClose;

    @JsonProperty("volume")
    private Long volume;

    @JsonProperty("dividendAmount")
    private Double dividendAmount;

    @JsonProperty("splitCoefficient")
    private Double splitCoefficient;

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Double getOpen() {
        return open;
    }

    public void setOpen(Double open) {
        this.open = open;
    }

    public Double getHigh() {
        return high;
    }

    public void setHigh(Double high) {
        this.high = high;
    }

    public Double getLow() {
        return low;
    }

    public void setLow(Double low) {
        this.low = low;
    }

    public Double getClose() {
        return close;
    }

    public void setClose(Double close) {
        this.close = close;
    }

    public Double getAdjustedClose() {
        return adjustedClose;
    }

    public void setAdjustedClose(Double adjustedClose) {
        this.adjustedClose = adjustedClose;
    }

    public Long getVolume() {
        return volume;
    }

    public void setVolume(Long volume) {
        this.volume = volume;
    }

    public Double getDividendAmount() {
        return dividendAmount;
    }

    public void setDividendAmount(Double dividendAmount) {
        this.dividendAmount = dividendAmount;
    }

    public Double getSplitCoefficient() {
        return splitCoefficient;
    }

    public void setSplitCoefficient(Double splitCoefficient) {
        this.splitCoefficient = splitCoefficient;
    }
}
