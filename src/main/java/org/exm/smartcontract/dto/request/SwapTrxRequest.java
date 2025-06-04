package org.exm.smartcontract.dto.request;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class SwapTrxRequest {

    private String fromAccount; // opsional & belum digunakan
    private String toAccount;
    private BigDecimal amount;

}
