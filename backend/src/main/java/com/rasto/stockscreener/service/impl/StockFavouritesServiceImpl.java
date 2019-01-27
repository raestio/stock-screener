package com.rasto.stockscreener.service.impl;

import com.rasto.stockscreener.data.entity.Stock;
import com.rasto.stockscreener.data.entity.User;
import com.rasto.stockscreener.data.repository.StockRepository;
import com.rasto.stockscreener.data.repository.UserRepository;
import com.rasto.stockscreener.dto.StockDTO;
import com.rasto.stockscreener.service.ConvertingService;
import com.rasto.stockscreener.service.StockFavouritesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class StockFavouritesServiceImpl implements StockFavouritesService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private ConvertingService convertingService;

    @Override
    public void addStockToFavourites(StockDTO stockDTO, Integer userId) {
        User user = userRepository.findById(userId).get();
        Optional<Stock> stockOptional = stockRepository.findById(stockDTO.getSymbol());
        Set<Stock> stockSet = new HashSet<>();
        if (stockOptional.isPresent()) {
            stockSet.add(stockOptional.get());
        } else {
            Stock stock = convertingService.convert(stockDTO, Stock.class);
            stockSet.add(stock);
        }
        user.setFavouriteStocks(stockSet);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void removeStockFromFavourites(String stockSymbol, Integer userId) {
        User user = userRepository.findById(userId).get();
        user.getFavouriteStocks().removeIf(stock -> stock.getSymbol().equals(stockSymbol));
        userRepository.save(user);
    }

    @Override
    @Transactional
    public List<StockDTO> findFavouriteStocksByUserId(Integer userId) {
        User user = userRepository.findById(userId).get();
        List<StockDTO> stockDTOS = new ArrayList<>();
        user.getFavouriteStocks().forEach(stock -> stockDTOS.add(convertingService.convert(stock, StockDTO.class)));
        return stockDTOS;
    }
}
