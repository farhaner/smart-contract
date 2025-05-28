package org.exm.smartcontract.services.impl;

import lombok.extern.log4j.Log4j2;
import org.exm.smartcontract.services.CoingeckoService;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;
import java.util.Map;

@Service
@Log4j2
public class CoingeckoImpl implements CoingeckoService {

    @Override
    public ResponseEntity<BigDecimal> convert() throws Exception {
        WebClient webClient = WebClient.builder().baseUrl("https://api.coingecko.com").build();

        Map<String, Map<String, BigDecimal>> responseCoinGecko = webClient.get()
                .uri("/api/v3/simple/price?ids=ethereum&vs_currencies=idr")
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Map<String, Map<String, BigDecimal>>>() {
                })
                .block();

        BigDecimal ethPriceInIdr = responseCoinGecko.get("ethereum").get("idr");
        return ResponseEntity.ok(ethPriceInIdr);
    }

}
