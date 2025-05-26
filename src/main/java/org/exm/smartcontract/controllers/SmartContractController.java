package org.exm.smartcontract.controllers;

import lombok.RequiredArgsConstructor;
import org.exm.smartcontract.dto.request.SmartContractRequest;
import org.exm.smartcontract.dto.response.SmartContractResponse;
import org.exm.smartcontract.services.GanacheService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/set")
public class SmartContractController {

    private final GanacheService ganacheService;

    @PostMapping("/contract")
    ResponseEntity<SmartContractResponse> createSmartContract(@RequestBody SmartContractRequest request) throws Exception {
        return ganacheService.crud(request);
    }
}
