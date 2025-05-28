package org.exm.smartcontract.dto.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class DetailWithdrawResponse {

    private String id;
    private String nik;
    private String name;
    private BigDecimal ethBalance;
    private BigDecimal idrBalance;
    private String fromAccount;
    private String toAccount;
}
