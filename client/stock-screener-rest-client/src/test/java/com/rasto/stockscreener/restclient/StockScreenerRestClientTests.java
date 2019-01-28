package com.rasto.stockscreener.restclient;

import org.junit.Test;

public class StockScreenerRestClientTests {

    private StockScreenerRestClient stockScreenerRestClient = new StockScreenerRestClientImpl();

    @Test
    public void testLogin() throws AuthException {
        stockScreenerRestClient.login("test", "test");
    }

    @Test(expected = AuthException.class)
    public void testLoginBadCredentials() throws AuthException {
        stockScreenerRestClient.login("tesstt", "testt");
    }
}
