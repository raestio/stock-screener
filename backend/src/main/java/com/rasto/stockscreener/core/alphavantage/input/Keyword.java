package com.rasto.stockscreener.core.alphavantage.input;

import org.patriques.input.ApiParameter;

public class Keyword implements ApiParameter {

    private String keyword;

    public Keyword(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String getKey() {
        return "keywords";
    }

    @Override
    public String getValue() {
        return keyword;
    }
}
