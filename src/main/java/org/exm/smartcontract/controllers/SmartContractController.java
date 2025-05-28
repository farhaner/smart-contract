package org.exm.smartcontract.controllers;

import lombok.RequiredArgsConstructor;
import org.exm.smartcontract.dto.request.SmartContractRequest;
import org.exm.smartcontract.dto.request.swapTrxRequest;
import org.exm.smartcontract.dto.response.SmartContractResponse;
import org.exm.smartcontract.services.GanacheService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/smart-contract/v1")
public class SmartContractController {

    private final GanacheService ganacheService;

    @PostMapping("/create")
    ResponseEntity<SmartContractResponse> createSmartContract(@RequestBody SmartContractRequest request) throws Exception {
        return ganacheService.create(request);
    }

    @PostMapping("/update")
    ResponseEntity<SmartContractResponse> updateSmartContract(@RequestBody SmartContractRequest request) throws Exception {
        return ganacheService.create(request);
    }

    @PostMapping("/transfer/swap")
    ResponseEntity<SmartContractResponse> swapTransaction(@RequestBody swapTrxRequest request) throws Exception {
        return ganacheService.swap(request);
    }

    @PostMapping("/transfer/withdraw")
    ResponseEntity<SmartContractResponse> withdrawTransaction(@RequestBody swapTrxRequest request) throws Exception {
        return ganacheService.withdraw(request);
    }

}
