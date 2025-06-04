package org.exm.smartcontract.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.math.BigDecimal;

@Data
@Builder
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class WithdrawTrxRequest {

    private int fromAccount;
    private int toAccount;
    private String fromCurrency;
    private String toCurrency;
    private BigDecimal amount;

}
