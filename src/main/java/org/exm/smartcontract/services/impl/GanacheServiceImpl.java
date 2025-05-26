package org.exm.smartcontract.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.exm.smartcontract.dto.request.SmartContractRequest;
import org.exm.smartcontract.dto.response.DetailData;
import org.exm.smartcontract.dto.response.SmartContractResponse;
import org.exm.smartcontract.services.GanacheService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.model.SmartContractV1;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.StaticGasProvider;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
@Slf4j
public class GanacheServiceImpl implements GanacheService {

    @Value("${GANACHE.URL}")
    private String urlGanache;
//    @Value("${GAS.PRICE}")
//    private Long gasPrice;
//    @Value("${GAS.LIMIT}")
//    private Long gasLimit;
//    @Value("${GANACHE.URL}")
//    private String urlGanache;

    @Override
    public ResponseEntity<SmartContractResponse> crud(SmartContractRequest request) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Web3j web3j = Web3j.build(new HttpService(urlGanache)); // Ganache di localhost
            Credentials credentials = Credentials.create("0x172c33318a78a9a8b0c1c570398449fa8394b98a4024801552247d631c426a37"); // private key account ganache
            ContractGasProvider gasProvider = new StaticGasProvider(
                    BigInteger.valueOf(20_000_000_000L),
                    BigInteger.valueOf(4_300_000)
            );
            String contractAddress = "0xD0a01Fa89db2aEDBb5aF1c28072F732375Aacf67";
            SmartContractV1 contract = SmartContractV1.load(contractAddress, web3j, credentials, gasProvider);

            // Contoh create
            String requestId = String.valueOf(UUID.randomUUID());
            log.info("requestId: {}", requestId);
            String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            log.info("currentDate: {}", now);

            TransactionReceipt createtrx = contract.createItem(
                            request.getRequestId(),
                            request.getSubmissionDate(),
                            request.getChannelId(),
                            request.getNama(),
                            request.getNik())
                    .send();

            DetailData detailData = DetailData.builder()
                    .txHash(createtrx.getTransactionHash())
                    .blockNumber(createtrx.getBlockNumberRaw())
                    .blockHash(createtrx.getBlockHash())
                    .build();
            SmartContractResponse<DetailData> response = SmartContractResponse.<DetailData>builder()
                    .statusCode("000")
                    .status(true)
                    .message("Success Create")
                    .data(detailData)
                    .build();
            log.info("Event: {}", createtrx);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error :{}\n", e.getMessage());

            SmartContractResponse<String> response = SmartContractResponse.<String>builder()
                    .statusCode("999")
                    .status(false)
                    .message("Failed Create")
                    .data(null)
                    .build();
            return ResponseEntity.status(500).body(response);
        } finally {
            log.info("Finished Process");
        }
    }
}
