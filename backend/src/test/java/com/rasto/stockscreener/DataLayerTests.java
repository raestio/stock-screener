package com.rasto.stockscreener;

import com.rasto.stockscreener.data.entity.Stock;
import com.rasto.stockscreener.data.entity.User;
import com.rasto.stockscreener.data.repository.StockRepository;
import com.rasto.stockscreener.data.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DataLayerTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StockRepository stockRepository;

    @Test
    public void testSaveUserAndRetrieve() {
        User user = new User();
        user.setUsername("test1");
        user.setPassword("AWefafeawefAWEF");
        user = userRepository.save(user);
        user = userRepository.findById(user.getId()).get();
        Assert.assertEquals(user.getUsername(), "test1");
        userRepository.deleteById(user.getId());
    }

    @Test
    public void testSaveStockAndRetrieve() {
        Stock stock = new Stock();
        stock.setSymbol("AAPL");
        stock.setCurrency("USD");
        stock = stockRepository.save(stock);
        stock = stockRepository.findById(stock.getSymbol()).get();
        Assert.assertEquals(stock.getCurrency(), "USD");
        stockRepository.deleteById("AAPL");
    }
}
