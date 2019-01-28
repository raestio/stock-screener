package com.rasto.richclient;

import com.rasto.stockscreener.model.Stock;
import com.rasto.stockscreener.model.StockData;
import com.rasto.stockscreener.model.StockSearch;
import com.rasto.stockscreener.restclient.AuthException;
import com.rasto.stockscreener.restclient.StockScreenerRestClient;
import com.rasto.stockscreener.restclient.StockScreenerRestClientImpl;
import com.rasto.stockscreener.restclient.TimeSeriesType;
import com.rasto.stockscreener.restclient.UserNotLoggedInException;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
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
    public Button daily;

    @FXML
    public Button weekly;

    @FXML
    public Button monthly;

    @FXML
    public Button favourite;
    public static final String ADD_TO_FAVOURITES = "Add to favourites";
    public static final String REMOVE_FROM_FAVOURITES = "Remove from favourites";

    @FXML
    public ListView<Stock> stocksListView;

    private Stock stockCurrentlyInChart;

    @FXML
    public CategoryAxis xAxis;

    @FXML
    public NumberAxis yAxis;

    @FXML
    public LineChart<String, Number> chart;
    public static final TimeSeriesType DEFAULT_TIME_SERIES_TYPE = TimeSeriesType.DAILY;

    private StockScreenerRestClient stockScreenerRestClient;

    public void initialize() {
        favourite.setDisable(true);
        setDisableChartButtons(true);
        stockScreenerRestClient = new StockScreenerRestClientImpl();
        try {
            stockScreenerRestClient.login("test", "test");
        } catch (AuthException e) {
            e.printStackTrace();
        }
        initializeSearchButton();
        initializeClearButton();
        initializeStocksListView();
        initializeChartButtons();
        initializeFavouriteButton();
    }

    private void initializeChartButtons() {
        initializeChartButton(daily, TimeSeriesType.DAILY);
        initializeChartButton(weekly, TimeSeriesType.WEEKLY);
        initializeChartButton(monthly, TimeSeriesType.MONTHLY);
    }

    private void initializeChartButton(Button chartButton, TimeSeriesType type) {
        chartButton.setOnMouseClicked(event -> chartStock(stockCurrentlyInChart, type));
    }

    private void initializeFavouriteButton() {
        favourite.setOnMouseClicked(event -> {
            try {
                if (favourite.getText().equals(ADD_TO_FAVOURITES)) {
                    stockScreenerRestClient.addToFavourites(stockCurrentlyInChart);
                } else if (favourite.getText().equals(REMOVE_FROM_FAVOURITES)) {
                    stockScreenerRestClient.removeFromFavourites(stockCurrentlyInChart.getSymbol());
                }
            } catch (UserNotLoggedInException e) {
                e.printStackTrace();
            }
        });
    }

    private void setDisableChartButtons(boolean b) {
        weekly.setDisable(b);
        monthly.setDisable(b);
        daily.setDisable(b);
    }

    private void initializeClearButton() {
        clearButton.setOnMouseClicked(event -> {
            fillListWithFavourites();
        });
    }

    private void clearStocksList() {
        stocksListView.getItems().clear();
    }

    private void fillListWithFavourites() {
        clearStocksList();
        try {
            var favs = stockScreenerRestClient.getFavourites();
            fillListViewWithStocks(favs);
        } catch (UserNotLoggedInException e) {
            e.printStackTrace();
        }
    }

    private void initializeStocksListView() {
        stocksListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        stocksListView.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Stock item, boolean empty) {
                super.updateItem(item, empty);
                if (!empty) {
                    setText(item.getSymbol() + " - " + item.getName());
                }
            }
        });

        stocksListView.setOnMouseClicked(event -> {
            Stock stock = stocksListView.getSelectionModel().getSelectedItem();
            chartStockFromStocksListView(stock, DEFAULT_TIME_SERIES_TYPE);
        });
    }

    private void chartStockFromStocksListView(Stock stock, TimeSeriesType timeSeriesType) {
        stockNameLabel.setText(stock.getName());
        chartStock(stock, timeSeriesType);
        setDisableChartButtons(false);
        setFavouriteButtonAccordingToUserFavourites(stock);
    }

    private void chartStock(Stock stock, TimeSeriesType timeSeriesType) {
        stockCurrentlyInChart = stock;
        List<StockData> stockData = new ArrayList<>();
        try {
            stockData = stockScreenerRestClient.searchStockTimeSeries(stock.getSymbol(), timeSeriesType);
        } catch (UserNotLoggedInException e) {
            e.printStackTrace();
        }
        fillChart(stockData, timeSeriesType);
    }

    private void setFavouriteButtonAccordingToUserFavourites(Stock stock) {
        try {
            if (isFavourite(stock)) {
                favourite.setText(REMOVE_FROM_FAVOURITES);
                favourite.setDisable(false);
            } else {
                favourite.setText(ADD_TO_FAVOURITES);
                favourite.setDisable(true);
            }
        } catch (UserNotLoggedInException e) {
            e.printStackTrace();
            favourite.setDisable(true);
        }
    }

    private boolean isFavourite(Stock stock) throws UserNotLoggedInException {
        return stockScreenerRestClient.getFavourites().stream().anyMatch(s -> s.getSymbol().equals(stock));
    }

    private void fillChart(List<StockData> stockData, TimeSeriesType timeSeriesType) {
        clearChart();
        xAxis.setLabel(timeSeriesType.toString());
        XYChart.Series series = new XYChart.Series();
        stockData.forEach(stockData1 -> series.getData().add(new XYChart.Data<String, Number>(stockData1.getDateTime().toLocalDate().toString(), stockData1.getClose())));
        chart.getData().add(series);
    }

    private void clearChart() {
        chart.getData().clear();
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
