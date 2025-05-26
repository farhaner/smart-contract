package org.exm.smartcontract;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.web3j.crypto.Credentials;
import org.web3j.model.SmartContractV1;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.StaticGasProvider;

import java.io.File;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@SpringBootApplication
@Slf4j
public class SmartContractApplication {


//    @Value("${GANACHE.URL}")
//    private static String urlGanache;
//
//    @Value("${PRIVATE.KEY}")
//    private static String privateKey;
//
//    @Value("${GAS.PRICE}")
//    private static Long gasPrice;
//
//    @Value("${GAS.LIMIT}")
//    private static Long gasLimit;

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SmartContractApplication.class, args);
//        ObjectMapper objectMapper = new ObjectMapper();
//
//        try {
//
//            Web3j web3j = Web3j.build(new HttpService(urlGanache)); // Ganache di localhost
//            Credentials credentials = Credentials.create("0x172c33318a78a9a8b0c1c570398449fa8394b98a4024801552247d631c426a37"); // private key account ganache
//            ContractGasProvider gasProvider = new StaticGasProvider(
//                    BigInteger.valueOf(20_000_000_000L), // gasPrice 20 Gwei
//                    BigInteger.valueOf(4_300_000)        // gasLimit
//            );
//            String contractAddress = "0xD0a01Fa89db2aEDBb5aF1c28072F732375Aacf67";
//            SmartContractV1 contract = SmartContractV1.load(contractAddress, web3j, credentials, gasProvider);
//
//            // Contoh create
//            String requestId = String.valueOf(UUID.randomUUID());
//            log.info("requestId: {}", requestId);
//            String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//
//            TransactionReceipt createtrx = contract.createItem(
//                            requestId,
//                            now,
//                            "chatBot_id",
//                            "farhan",
//                            "3212728371")
//                    .send();
//            log.info("Event: {}", createtrx);
//        } catch (Exception e) {
//            e.printStackTrace();
//            log.error("Error {}\n", objectMapper.writeValueAsString(e.getMessage()));
//        }

        // Contoh read id=1
//        RemoteFunctionCall<List> allRequestIds = contract.getAllRequestIds();
//        String name = contract.getAllRequestIds().send().toString();
//        System.out.println("Name with id=1: " + allRequestIds);
    }
}
