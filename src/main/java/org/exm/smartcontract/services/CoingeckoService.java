package org.exm.smartcontract.services;

import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

public interface CoingeckoService {
    ResponseEntity<BigDecimal> convert() throws Exception;

}
