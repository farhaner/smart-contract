package org.exm.smartcontract.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SmartContractRequest {

    private String requestId;
    private String submissionDate;
    private String channelId;
    private String name;
    private String nik;

}
