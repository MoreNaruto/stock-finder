package com.review.stockfinder.mapper;

import com.review.stockfinder.models.HistoricalPrice;
import pl.zankowski.iextrading4j.api.stocks.Chart;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

public class ChartMapper {

    public static List<HistoricalPrice> convertChartsToHistoricalPrice(List<Chart> charts) {
        return charts.stream()
                .map(chart -> {
                    BigDecimal high = chart.getHigh();
                    BigDecimal low = chart.getLow();
                    BigDecimal open = chart.getOpen();
                    BigDecimal close = chart.getClose();
                    
                    return HistoricalPrice.builder()
                            .date(chart.getDate())
                            .high(high.doubleValue())
                            .low(low.doubleValue())
                            .open(open.doubleValue())
                            .close(close.doubleValue())
                            .average(high.add(low).add(open).add(close).divide(BigDecimal.valueOf(4L), RoundingMode.HALF_UP).doubleValue())
                            .build();
                })
                .collect(Collectors.toList());
    }
}
