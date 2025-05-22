package org.exm.smartcontract.services.impl;

import org.exm.smartcontract.dto.request.SmartContractRequest;
import org.exm.smartcontract.dto.response.SmartContractResponse;
import org.exm.smartcontract.services.GanacheService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class GanacheServiceImpl implements GanacheService {

    @Override
    public ResponseEntity<SmartContractResponse> crud(SmartContractRequest smartContractReq) {
        return null;
    }
}
