package org.exm.smartcontract.services;

import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

public interface PriceConversionService {
    ResponseEntity<BigDecimal> convert() throws Exception;

}
