package org.exm.smartcontract.services.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.exm.smartcontract.dto.request.SmartContractRequest;
import org.exm.smartcontract.dto.request.SwapTrxRequest;
import org.exm.smartcontract.dto.request.WithdrawTrxRequest;
import org.exm.smartcontract.dto.response.DetailData;
import org.exm.smartcontract.dto.response.SmartContractResponse;
import org.exm.smartcontract.models.Nasabah;
import org.exm.smartcontract.models.Transactions;
import org.exm.smartcontract.repositories.NasabahRepository;
import org.exm.smartcontract.repositories.TransactionsRepository;
import org.exm.smartcontract.services.GanacheService;
import org.exm.smartcontract.services.PriceConversionService;
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
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class GanacheServiceImpl implements GanacheService {

    @Value("${GANACHE.URL}")
    private String urlGanache;
    @Value("${PRIVATE.KEY}")
    private String privateKey;
    @Value("${CONTRACT.ADDRESS}")
    private String contractAddress;

    private final PriceConversionService priceConversionService;
    private final TransactionsRepository transactionsRepository;
    private final NasabahRepository nasabahRepository;


    @Override
    public ResponseEntity<SmartContractResponse> create(SmartContractRequest request) throws Exception {
        try {
            Web3j web3j = Web3j.build(new HttpService(urlGanache)); // Ganache di localhost
            Credentials credentials = Credentials.create(privateKey); // private key account ganache

            ContractGasProvider gasProvider = new StaticGasProvider(
                    BigInteger.valueOf(20_000_000_000L),
                    BigInteger.valueOf(4_300_000));

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

            SmartContractResponse<DetailData> response = SmartContractResponse.<DetailData>builder().statusCode("000").status(true).message("Success Create").data(detailData).build();
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
            log.info("Finished Process Transaction");
        }
    }

    @Override
    public ResponseEntity<SmartContractResponse> update(SmartContractRequest request) throws Exception {
        return null;
    }


    @Override
    public ResponseEntity<SmartContractResponse> swap(SwapTrxRequest request) throws Exception {
        TransactionReceipt transfer = null;
        try {
            Web3j web3j = Web3j.build(new HttpService(urlGanache)); // Ganache di localhost
            Credentials credentials = Credentials.create(privateKey); // private key account ganache

            //Request
            transfer = Transfer.sendFunds(web3j, credentials, request.getToAccount(), request.getAmount(), Convert.Unit.ETHER).send();
            log.info("Transfer: {}", transfer);

            //Response
            DetailData detailData = DetailData.builder()
                    .status(transfer.getStatus()) // 0x0 = gagal, 0x1 = sukses
                    .txHash(transfer.getTransactionHash())
                    .blockNumber(transfer.getBlockNumberRaw())
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

            DetailData detailData = DetailData.builder().status(transfer.getStatus()) // 0x0 = gagal, 0x1 = sukses
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
                    .data(detailData).build();
            return ResponseEntity.status(500).body(response);
        } finally {
            log.info("Finished Process Transaction");
        }
    }


    @Override
    @Transactional
    public ResponseEntity<SmartContractResponse> withdraw(WithdrawTrxRequest request) throws Exception {
        //Action:
        // Gunakan Fungsi
        // BigDecimal idrValue = calculateEthToIdr(request, rate);
        // BigDecimal ethValue = calculateIdrToEth(idrValue, rate);
        Optional<Transactions> dataTransaction = null;
        Timestamp now = Timestamp.valueOf(LocalDateTime.now());
        try {
            if (request.getFromAccount() == request.getToAccount()) {
                log.info("Transaction Invalid");
                throw new Exception("Transaction Invalid");
            }

            Nasabah sourceNasabah = nasabahRepository.findByAccount(request.getFromAccount());
            Nasabah recepientNasabah = nasabahRepository.findByAccount(request.getToAccount());

            if (recepientNasabah != null && sourceNasabah != null) {
                log.info("Data Nasabah Recepient: {}", recepientNasabah);

                //Request Inquiry Rate ETH To IDR
                ResponseEntity<BigDecimal> rate = priceConversionService.convert();
                log.info("ETH to IDR rate : {}", rate.getBody());

                if (request.getFromCurrency().equals("ETH") && request.getToCurrency().equals("IDR")) {
                    if (sourceNasabah.getEthBalance() != null && sourceNasabah.getEthBalance().compareTo(BigDecimal.ZERO) > 0) {

                        BigDecimal idrValue = calculateEthToIdr(request.getAmount(), rate);
                        log.info("ETH to IDR rate : {}", idrValue);

                        BigDecimal currentIdrValue = idrValue.add(recepientNasabah.getIdrBalance());
                        log.info("Current IDR value : {}", currentIdrValue);

                        nasabahRepository.updateIdrBalancesByAccount(request.getToAccount(), currentIdrValue); // dana masuk

                        BigDecimal currentEthValue = sourceNasabah.getEthBalance().subtract(request.getAmount());
                        log.info("Current ETH value : {}", currentEthValue);

                        nasabahRepository.updateEthBalancesByAccount(request.getFromAccount(), currentEthValue); // dana keluar

                        Transactions transactions = new Transactions();
                        transactions.setId(UUID.randomUUID().toString());
                        transactions.setAccountSource(request.getFromAccount());
                        transactions.setAccountRecepient(request.getToAccount());
                        transactions.setAmount(request.getAmount());
                        transactions.setStatus("0x0");
                        transactions.setCreatedAt(now);
                        transactions.setUpdatedAt(now);

                        Transactions savedTransaction = transactionsRepository.save(transactions);
                        dataTransaction = transactionsRepository.findById(savedTransaction.getId());
                        log.info("Data Transaction: {}", dataTransaction);
                    } else {
                        log.info("insufficient funds");
                        throw new Exception("insufficient funds");
                    }
                } else if (request.getFromCurrency().equals("IDR") && request.getToCurrency().equals("ETH")) {
                    if (sourceNasabah.getIdrBalance() != null && sourceNasabah.getIdrBalance().compareTo(BigDecimal.ZERO) > 0) {

                        BigDecimal ethValue = calculateIdrToEth(request.getAmount(), rate);
                        log.info("IDR to ETH rate : {}", ethValue);

                        BigDecimal currentEthValue = ethValue.add(recepientNasabah.getEthBalance());
                        log.info("Current ETH value : {}", currentEthValue);

                        nasabahRepository.updateEthBalancesByAccount(request.getToAccount(), currentEthValue); // dana masuk

                        BigDecimal currentIdrValue = sourceNasabah.getIdrBalance().subtract(request.getAmount());
                        log.info("Current IDR value : {}", currentIdrValue);

                        nasabahRepository.updateIdrBalancesByAccount(request.getFromAccount(), currentIdrValue); // dana keluar

                        Transactions transactions = new Transactions();
                        transactions.setId(UUID.randomUUID().toString());
                        transactions.setAccountSource(request.getFromAccount());
                        transactions.setAccountRecepient(request.getToAccount());
                        transactions.setAmount(request.getAmount());
                        transactions.setStatus("0x0");
                        transactions.setCreatedAt(now);
                        transactions.setUpdatedAt(now);

                        Transactions savedTransaction = transactionsRepository.save(transactions);
                        dataTransaction = transactionsRepository.findById(savedTransaction.getId());
                        log.info("Data Transaction: {}", dataTransaction);
                    } else {
                        log.info("insufficient funds");
                        throw new Exception("insufficient funds");
                    }
                } else if (request.getFromCurrency().equals("IDR") && request.getToCurrency().equals("IDR")) {
                    if (sourceNasabah.getIdrBalance() != null && sourceNasabah.getIdrBalance().compareTo(BigDecimal.ZERO) > 0) {

                        BigDecimal currentSourceValue = sourceNasabah.getIdrBalance().subtract(request.getAmount());
                        log.info("Current IDR value : {}", currentSourceValue);

                        nasabahRepository.updateIdrBalancesByAccount(request.getFromAccount(), currentSourceValue); // dana masuk

                        BigDecimal currentRecepientValue = recepientNasabah.getIdrBalance().add(request.getAmount());
                        log.info("Current IDR value : {}", currentRecepientValue);

                        nasabahRepository.updateIdrBalancesByAccount(request.getToAccount(), currentRecepientValue); // dana keluar

                        Transactions transactions = new Transactions();
                        transactions.setId(UUID.randomUUID().toString());
                        transactions.setAccountSource(request.getFromAccount());
                        transactions.setAccountRecepient(request.getToAccount());
                        transactions.setAmount(request.getAmount());
                        transactions.setStatus("0x0");
                        transactions.setCreatedAt(now);
                        transactions.setUpdatedAt(now);

                        Transactions savedTransaction = transactionsRepository.save(transactions);
                        dataTransaction = transactionsRepository.findById(savedTransaction.getId());
                        log.info("Data Transaction: {}", dataTransaction);
                    } else {
                        log.info("insufficient funds");
                        throw new Exception("insufficient funds");
                    }
                } else {
                    log.info("Unconverted Service Not Available");
                    throw new Exception("Unconverted Service Not Available");
                }
            } else {
                log.info("Nasabah Not Found");
                throw new Exception("Nasabah Not Found");
            }
            SmartContractResponse<Object> response = SmartContractResponse.builder()
                    .statusCode("000")
                    .status(true)
                    .message("Success Transfer")
                    .data(dataTransaction)
                    .build();
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Error :{}\n", e.getMessage());

            Transactions transactions = new Transactions();
            transactions.setId(UUID.randomUUID().toString());
            transactions.setAccountSource(request.getFromAccount());
            transactions.setAccountRecepient(request.getToAccount());
            transactions.setAmount(request.getAmount());
            transactions.setStatus("0x1");
            transactions.setCreatedAt(now);
            transactions.setUpdatedAt(now);

            Transactions savedTransaction = transactionsRepository.save(transactions);
            dataTransaction = transactionsRepository.findById(savedTransaction.getId());

            log.info("Data Transaction: {}", dataTransaction);
            SmartContractResponse<Object> Error = SmartContractResponse.builder()
                    .statusCode("999")
                    .status(false)
                    .message(e.getMessage())
                    .data(dataTransaction)
                    .build();
            return ResponseEntity.internalServerError().body(Error);
        }
    }

    private static BigDecimal calculateIdrToEth(BigDecimal idrValue, ResponseEntity<BigDecimal> rate) {
        BigDecimal idrInput = idrValue; // input IDR
        BigDecimal ethValue = idrInput.divide(rate.getBody(), 2, RoundingMode.HALF_UP); // IDR → ETH
        log.info("IDR to ETH value: {}", ethValue);
        return ethValue;
    }

    @NotNull
    private static BigDecimal calculateEthToIdr(BigDecimal request, ResponseEntity<BigDecimal> rate) {
        BigDecimal ethAmount = request; // input ETH
        BigDecimal idrValue = rate.getBody().multiply(ethAmount).setScale(0, RoundingMode.HALF_UP); // ETH → IDR
        log.info("ETH to IDR value: {}", idrValue);
        return idrValue;
    }
}
