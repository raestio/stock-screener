package com.rasto.stockscreener.service;

import com.rasto.stockscreener.dto.StockDTO;

import java.util.List;

public interface StockFavouritesService {
    void addStockToFavourites(StockDTO stockDTO, Integer userId);
    void removeStockFromFavourites(String stockSymbol, Integer userId);
    List<StockDTO> findFavouriteStocksByUserId(Integer userId);
}
