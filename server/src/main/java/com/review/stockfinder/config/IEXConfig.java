package com.review.stockfinder.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.zankowski.iextrading4j.client.IEXCloudClient;
import pl.zankowski.iextrading4j.client.IEXCloudTokenBuilder;
import pl.zankowski.iextrading4j.client.IEXTradingApiVersion;
import pl.zankowski.iextrading4j.client.IEXTradingClient;

@Configuration
public class IEXConfig {
    @Bean
    public IEXCloudClient iexCloudClient(IEXProperties properties) {
        return IEXTradingClient.create(IEXTradingApiVersion.IEX_CLOUD_V1,
                new IEXCloudTokenBuilder()
                        .withPublishableToken(properties.getPublishable())
                        .withSecretToken(properties.getSecret())
                        .build());
    }
}
