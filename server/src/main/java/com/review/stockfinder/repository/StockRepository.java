package com.review.stockfinder.repository;

import com.review.stockfinder.models.Company;

import java.util.List;

public interface StockRepository {
    List<Company> getCompaniesByPrefixName(String prefixName);
}
