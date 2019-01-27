package com.rasto.stockscreener;

import com.rasto.stockscreener.dto.StockDataDTO;
import com.rasto.stockscreener.dto.StockSearchDTO;
import com.rasto.stockscreener.service.StockSearchService;
import com.rasto.stockscreener.service.StockTimeSeriesService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Currency;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StockScreenerApplicationTests {

	@Autowired
	private StockSearchService stockSearchService;

	@Autowired
	private StockTimeSeriesService stockTimeSeriesService;

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

	@Test
	public void testMonthlyTimeSeries() {
		List<StockDataDTO> stockDataDTOS = stockTimeSeriesService.getMonthly("AAPL");
		Assert.assertTrue(stockDataDTOS.size() > 150);
		StockDataDTO stockDataDTO = stockDataDTOS.stream().filter(stockData -> stockData.getDateTime().equals(LocalDate.parse("2018-11-30").atStartOfDay())).findFirst().get();
		Assert.assertEquals(stockDataDTO.getClose(), Double.valueOf(178.58));
		Assert.assertEquals(stockDataDTO.getOpen(), Double.valueOf(219.05));
		Assert.assertEquals(stockDataDTO.getLow(), Double.valueOf(170.26));
		Assert.assertEquals(stockDataDTO.getHigh(), Double.valueOf(222.36));
		Assert.assertEquals(stockDataDTO.getVolume(), Long.valueOf(961321947));
	}

	@Test
	public void testWeeklyTimeSeries() {
		List<StockDataDTO> stockDataDTOS = stockTimeSeriesService.getWeekly("AAPL");
		Assert.assertTrue(stockDataDTOS.size() > 150);
		StockDataDTO stockDataDTO = stockDataDTOS.stream().filter(stockData -> stockData.getDateTime().equals(LocalDate.parse("2019-01-11").atStartOfDay())).findFirst().get();
		Assert.assertEquals(stockDataDTO.getClose(), Double.valueOf(152.2900));
		Assert.assertEquals(stockDataDTO.getOpen(), Double.valueOf(148.7));
		Assert.assertEquals(stockDataDTO.getLow(), Double.valueOf(145.9000));
		Assert.assertEquals(stockDataDTO.getHigh(), Double.valueOf(154.5300));
		Assert.assertEquals(stockDataDTO.getVolume(), Long.valueOf(203706070));
	}

	@Test
	public void testDailyTimeSeries() {
		List<StockDataDTO> stockDataDTOS = stockTimeSeriesService.getDaily("AAPL");
		Assert.assertTrue(stockDataDTOS.size() >= 100);
		StockDataDTO stockDataDTO = stockDataDTOS.stream().filter(stockData -> stockData.getDateTime().equals(LocalDate.parse("2018-09-12").atStartOfDay())).findFirst().get();
		Assert.assertEquals(stockDataDTO.getClose(), Double.valueOf(221.0700));
		Assert.assertEquals(stockDataDTO.getOpen(), Double.valueOf(224.9400));
		Assert.assertEquals(stockDataDTO.getLow(), Double.valueOf(219.8400));
		Assert.assertEquals(stockDataDTO.getHigh(), Double.valueOf(225.0000));
		Assert.assertEquals(stockDataDTO.getVolume(), Long.valueOf(49278740));
	}
}

