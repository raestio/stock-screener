package com.rasto.stockscreener;

import com.rasto.stockscreener.dto.StockSearchDTO;
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
	public void testStockSearchByKeywordIUSG() {
		List<StockSearchDTO> stockSearchDataList = stockSearchService.searchStockByKeyword("IUSG");
		Assert.assertTrue(!stockSearchDataList.isEmpty());
		StockSearchDTO stockSearchData = stockSearchDataList.get(0);
		Assert.assertEquals(stockSearchData.getName(), "iShares Core S&P US Growth ETF");
		Assert.assertEquals(stockSearchData.getSymbol(), "IUSG");
		Assert.assertEquals(stockSearchData.getCurrency(), Currency.getInstance("USD"));
		Assert.assertEquals(stockSearchData.getTimezone(), "UTC-05");
	}

	@Test
	public void testStockSearchByKeywordThatDoesNotExist() {
		List<StockSearchDTO> stockSearchDataList = stockSearchService.searchStockByKeyword("IUSGafewfeferfERGREGafwefaewfe");
		Assert.assertTrue(stockSearchDataList.isEmpty());
	}
}

