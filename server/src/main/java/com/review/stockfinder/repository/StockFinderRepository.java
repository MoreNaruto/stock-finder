package com.review.stockfinder.repository;

import com.review.stockfinder.models.Company;
import lombok.Builder;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Builder
public class StockFinderRepository implements StockRepository {
    private List<Company> companies;

    @Override
    public List<Company> getCompaniesByPrefixName(String prefix) {
        String upperCasePrefix = prefix.toUpperCase();

        return companies.stream()
                .filter(company -> company.getName().toUpperCase().startsWith(upperCasePrefix))
                .sorted(Comparator.comparing(Company::getName))
                .collect(Collectors.toList());
    }
}
