package com.review.stockfinder.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "iex.token")
@Data
public class IEXProperties {
    private String publishable;
    private String secret;
}
