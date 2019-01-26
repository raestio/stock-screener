package com.rasto.stockscreener.core.alphavantage.input;

import org.patriques.input.ApiParameter;

public enum Function implements ApiParameter {
    SYMBOL_SEARCH("SYMBOL_SEARCH");

    private final String urlParameter;

    Function(String urlParameter) {
        this.urlParameter = urlParameter;
    }

    public String getKey() {
        return "function";
    }

    public String getValue() {
        return this.urlParameter;
    }
}
