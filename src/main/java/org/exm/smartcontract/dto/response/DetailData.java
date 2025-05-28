package org.exm.smartcontract.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DetailData {
    private String txHash;
    private String blockHash;
    private String blockNumber;
    private String accountFrom;
    private String accountTo;
    private String status;
    private BigDecimal idrRate;
    private BigDecimal ethRate;
}
