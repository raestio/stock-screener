package com.rasto.stockscreener.restclient;

public enum TimeSeriesType {
    MONTHLY("monthly"),
    DAILY("daily"),
    WEEKLY("weekly");

    private String value;

    TimeSeriesType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}
