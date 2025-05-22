package org.exm.smartcontract.dto.response;

import lombok.Data;

@Data
public class SmartContractResponse {
    private String statusCode;
    private String status;
    private String message;
    private Object data;
}
