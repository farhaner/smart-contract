package org.exm.smartcontract.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SmartContractResponse<T> {
    private String statusCode;
    private Boolean status;
    private String message;
    private T data;
}
