package com.rasto.stockscreener.service;

import com.rasto.stockscreener.dto.StockDTO;

import java.util.List;

public interface StockFavouritesService {
    List<StockDTO> addStockToFavourites(StockDTO stockDTO, Integer userId);
    List<StockDTO> removeStockFromFavourites(String stockSymbol, Integer userId);
    List<StockDTO> findFavouriteStocksByUserId(Integer userId);
}
