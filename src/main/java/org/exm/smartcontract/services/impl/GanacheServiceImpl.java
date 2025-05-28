package org.exm.smartcontract.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.exm.smartcontract.dto.request.SmartContractRequest;
import org.exm.smartcontract.dto.request.swapTrxRequest;
import org.exm.smartcontract.dto.response.DetailData;
import org.exm.smartcontract.dto.response.SmartContractResponse;
import org.exm.smartcontract.models.NasabahSource;
import org.exm.smartcontract.repositories.NasabahRecipientRepository;
import org.exm.smartcontract.repositories.NasabahSourceRepository;
import org.exm.smartcontract.services.CoingeckoService;
import org.exm.smartcontract.services.GanacheService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.model.SmartContractV1;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Transfer;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.StaticGasProvider;
import org.web3j.utils.Convert;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class GanacheServiceImpl implements GanacheService {

    private final NasabahSourceRepository nasabahSourceRepository;
    @Value("${GANACHE.URL}")
    private String urlGanache;
    @Value("${PRIVATE.KEY}")
    private String privateKey;
    @Value("${CONTRACT.ADDRESS}")
    private String contractAddress;

    private final CoingeckoService coingeckoService;
    private final NasabahSourceRepository sourceRepository;
    private final NasabahRecipientRepository recipientRepository;


    @Override
    public ResponseEntity<SmartContractResponse> create(SmartContractRequest request) throws Exception {
        try {
            Web3j web3j = Web3j.build(new HttpService(urlGanache)); // Ganache di localhost
            Credentials credentials = Credentials.create(privateKey); // private key account ganache

            ContractGasProvider gasProvider = new StaticGasProvider(
                    BigInteger.valueOf(20_000_000_000L),
                    BigInteger.valueOf(4_300_000)
            );
            SmartContractV1 contract = SmartContractV1.load(contractAddress, web3j, credentials, gasProvider);

            // initial field create
            String requestId = String.valueOf(UUID.randomUUID());
            log.info("requestId: {}", requestId);
            String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            log.info("currentDate: {}", now);

            //Request
            TransactionReceipt createtrx = contract.createItem(
                            request.getRequestId(),
                            request.getSubmissionDate(),
                            request.getChannelId(),
                            request.getName(),
                            request.getNik())
                    .send();

            // Response
            DetailData detailData = DetailData.builder()
                    .txHash(createtrx.getTransactionHash())
                    .blockNumber(createtrx.getBlockNumberRaw())
                    .blockHash(createtrx.getBlockHash())
                    .status(createtrx.getStatus())
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
            log.error("Error :{}\n", e.getMessage());

            // Error Response
            SmartContractResponse<Object> response = SmartContractResponse.<Object>builder()
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

    @Override
    public ResponseEntity<SmartContractResponse> update(SmartContractRequest request) throws Exception {
        return null;
    }


    @Override
    public ResponseEntity<SmartContractResponse> swap(swapTrxRequest request) throws Exception {
        TransactionReceipt transfer = null;
        try {
            Web3j web3j = Web3j.build(new HttpService(urlGanache)); // Ganache di localhost
            Credentials credentials = Credentials.create(privateKey); // private key account ganache

            //Request
            transfer = Transfer.sendFunds(
                            web3j,
                            credentials,
                            request.getAccountTo(),
                            request.getAmount(),
                            Convert.Unit.ETHER)
                    .send();
            log.info("Transfer: {}", transfer);

            //Response
            DetailData detailData = DetailData.builder()
                    .status(transfer.getStatus()) // 0x0 = gagal, 0x1 = sukses
                    .txHash(transfer.getTransactionHash())
                    .blockNumber(transfer.getBlockNumberRaw())
                    .status(transfer.getStatus())
                    .accountFrom(transfer.getFrom())
                    .accountTo(transfer.getTo())
                    .build();
            SmartContractResponse<DetailData> response = SmartContractResponse.<DetailData>builder()
                    .statusCode("000")
                    .status(true)
                    .message("Success Transfer")
                    .data(detailData)
                    .build();
            log.info("Result Transaction: {}", response);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Error :{}\n", e.getMessage());

            DetailData detailData = DetailData.builder()
                    .status(transfer.getStatus()) // 0x0 = gagal, 0x1 = sukses
                    .txHash(transfer.getTransactionHash())
                    .blockNumber(transfer.getBlockNumberRaw())
                    .status(transfer.getStatus())
                    .accountFrom(transfer.getFrom())
                    .accountTo(transfer.getTo())
                    .build();
            SmartContractResponse<Object> response = SmartContractResponse.<Object>builder()
                    .statusCode("999")
                    .status(false)
                    .message("Failed Transfer")
                    .data(detailData)
                    .build();
            return ResponseEntity.status(500).body(response);
        } finally {
            log.info("Finished Process");
        }
    }

    @Override
    public ResponseEntity<SmartContractResponse> withdraw(swapTrxRequest request) throws Exception {
        try {
            //Request
            ResponseEntity<BigDecimal> rate = coingeckoService.convert();   // harga 1 eth ke IDR
            log.info("ETH to IDR rate : {}", rate.getBody());

            NasabahSource referenceById = sourceRepository.getReferenceById(request.getAccountTo());// ganti request.nik();
            referenceById.setEthBalance();
            recipientRepository.findByNik(request.getAccountTo()); // ganti request.nik();
//            Gunakan Fungsi
//            BigDecimal idrValue = calculateEthToIdr(request, rate);
//            BigDecimal ethValue = calculateIdrToEth(idrValue, rate);


            SmartContractResponse<Object> build = SmartContractResponse.builder().build();
            return ResponseEntity.ok(build);
        } catch (Exception e) {
            log.error("Error :{}\n", e.getMessage());
            SmartContractResponse<Object> Error = SmartContractResponse.builder().statusCode("999").build();
            return ResponseEntity.badRequest().body(Error);
        }

    }

    private static BigDecimal calculateIdrToEth(BigDecimal idrValue, ResponseEntity<BigDecimal> rate) {
        BigDecimal idrInput = idrValue; // input IDR
        BigDecimal ethValue = idrInput.divide(rate.getBody(), 2, RoundingMode.HALF_UP); // IDR → ETH
        log.info("IDR to ETH value: {}", ethValue);
        return ethValue;
    }

    @NotNull
    private static BigDecimal calculateEthToIdr(swapTrxRequest request, ResponseEntity<BigDecimal> rate) {
        BigDecimal ethAmount = request.getAmount(); // input ETH
        BigDecimal idrValue = rate.getBody().multiply(ethAmount).setScale(0, RoundingMode.HALF_UP); // ETH → IDR
        log.info("ETH to IDR value: {}", idrValue);
        return idrValue;
    }
}
