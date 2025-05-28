package org.exm.smartcontract.dto.request;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class swapTrxRequest {
    private String fromAccount;
    private String toAccount;
    private BigDecimal amount;
}
