package com.rasto.richclient;

import com.rasto.stockscreener.model.Stock;
import com.rasto.stockscreener.model.StockSearch;
import com.rasto.stockscreener.restclient.AuthException;
import com.rasto.stockscreener.restclient.StockScreenerRestClient;
import com.rasto.stockscreener.restclient.StockScreenerRestClientImpl;
import com.rasto.stockscreener.restclient.UserNotLoggedInException;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MainController {
    
    @FXML
    public TextField searchTextField;

    @FXML
    public Label stockNameLabel;

    @FXML
    public Button searchButton;

    @FXML
    public Button clearButton;

    @FXML
    public ListView<Stock> stocksListView;

    private StockScreenerRestClient stockScreenerRestClient;

    public void initialize() {
        stockScreenerRestClient = new StockScreenerRestClientImpl();
        try {
            stockScreenerRestClient.login("test", "test");
        } catch (AuthException e) {
            e.printStackTrace();
        }
        initializeSearchButton();
        initializeClearButton();
        initializeStocksListView();
    }

    private void initializeClearButton() {
        clearButton.setOnMouseClicked(event -> stocksListView.getItems().clear());
    }

    private void initializeStocksListView() {
        stocksListView.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Stock item, boolean empty) {
                super.updateItem(item, empty);
                setText(item.getSymbol() + " - " + item.getName());
            }
        });
    }

    private void initializeSearchButton() {
        searchButton.setOnMouseClicked(event -> {
            var searchText = searchTextField.getText();
            if (searchText != null && !searchText.trim().isEmpty()) {
                List<StockSearch> stocks = new ArrayList<>();
                try {
                    stocks = stockScreenerRestClient.searchStocksByKeyword(searchText);
                } catch (UserNotLoggedInException e) {
                    e.printStackTrace();
                }
                fillListViewWithStocks(convert(stocks));
            }
        });
    }

    private List<Stock> convert(List<StockSearch> stockSearches) {
        return stockSearches.stream().map(this::convertStock).collect(Collectors.toList());
    }

    private Stock convertStock(StockSearch stockSearch) {
        Stock stock = new Stock();
        stock.setName(stockSearch.getName());
        stock.setSymbol(stockSearch.getSymbol());
        stock.setCurrency(stockSearch.getCurrency());
        stock.setMarketClose(stockSearch.getMarketClose());
        stock.setMarketOpen(stockSearch.getMarketOpen());
        stock.setRegion(stockSearch.getRegion());
        stock.setType(stockSearch.getType());
        stock.setTimezone(stockSearch.getTimezone());
        return stock;
    }

    private void fillListViewWithStocks(List<Stock> stocks) {
        stocksListView.setItems(FXCollections.observableArrayList(stocks));
    }
}
