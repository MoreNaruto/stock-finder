package com.review.stockfinder.controller;

import com.review.stockfinder.models.Company;
import com.review.stockfinder.service.StockFinderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "stocks")
public class StockFinderController {
    @Autowired
    StockFinderService stockFinderService;

    @GetMapping(value = "/find/{input}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Company> getCompanies(@PathVariable String input) {
       return stockFinderService.getAllCompaniesWithPrefix(input);
    }
}
