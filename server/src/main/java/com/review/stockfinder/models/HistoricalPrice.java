package com.review.stockfinder.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HistoricalPrice {
    private Double high;
    private Double low;
    private Double open;
    private Double close;
    private Double average;
    private String date;
}
