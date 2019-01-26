package com.rasto.stockscreener;

import com.rasto.stockscreener.core.alphavantage.output.StockSearchData;
import com.rasto.stockscreener.service.StockSearchService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Currency;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StockScreenerApplicationTests {

	@Autowired
	private StockSearchService stockSearchService;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testStockSearchByKeyword() {
		List<StockSearchData> stockSearchDataList = stockSearchService.searchStockByKeyword("IUSG");
		Assert.assertTrue(!stockSearchDataList.isEmpty());
		StockSearchData stockSearchData = stockSearchDataList.get(0);
		Assert.assertEquals(stockSearchData.getName(), "iShares Core S&P US Growth ETF");
		Assert.assertEquals(stockSearchData.getSymbol(), "IUSG");
		Assert.assertEquals(stockSearchData.getCurrency(), Currency.getInstance("USD"));
		Assert.assertEquals(stockSearchData.getTimezone(), "UTC-05");
	}
}

