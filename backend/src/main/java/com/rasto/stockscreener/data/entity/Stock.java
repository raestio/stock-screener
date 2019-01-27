package com.rasto.stockscreener.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "stock")
public class Stock {

    @Id
    @Column(name = "symbol")
    private String symbol;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "region")
    private String region;

    @Column(name = "marketOpen")
    private LocalTime marketOpen;

    @Column(name = "marketClose")
    private LocalTime marketClose;

    @Column(name = "timezone")
    private String timezone;

    @Column(name = "currency")
    private String currency;

    @ManyToMany(mappedBy = "favouriteStocks")
    private Set<User> user = new HashSet<>();

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

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
