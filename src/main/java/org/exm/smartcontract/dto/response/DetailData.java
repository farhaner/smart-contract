package org.exm.smartcontract.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DetailData {
    private String txHash;
    private String blockHash;
    private String blockNumber;
}
