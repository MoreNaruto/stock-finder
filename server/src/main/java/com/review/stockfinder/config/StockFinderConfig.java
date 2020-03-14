package com.review.stockfinder.config;

import com.review.stockfinder.repository.StockFinderRepository;
import com.review.stockfinder.mapper.FileMapper;
import com.review.stockfinder.models.Company;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class StockFinderConfig {

    public static final String AMEX_CSV_PATH = "static/amex.csv";
    public static final String NASDAQ_CSV_PATH = "static/nasdaq.csv";
    public static final String NYSE_CSV_PATH = "static/nyse.csv";

    @Bean
    public StockFinderRepository stockFinderRepository() {
        ArrayList<Company> allCompanies = new ArrayList<>(
                FileMapper.convertFileToListOfCompanies(
                        List.of(AMEX_CSV_PATH, NASDAQ_CSV_PATH, NYSE_CSV_PATH)
                )
        );

        return StockFinderRepository.builder()
                .companies(allCompanies)
                .build();
    }
}
