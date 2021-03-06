package com.rasto.stockscreener.restclient;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import com.rasto.stockscreener.model.Stock;
import com.rasto.stockscreener.model.StockData;
import com.rasto.stockscreener.model.StockSearch;
import com.rasto.stockscreener.model.User;
import com.rasto.stockscreener.restclient.core.ContextHolder;
import com.rasto.stockscreener.restclient.core.request.LoginRequest;
import com.rasto.stockscreener.restclient.core.response.StockFavouritesResponse;
import com.rasto.stockscreener.restclient.core.response.StockSearchResponse;
import com.rasto.stockscreener.restclient.core.response.StockTimeSeriesResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class StockScreenerRestClientImpl implements StockScreenerRestClient {

    public static final String SERVER_API_URL = "http://localhost:8080";
    private ContextHolder contextHolder;
    private RestTemplate restTemplate;

    public StockScreenerRestClientImpl() {
        restTemplate = new RestTemplate();
        ObjectMapper objectMapper = new ObjectMapper()
                .registerModule(new ParameterNamesModule())
                .registerModule(new Jdk8Module())
                .registerModule(new JavaTimeModule());
        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        mappingJackson2HttpMessageConverter.setObjectMapper(objectMapper);
        restTemplate.getMessageConverters().add(0, mappingJackson2HttpMessageConverter);
        contextHolder = new ContextHolder();
    }

    @Override
    public List<StockSearch> searchStocksByKeyword(String keyword) throws UserNotLoggedInException {
        checkUserLogin();
        ResponseEntity<StockSearchResponse> responseEntity = restTemplate.getForEntity(SERVER_API_URL + "/stocks/search?keyword={keyword}", StockSearchResponse.class, keyword);
        return responseEntity.getBody().getStocks();
    }

    private void checkUserLogin() throws UserNotLoggedInException {
        if (!isUserLoggedIn()) {
            throw new UserNotLoggedInException();
        }
    }

    @Override
    public AuthResponse login(String username, String password) throws AuthException {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername(username);
        loginRequest.setPassword(password);
        try {
            ResponseEntity<AuthResponse> authResponseResponseEntity = restTemplate.postForEntity(SERVER_API_URL + "/auth/login", loginRequest, AuthResponse.class);
            if (authResponseResponseEntity.getStatusCode().is2xxSuccessful()) {
                User user = new User();
                user.setUsername(username);
                user.setPassword(password);
                contextHolder.setUser(user);
                restTemplate.getInterceptors().add(new BasicAuthorizationInterceptor(username, password));
                return authResponseResponseEntity.getBody();
            } else {
                throw new AuthException(authResponseResponseEntity.getBody().getMessage());
            }
        } catch (HttpClientErrorException e) {
            throw new AuthException();
        }
    }

    @Override
    public boolean isUserLoggedIn() {
        return contextHolder.getUser() != null;
    }

    @Override
    public List<Stock> getFavourites() throws UserNotLoggedInException {
        checkUserLogin();
        ResponseEntity<StockFavouritesResponse> responseEntity = restTemplate.getForEntity(SERVER_API_URL + "/me/favourites", StockFavouritesResponse.class);
        return responseEntity.getBody().getFavouriteStocks();
    }

    @Override
    public List<Stock> addToFavourites(Stock stock) throws UserNotLoggedInException {
        checkUserLogin();
        ResponseEntity<StockFavouritesResponse> responseEntity = restTemplate.postForEntity(SERVER_API_URL + "/me/favourites", stock, StockFavouritesResponse.class);
        return responseEntity.getBody().getFavouriteStocks();
    }

    @Override
    public List<Stock> removeFromFavourites(String symbol) throws UserNotLoggedInException {
        checkUserLogin();
        restTemplate.delete(SERVER_API_URL + "/me/favourites/{symbol}", symbol);
        return new ArrayList<>();
    }

    @Override
    public List<StockData> searchStockTimeSeries(String symbol, TimeSeriesType timeSeriesType) throws UserNotLoggedInException {
        checkUserLogin();
        ResponseEntity<StockTimeSeriesResponse> responseEntity = restTemplate.getForEntity(SERVER_API_URL + "/stocks/time-series/" + timeSeriesType.toString() + "?symbol={symbol}", StockTimeSeriesResponse.class, symbol);
        return responseEntity.getBody().getStocksData();
    }
}
