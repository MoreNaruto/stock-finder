package com.review.stockfinder.service;

import com.review.stockfinder.mapper.FileMapper;
import com.review.stockfinder.models.Company;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StockFinderService {
    public List<Company> getAllCompaniesWithPrefix(String prefix) {
        List<Company> companies = new ArrayList<>(
                FileMapper.convertFileToListOfCompanies(
                        List.of("static/amex.csv", "static/nasdaq.csv", "static/nyse.csv")
                )
        );

        return companies.stream()
                .filter(company -> company.getName().toUpperCase().startsWith(prefix.toUpperCase()))
                .sorted(Comparator.comparing(Company::getName))
                .collect(Collectors.toList());
    }
}
