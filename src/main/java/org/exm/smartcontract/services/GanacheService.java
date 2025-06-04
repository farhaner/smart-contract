package org.exm.smartcontract.services;


import org.exm.smartcontract.dto.request.SmartContractRequest;
import org.exm.smartcontract.dto.request.SwapTrxRequest;
import org.exm.smartcontract.dto.request.WithdrawTrxRequest;
import org.exm.smartcontract.dto.response.SmartContractResponse;
import org.springframework.http.ResponseEntity;

public interface GanacheService {

    ResponseEntity<SmartContractResponse> create(SmartContractRequest request) throws Exception;

    ResponseEntity<SmartContractResponse> update(SmartContractRequest request) throws Exception;

    ResponseEntity<SmartContractResponse> swap(SwapTrxRequest request) throws Exception;

    ResponseEntity<SmartContractResponse> withdraw(WithdrawTrxRequest request) throws Exception;
}
