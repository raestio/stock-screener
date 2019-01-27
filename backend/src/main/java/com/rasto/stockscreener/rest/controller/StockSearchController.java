package com.rasto.stockscreener.rest.controller;

import com.rasto.stockscreener.rest.response.StockSearchResponse;
import com.rasto.stockscreener.dto.StockSearchDTO;
import com.rasto.stockscreener.service.StockSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/stocks/search")
public class StockSearchController {

    @Autowired
    private StockSearchService stockSearchService;

    @GetMapping
    @ResponseBody
    public StockSearchResponse searchByKeyword(@RequestParam("keyword") String keyword) {
        List<StockSearchDTO> searchDTOList = stockSearchService.searchStockByKeyword(keyword);
        return new StockSearchResponse(searchDTOList);
    }

}
