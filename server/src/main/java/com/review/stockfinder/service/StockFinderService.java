package com.review.stockfinder.service;

import com.review.stockfinder.models.Company;
import com.review.stockfinder.repository.StockFinderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockFinderService {
    private StockFinderRepository repository;

    public StockFinderService(StockFinderRepository repository) {
        this.repository = repository;
    }

    public List<Company> getAllCompaniesWithPrefix(String prefix) {
        return repository.getCompaniesByPrefixName(prefix);
    }
}
