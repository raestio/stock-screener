package com.rasto.stockscreener.core.alphavantage.output;

import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import org.patriques.output.AlphaVantageException;
import org.patriques.output.JsonParser;

import java.lang.reflect.Type;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

public class StockSearchResponse {
    private List<StockSearchData> bestMatches;

    public StockSearchResponse(List<StockSearchData> bestMatches) {
        this.bestMatches = bestMatches;
    }

    public static StockSearchResponse from(String json) {
        StockSearchResponse.Parser parser = new StockSearchResponse.Parser();
        return parser.parseJson(json);
    }

    private static class Parser extends JsonParser<StockSearchResponse> {
        private static final String KEY_SYMBOL = "1. symbol";
        private static final String KEY_NAME = "2. name";
        private static final String KEY_TYPE = "3. type";
        private static final String KEY_REGION = "4. region";
        private static final String KEY_MARKETOPEN = "5. marketOpen";
        private static final String KEY_MARKETCLOSE = "6. marketClose";
        private static final String KEY_TIMEZONE = "7. timezone";
        private static final String KEY_CURRENCY = "8. currency";
        private static final String KEY_MATCHSCORE = "9. matchScore";

        private Parser() {
        }

        @Override
        protected StockSearchResponse resolve(JsonObject rootObject) {
            Type dataType = (new TypeToken<List<Map<String, String>>>() {}).getType();
            List<StockSearchData> bestMatches = new ArrayList<>();

            try {
                List<Map<String, String>> bestMatchesStrings = GSON.fromJson(rootObject.get(this.getStockSearchResponseKey()), dataType);
                for (Map<String, String> bestMatch : bestMatchesStrings) {
                    StockSearchData stockSearchData = new StockSearchData();
                    stockSearchData.setCurrency(Currency.getInstance(bestMatch.get(KEY_CURRENCY)));
                    stockSearchData.setMarketClose(LocalTime.parse(bestMatch.get(KEY_MARKETCLOSE)));
                    stockSearchData.setMarketOpen(LocalTime.parse(bestMatch.get(KEY_MARKETOPEN)));
                    stockSearchData.setMatchScore(Double.parseDouble(bestMatch.get(KEY_MATCHSCORE)));
                    stockSearchData.setName(bestMatch.get(KEY_NAME));
                    stockSearchData.setRegion(bestMatch.get(KEY_REGION));
                    stockSearchData.setTimezone(bestMatch.get(KEY_TIMEZONE));
                    stockSearchData.setType(bestMatch.get(KEY_TYPE));
                    stockSearchData.setSymbol(bestMatch.get(KEY_SYMBOL));
                    bestMatches.add(stockSearchData);
                }

                return new StockSearchResponse(bestMatches);
            } catch (JsonSyntaxException e) {
                throw new AlphaVantageException("Search endpoint API change.", e);
            }
        }

        private String getStockSearchResponseKey() {
            return "bestMatches";
        }
    }

    public List<StockSearchData> getBestMatches() {
        return bestMatches;
    }

    public void setBestMatches(List<StockSearchData> bestMatches) {
        this.bestMatches = bestMatches;
    }
}
