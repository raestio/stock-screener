package com.rasto.stockscreener.data.repository;

import com.rasto.stockscreener.data.entity.Stock;
import org.springframework.data.repository.CrudRepository;

public interface StockRepository extends CrudRepository<Stock, String> {
}
