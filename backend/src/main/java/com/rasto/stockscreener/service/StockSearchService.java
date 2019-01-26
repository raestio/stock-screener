package com.rasto.stockscreener.service;

import com.rasto.stockscreener.dto.StockSearchDTO;

import java.util.List;

public interface StockSearchService {
    List<StockSearchDTO> searchStockByKeyword(String keyword);
}
