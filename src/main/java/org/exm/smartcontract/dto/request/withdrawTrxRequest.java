package org.exm.smartcontract.dto.request;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class withdrawTrxRequest {
    private String id;
    private String nik;
    private String fromAccount;
    private String toAccount;
    private BigDecimal amount;
    private String currency;

}
