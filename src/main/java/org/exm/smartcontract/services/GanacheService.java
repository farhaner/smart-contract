package org.exm.smartcontract.services;


import org.exm.smartcontract.dto.request.SmartContractRequest;
import org.exm.smartcontract.dto.response.SmartContractResponse;
import org.springframework.http.ResponseEntity;

public interface GanacheService {

    ResponseEntity<SmartContractResponse> crud(SmartContractRequest request) throws Exception;

}
