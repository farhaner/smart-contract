package org.exm.smartcontract;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.web3j.crypto.Credentials;
import org.web3j.model.v1.CrudContract;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.StaticGasProvider;

import java.math.BigInteger;

@SpringBootApplication
public class SmartContractApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SmartContractApplication.class, args);

        Web3j web3j = Web3j.build(new HttpService("http://localhost:7545")); // Ganache di localhost
        Credentials credentials = Credentials.create("0x172c33318a78a9a8b0c1c570398449fa8394b98a4024801552247d631c426a37"); // private key account ganache
        ContractGasProvider gasProvider = new StaticGasProvider(
                BigInteger.valueOf(20_000_000_000L), // gasPrice 20 Gwei
                BigInteger.valueOf(4_300_000)        // gasLimit
        );
        String contractAddress = "0x543B38E54ECF51eff142E05c6801676e6F93Ea76";
        CrudContract contract = CrudContract.load(contractAddress, web3j, credentials, gasProvider);

        // Contoh create
        contract.create("Alice").send();

        // Contoh read id=1
        String name = contract.read(BigInteger.ONE).send();
        System.out.println("Name with id=1: " + name);
    }
}
