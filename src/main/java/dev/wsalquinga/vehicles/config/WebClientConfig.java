package dev.wsalquinga.vehicles.config;

import dev.wsalquinga.vehicles.common.GlobalConstant;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author wsalquinga on 31/10/2023
 */
@Configuration
public class WebClientConfig {
    @Bean
    public WebClient webClient() {
        return WebClient.builder().baseUrl(GlobalConstant.API_PRICE_VEHICLES).build();
    }
}
