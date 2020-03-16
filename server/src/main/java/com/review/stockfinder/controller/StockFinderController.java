package com.review.stockfinder.controller;

import com.review.stockfinder.models.Company;
import com.review.stockfinder.models.HistoricalPrice;
import com.review.stockfinder.service.PriceHistoryService;
import com.review.stockfinder.service.StockFinderService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.zankowski.iextrading4j.api.stocks.Chart;

import java.util.List;

@RestController
@RequestMapping(path = "stocks")
public class StockFinderController {
    private final StockFinderService stockFinderService;
    private final PriceHistoryService priceHistoryService;

    public StockFinderController(StockFinderService stockFinderService, PriceHistoryService priceHistoryService) {
        this.stockFinderService = stockFinderService;
        this.priceHistoryService = priceHistoryService;
    }

    @GetMapping(value = "/find/{input}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Company> getCompanies(@PathVariable String input) {
        return stockFinderService.getAllCompaniesWithPrefix(input);
    }

    @GetMapping(value = "/price/history/{symbol}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<HistoricalPrice> getPriceHistory(@PathVariable String symbol) {
        return priceHistoryService.retrieveHistoricalPriceForStock(symbol);
    }
}
