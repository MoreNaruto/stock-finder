package com.review.stockfinder.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Company {
    private String symbol;
    private String name;
    private String lastSale;
    private String marketCap;
    private String ipoYear;
    private String sector;
    private String industry;
    private String summaryQuote;
}
