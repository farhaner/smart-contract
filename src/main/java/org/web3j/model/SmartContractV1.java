package org.web3j.model;

import io.reactivex.Flowable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 4.10.3.
 */
@SuppressWarnings("rawtypes")
public class SmartContractV1 extends Contract {
    public static final String BINARY = "0x608060405234801561001057600080fd5b5061091d806100206000396000f3fe608060405234801561001057600080fd5b50600436106100415760003560e01c80638dd752d914610046578063ddc4b2d114610062578063ec1e6bd61461007e575b600080fd5b610060600480360381019061005b9190610468565b61009c565b005b61007c60048036038101906100779190610468565b610129565b005b610086610284565b604051610093919061069a565b60405180910390f35b600080866040516100ad9190610683565b90815260200160405180910390209050848160010190805190602001906100d592919061035d565b50838160020190805190602001906100ee92919061035d565b508281600301908051906020019061010792919061035d565b508181600401908051906020019061012092919061035d565b50505050505050565b6040518060a00160405280868152602001858152602001848152602001838152602001828152506000866040516101609190610683565b9081526020016040518091039020600082015181600001908051906020019061018a92919061035d565b5060208201518160010190805190602001906101a792919061035d565b5060408201518160020190805190602001906101c492919061035d565b5060608201518160030190805190602001906101e192919061035d565b5060808201518160040190805190602001906101fe92919061035d565b5090505060018590806001815401808255809150506001900390600052602060002001600090919091909150908051906020019061023d92919061035d565b507f1439eaaf09ede6c9a05feeeb72bd6067e763429ac4bbfd6a81569087b655e53085858585856040516102759594939291906106bc565b60405180910390a15050505050565b60606001805480602002602001604051908101604052809291908181526020016000905b828210156103545783829060005260206000200180546102c790610846565b80601f01602080910402602001604051908101604052809291908181526020018280546102f390610846565b80156103405780601f1061031557610100808354040283529160200191610340565b820191906000526020600020905b81548152906001019060200180831161032357829003601f168201915b5050505050815260200190600101906102a8565b50505050905090565b82805461036990610846565b90600052602060002090601f01602090048101928261038b57600085556103d2565b82601f106103a457805160ff19168380011785556103d2565b828001600101855582156103d2579182015b828111156103d15782518255916020019190600101906103b6565b5b5090506103df91906103e3565b5090565b5b808211156103fc5760008160009055506001016103e4565b5090565b600061041361040e84610763565b610732565b90508281526020810184848401111561042b57600080fd5b610436848285610804565b509392505050565b600082601f83011261044f57600080fd5b813561045f848260208601610400565b91505092915050565b600080600080600060a0868803121561048057600080fd5b600086013567ffffffffffffffff81111561049a57600080fd5b6104a68882890161043e565b955050602086013567ffffffffffffffff8111156104c357600080fd5b6104cf8882890161043e565b945050604086013567ffffffffffffffff8111156104ec57600080fd5b6104f88882890161043e565b935050606086013567ffffffffffffffff81111561051557600080fd5b6105218882890161043e565b925050608086013567ffffffffffffffff81111561053e57600080fd5b61054a8882890161043e565b9150509295509295909350565b600061056383836105e0565b905092915050565b6000610576826107a3565b61058081856107c6565b93508360208202850161059285610793565b8060005b858110156105ce57848403895281516105af8582610557565b94506105ba836107b9565b925060208a01995050600181019050610596565b50829750879550505050505092915050565b60006105eb826107ae565b6105f581856107d7565b9350610605818560208601610813565b61060e816108d6565b840191505092915050565b6000610624826107ae565b61062e81856107e8565b935061063e818560208601610813565b610647816108d6565b840191505092915050565b600061065d826107ae565b61066781856107f9565b9350610677818560208601610813565b80840191505092915050565b600061068f8284610652565b915081905092915050565b600060208201905081810360008301526106b4818461056b565b905092915050565b600060a08201905081810360008301526106d68188610619565b905081810360208301526106ea8187610619565b905081810360408301526106fe8186610619565b905081810360608301526107128185610619565b905081810360808301526107268184610619565b90509695505050505050565b6000604051905081810181811067ffffffffffffffff82111715610759576107586108a7565b5b8060405250919050565b600067ffffffffffffffff82111561077e5761077d6108a7565b5b601f19601f8301169050602081019050919050565b6000819050602082019050919050565b600081519050919050565b600081519050919050565b6000602082019050919050565b600082825260208201905092915050565b600082825260208201905092915050565b600082825260208201905092915050565b600081905092915050565b82818337600083830152505050565b60005b83811015610831578082015181840152602081019050610816565b83811115610840576000848401525b50505050565b6000600282049050600182168061085e57607f821691505b6020821081141561087257610871610878565b5b50919050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052602260045260246000fd5b7f4e487b7100000000000000000000000000000000000000000000000000000000600052604160045260246000fd5b6000601f19601f830116905091905056fea26469706673582212209b306451ceac77ae16d77a042da88b5ec6fe21837b077d554edff138396a435464736f6c63430008000033";

    public static final String FUNC_CREATEITEM = "createItem";

    public static final String FUNC_UPDATEITEM = "updateItem";

    public static final String FUNC_GETALLREQUESTIDS = "getAllRequestIds";

    public static final Event ITEMCREATED_EVENT = new Event("ItemCreated", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
    ;

    @Deprecated
    protected SmartContractV1(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected SmartContractV1(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected SmartContractV1(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected SmartContractV1(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static List<ItemCreatedEventResponse> getItemCreatedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(ITEMCREATED_EVENT, transactionReceipt);
        ArrayList<ItemCreatedEventResponse> responses = new ArrayList<ItemCreatedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ItemCreatedEventResponse typedResponse = new ItemCreatedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.requestId = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.submissionDate = (String) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.channelId = (String) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse.name = (String) eventValues.getNonIndexedValues().get(3).getValue();
            typedResponse.nik = (String) eventValues.getNonIndexedValues().get(4).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static ItemCreatedEventResponse getItemCreatedEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(ITEMCREATED_EVENT, log);
        ItemCreatedEventResponse typedResponse = new ItemCreatedEventResponse();
        typedResponse.log = log;
        typedResponse.requestId = (String) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.submissionDate = (String) eventValues.getNonIndexedValues().get(1).getValue();
        typedResponse.channelId = (String) eventValues.getNonIndexedValues().get(2).getValue();
        typedResponse.name = (String) eventValues.getNonIndexedValues().get(3).getValue();
        typedResponse.nik = (String) eventValues.getNonIndexedValues().get(4).getValue();
        return typedResponse;
    }

    public Flowable<ItemCreatedEventResponse> itemCreatedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getItemCreatedEventFromLog(log));
    }

    public Flowable<ItemCreatedEventResponse> itemCreatedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(ITEMCREATED_EVENT));
        return itemCreatedEventFlowable(filter);
    }

    public RemoteFunctionCall<TransactionReceipt> createItem(String requestId, String submissionDate, String channelId, String name, String nik) {
        final Function function = new Function(
                FUNC_CREATEITEM, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(requestId), 
                new org.web3j.abi.datatypes.Utf8String(submissionDate), 
                new org.web3j.abi.datatypes.Utf8String(channelId), 
                new org.web3j.abi.datatypes.Utf8String(name), 
                new org.web3j.abi.datatypes.Utf8String(nik)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> updateItem(String requestId, String submissionDate, String channelId, String name, String nik) {
        final Function function = new Function(
                FUNC_UPDATEITEM, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(requestId), 
                new org.web3j.abi.datatypes.Utf8String(submissionDate), 
                new org.web3j.abi.datatypes.Utf8String(channelId), 
                new org.web3j.abi.datatypes.Utf8String(name), 
                new org.web3j.abi.datatypes.Utf8String(nik)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<List> getAllRequestIds() {
        final Function function = new Function(FUNC_GETALLREQUESTIDS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Utf8String>>() {}));
        return new RemoteFunctionCall<List>(function,
                new Callable<List>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public List call() throws Exception {
                        List<Type> result = (List<Type>) executeCallSingleValueReturn(function, List.class);
                        return convertToNative(result);
                    }
                });
    }

    @Deprecated
    public static SmartContractV1 load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new SmartContractV1(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static SmartContractV1 load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new SmartContractV1(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static SmartContractV1 load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new SmartContractV1(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static SmartContractV1 load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new SmartContractV1(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<SmartContractV1> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(SmartContractV1.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<SmartContractV1> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(SmartContractV1.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<SmartContractV1> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(SmartContractV1.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<SmartContractV1> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(SmartContractV1.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static class ItemCreatedEventResponse extends BaseEventResponse {
        public String requestId;

        public String submissionDate;

        public String channelId;

        public String name;

        public String nik;
    }
}
