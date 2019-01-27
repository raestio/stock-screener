package com.rasto.stockscreener;

import com.rasto.stockscreener.data.entity.User;
import com.rasto.stockscreener.data.repository.UserRepository;
import com.rasto.stockscreener.dto.StockDTO;
import com.rasto.stockscreener.service.StockFavouritesService;
import com.rasto.stockscreener.service.UserService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Currency;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceLayerTests {

    @Autowired
    private StockFavouritesService stockFavouritesService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    private static final String USERNAME = "test1";

    @Before
    public void setUpUser() {
        User user = new User();
        user.setUsername(USERNAME);
        user.setPassword("AWefafeawefAWEF");
        userService.save(user);
    }

    @After
    public void removeUser() {
        userRepository.deleteById(userService.findByUsername(USERNAME).getId());
    }

    @Test
    public void testAddStockToFavourites() {
        User user = userService.findByUsername(USERNAME);
        stockFavouritesService.addStockToFavourites(getMockStock(), user.getId());
        Optional<StockDTO> stockOpt = stockFavouritesService.findFavouriteStocksByUserId(user.getId()).stream().filter(stockDTO -> stockDTO.getSymbol().equals(getMockStock().getSymbol())).findFirst();
        Assert.assertEquals(stockOpt.get().getCurrency().toString(), getMockStock().getCurrency().toString());
    }

    @Test
    public void testRemoveStockFromFavourites() {
        User user = userService.findByUsername(USERNAME);
        stockFavouritesService.addStockToFavourites(getMockStock(), user.getId());
        stockFavouritesService.removeStockFromFavourites(getMockStock().getSymbol(), user.getId());
        Assert.assertTrue(stockFavouritesService.findFavouriteStocksByUserId(user.getId()).isEmpty());
    }

    private StockDTO getMockStock() {
        StockDTO stockDTO = new StockDTO();
        stockDTO.setSymbol("IUSG");
        stockDTO.setCurrency(Currency.getInstance("USD"));
        return stockDTO;
    }
}
