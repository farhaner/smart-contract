package org.exm.smartcontract.services;


import org.exm.smartcontract.dto.request.SmartContractRequest;
import org.exm.smartcontract.dto.request.swapTrxRequest;
import org.exm.smartcontract.dto.response.SmartContractResponse;
import org.springframework.http.ResponseEntity;

public interface GanacheService {

    ResponseEntity<SmartContractResponse> create(SmartContractRequest request) throws Exception;

    ResponseEntity<SmartContractResponse> update(SmartContractRequest request) throws Exception;

    ResponseEntity<SmartContractResponse> swap(swapTrxRequest request) throws Exception;

    ResponseEntity<SmartContractResponse> withdraw(swapTrxRequest request) throws Exception;
}
