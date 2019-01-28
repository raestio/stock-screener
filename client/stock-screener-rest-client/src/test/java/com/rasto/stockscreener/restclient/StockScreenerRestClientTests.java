package com.rasto.stockscreener.restclient;

import com.rasto.stockscreener.model.StockData;
import com.rasto.stockscreener.model.StockSearch;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class StockScreenerRestClientTests {

    private StockScreenerRestClient stockScreenerRestClient = new StockScreenerRestClientImpl();

    @Before
    public void login() throws AuthException {
        stockScreenerRestClient.login("test", "test");
    }

    @Test
    public void testLogin() throws AuthException {
        stockScreenerRestClient.login("test", "test");
    }

    @Test(expected = AuthException.class)
    public void testLoginBadCredentials() throws AuthException {
        stockScreenerRestClient.login("tesstt", "testt");
    }

    @Test
    public void testFindStockByKeyword() throws UserNotLoggedInException {
        List<StockSearch> stockSearchList = stockScreenerRestClient.searchStocksByKeyword("IUSG");
        Assert.assertEquals(2, stockSearchList.size());
    }

    @Test
    public void testGetMonthly() throws UserNotLoggedInException {
        List<StockData> stockSearchList = stockScreenerRestClient.searchStockTimeSeries("IUSG", TimeSeriesType.MONTHLY);
        Assert.assertTrue(stockSearchList.size() > 120);
    }

}
