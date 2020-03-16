package com.review.stockfinder.service;

import com.review.stockfinder.mapper.ChartMapper;
import com.review.stockfinder.models.HistoricalPrice;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.zankowski.iextrading4j.api.stocks.Chart;
import pl.zankowski.iextrading4j.api.stocks.ChartRange;
import pl.zankowski.iextrading4j.client.IEXCloudClient;
import pl.zankowski.iextrading4j.client.rest.request.stocks.ChartRequestBuilder;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class PriceHistoryService {
    private IEXCloudClient client;

    public List<HistoricalPrice> retrieveHistoricalPriceForStock(String symbol) {
        List<Chart> charts = client.executeRequest(new ChartRequestBuilder()
                .withSymbol(symbol)
                .withChartRange(ChartRange.ONE_MONTH)
                .withDate(LocalDate.now())
                .build());
        return ChartMapper.convertChartsToHistoricalPrice(charts);
    }
}
