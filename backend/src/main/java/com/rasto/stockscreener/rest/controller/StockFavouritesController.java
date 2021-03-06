package com.rasto.stockscreener.rest.controller;

import com.rasto.stockscreener.data.entity.User;
import com.rasto.stockscreener.dto.StockDTO;
import com.rasto.stockscreener.rest.response.StockFavouritesResponse;
import com.rasto.stockscreener.security.UserPrincipal;
import com.rasto.stockscreener.service.StockFavouritesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/me/favourites")
public class StockFavouritesController {

    @Autowired
    private StockFavouritesService stockFavouritesService;

    @GetMapping
    @ResponseBody
    public StockFavouritesResponse getFavourites(Authentication authentication) {
        List<StockDTO> stockDTOList = stockFavouritesService.findFavouriteStocksByUserId(getLoggedUserId(authentication));
        return new StockFavouritesResponse(stockDTOList);
    }

    @PostMapping
    @ResponseBody
    public StockFavouritesResponse addToFavourites(@RequestBody StockDTO stockDTO, Authentication authentication) {
        List<StockDTO> stockDTOList = stockFavouritesService.addStockToFavourites(stockDTO, getLoggedUserId(authentication));
        return new StockFavouritesResponse(stockDTOList);
    }

    @DeleteMapping(path = "/{symbol}")
    @ResponseBody
    public StockFavouritesResponse removeFromFavourites(@PathVariable("symbol") String symbol, Authentication authentication) {
        List<StockDTO> stockDTOList = stockFavouritesService.removeStockFromFavourites(symbol, getLoggedUserId(authentication));
        return new StockFavouritesResponse(stockDTOList);
    }

    private Integer getLoggedUserId(Authentication authentication) {
        return ((UserPrincipal) authentication.getPrincipal()).getUser().getId();
    }

}
