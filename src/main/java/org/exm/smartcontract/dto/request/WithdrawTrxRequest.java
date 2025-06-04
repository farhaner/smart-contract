package org.exm.smartcontract.dto.request;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class WithdrawTrxRequest {

    private int fromAccount;
    private int toAccount;
    private String fromCurrency;
    private String toCurrency;
    private BigDecimal amount;

}
