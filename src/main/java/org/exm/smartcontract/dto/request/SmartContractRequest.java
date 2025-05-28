package org.exm.smartcontract.dto.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SmartContractRequest {

    private String requestId;
    private String submissionDate;
    private String channelId;
    private String name;
    private String nik;
}
