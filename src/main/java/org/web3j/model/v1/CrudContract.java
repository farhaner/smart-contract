package org.web3j.model.v1;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.DynamicStruct;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple2;
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
public class CrudContract extends Contract {
    public static final String BINARY = "0x6080604052600160005534801561001557600080fd5b50611005806100256000396000f3fe608060405234801561001057600080fd5b506004361061007d5760003560e01c8063b6a46b3b1161005b578063b6a46b3b146100ec578063da6ea12a14610108578063ed2e5a9714610139578063f745630f146101695761007d565b80630f4c96b2146100825780634cc82215146100b257806361b8ce8c146100ce575b600080fd5b61009c600480360381019061009791906106de565b610185565b6040516100a991906108bc565b60405180910390f35b6100cc60048036038101906100c791906108de565b610338565b005b6100d66103ce565b6040516100e3919061091a565b60405180910390f35b61010660048036038101906101019190610a6a565b6103d4565b005b610122600480360381019061011d91906108de565b61043c565b604051610130929190610afd565b60405180910390f35b610153600480360381019061014e91906108de565b6104e8565b6040516101609190610b2d565b60405180910390f35b610183600480360381019061017e9190610b4f565b610590565b005b606060008267ffffffffffffffff8111156101a3576101a261093f565b5b6040519080825280602002602001820160405280156101dc57816020015b6101c961061d565b8152602001906001900390816101c15790505b5090506000806001866101ef9190610bda565b90505b600054811115801561020357508482105b1561032c57600060016000838152602001908152602001600020600101805461022b90610c3d565b90501461031957600160008281526020019081526020016000206040518060400160405290816000820154815260200160018201805461026a90610c3d565b80601f016020809104026020016040519081016040528092919081815260200182805461029690610c3d565b80156102e35780601f106102b8576101008083540402835291602001916102e3565b820191906000526020600020905b8154815290600101906020018083116102c657829003601f168201915b5050505050815250508383815181106102ff576102fe610c6e565b5b6020026020010181905250818061031590610c9d565b9250505b808061032490610c9d565b9150506101f2565b50819250505092915050565b600060016000838152602001908152602001600020600101805461035b90610c3d565b90500361039d576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161039490610d31565b60405180910390fd5b600160008281526020019081526020016000206000808201600090556001820160006103c99190610637565b505050565b60005481565b6040518060400160405280600054815260200182815250600160008054815260200190815260200160002060008201518160000155602082015181600101908161041e9190610efd565b5090505060008081548092919061043490610c9d565b919050555050565b600160205280600052604060002060009150905080600001549080600101805461046590610c3d565b80601f016020809104026020016040519081016040528092919081815260200182805461049190610c3d565b80156104de5780601f106104b3576101008083540402835291602001916104de565b820191906000526020600020905b8154815290600101906020018083116104c157829003601f168201915b5050505050905082565b606060016000838152602001908152602001600020600101805461050b90610c3d565b80601f016020809104026020016040519081016040528092919081815260200182805461053790610c3d565b80156105845780601f1061055957610100808354040283529160200191610584565b820191906000526020600020905b81548152906001019060200180831161056757829003601f168201915b50505050509050919050565b60006001600084815260200190815260200160002060010180546105b390610c3d565b9050036105f5576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016105ec90610d31565b60405180910390fd5b806001600084815260200190815260200160002060010190816106189190610efd565b505050565b604051806040016040528060008152602001606081525090565b50805461064390610c3d565b6000825580601f106106555750610674565b601f0160209004906000526020600020908101906106739190610677565b5b50565b5b80821115610690576000816000905550600101610678565b5090565b6000604051905090565b600080fd5b600080fd5b6000819050919050565b6106bb816106a8565b81146106c657600080fd5b50565b6000813590506106d8816106b2565b92915050565b600080604083850312156106f5576106f461069e565b5b6000610703858286016106c9565b9250506020610714858286016106c9565b9150509250929050565b600081519050919050565b600082825260208201905092915050565b6000819050602082019050919050565b610753816106a8565b82525050565b600081519050919050565b600082825260208201905092915050565b60005b83811015610793578082015181840152602081019050610778565b60008484015250505050565b6000601f19601f8301169050919050565b60006107bb82610759565b6107c58185610764565b93506107d5818560208601610775565b6107de8161079f565b840191505092915050565b6000604083016000830151610801600086018261074a565b506020830151848203602086015261081982826107b0565b9150508091505092915050565b600061083283836107e9565b905092915050565b6000602082019050919050565b60006108528261071e565b61085c8185610729565b93508360208202850161086e8561073a565b8060005b858110156108aa578484038952815161088b8582610826565b94506108968361083a565b925060208a01995050600181019050610872565b50829750879550505050505092915050565b600060208201905081810360008301526108d68184610847565b905092915050565b6000602082840312156108f4576108f361069e565b5b6000610902848285016106c9565b91505092915050565b610914816106a8565b82525050565b600060208201905061092f600083018461090b565b92915050565b600080fd5b600080fd5b7f4e487b7100000000000000000000000000000000000000000000000000000000600052604160045260246000fd5b6109778261079f565b810181811067ffffffffffffffff821117156109965761099561093f565b5b80604052505050565b60006109a9610694565b90506109b5828261096e565b919050565b600067ffffffffffffffff8211156109d5576109d461093f565b5b6109de8261079f565b9050602081019050919050565b82818337600083830152505050565b6000610a0d610a08846109ba565b61099f565b905082815260208101848484011115610a2957610a2861093a565b5b610a348482856109eb565b509392505050565b600082601f830112610a5157610a50610935565b5b8135610a618482602086016109fa565b91505092915050565b600060208284031215610a8057610a7f61069e565b5b600082013567ffffffffffffffff811115610a9e57610a9d6106a3565b5b610aaa84828501610a3c565b91505092915050565b600082825260208201905092915050565b6000610acf82610759565b610ad98185610ab3565b9350610ae9818560208601610775565b610af28161079f565b840191505092915050565b6000604082019050610b12600083018561090b565b8181036020830152610b248184610ac4565b90509392505050565b60006020820190508181036000830152610b478184610ac4565b905092915050565b60008060408385031215610b6657610b6561069e565b5b6000610b74858286016106c9565b925050602083013567ffffffffffffffff811115610b9557610b946106a3565b5b610ba185828601610a3c565b9150509250929050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052601160045260246000fd5b6000610be5826106a8565b9150610bf0836106a8565b9250828201905080821115610c0857610c07610bab565b5b92915050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052602260045260246000fd5b60006002820490506001821680610c5557607f821691505b602082108103610c6857610c67610c0e565b5b50919050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052603260045260246000fd5b6000610ca8826106a8565b91507fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff8203610cda57610cd9610bab565b5b600182019050919050565b7f44617461206e6f7420666f756e64000000000000000000000000000000000000600082015250565b6000610d1b600e83610ab3565b9150610d2682610ce5565b602082019050919050565b60006020820190508181036000830152610d4a81610d0e565b9050919050565b60008190508160005260206000209050919050565b60006020601f8301049050919050565b600082821b905092915050565b600060088302610db37fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff82610d76565b610dbd8683610d76565b95508019841693508086168417925050509392505050565b6000819050919050565b6000610dfa610df5610df0846106a8565b610dd5565b6106a8565b9050919050565b6000819050919050565b610e1483610ddf565b610e28610e2082610e01565b848454610d83565b825550505050565b600090565b610e3d610e30565b610e48818484610e0b565b505050565b5b81811015610e6c57610e61600082610e35565b600181019050610e4e565b5050565b601f821115610eb157610e8281610d51565b610e8b84610d66565b81016020851015610e9a578190505b610eae610ea685610d66565b830182610e4d565b50505b505050565b600082821c905092915050565b6000610ed460001984600802610eb6565b1980831691505092915050565b6000610eed8383610ec3565b9150826002028217905092915050565b610f0682610759565b67ffffffffffffffff811115610f1f57610f1e61093f565b5b610f298254610c3d565b610f34828285610e70565b600060209050601f831160018114610f675760008415610f55578287015190505b610f5f8582610ee1565b865550610fc7565b601f198416610f7586610d51565b60005b82811015610f9d57848901518255600182019150602085019450602081019050610f78565b86831015610fba5784890151610fb6601f891682610ec3565b8355505b6001600288020188555050505b50505050505056fea264697066735822122033c52b8fe6db96c41fc095ee105852669d46fad56c7c6202a4a5c93137f3eb9464736f6c63430008130033";

    public static final String FUNC_DATASTORE = "dataStore";

    public static final String FUNC_NEXTID = "nextId";

    public static final String FUNC_CREATE = "create";

    public static final String FUNC_READ = "read";

    public static final String FUNC_UPDATE = "update";

    public static final String FUNC_REMOVE = "remove";

    public static final String FUNC_GETALL = "getAll";

    @Deprecated
    protected CrudContract(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected CrudContract(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected CrudContract(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected CrudContract(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<Tuple2<BigInteger, String>> dataStore(BigInteger param0) {
        final Function function = new Function(FUNC_DATASTORE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}));
        return new RemoteFunctionCall<Tuple2<BigInteger, String>>(function,
                new Callable<Tuple2<BigInteger, String>>() {
                    @Override
                    public Tuple2<BigInteger, String> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple2<BigInteger, String>(
                                (BigInteger) results.get(0).getValue(), 
                                (String) results.get(1).getValue());
                    }
                });
    }

    public RemoteFunctionCall<BigInteger> nextId() {
        final Function function = new Function(FUNC_NEXTID, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> create(String name) {
        final Function function = new Function(
                FUNC_CREATE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(name)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> read(BigInteger id) {
        final Function function = new Function(FUNC_READ, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(id)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> update(BigInteger id, String name) {
        final Function function = new Function(
                FUNC_UPDATE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(id), 
                new org.web3j.abi.datatypes.Utf8String(name)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> remove(BigInteger id) {
        final Function function = new Function(
                FUNC_REMOVE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(id)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<List> getAll(BigInteger offset, BigInteger limit) {
        final Function function = new Function(FUNC_GETALL, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(offset), 
                new org.web3j.abi.datatypes.generated.Uint256(limit)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Data>>() {}));
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
    public static CrudContract load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new CrudContract(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static CrudContract load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new CrudContract(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static CrudContract load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new CrudContract(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static CrudContract load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new CrudContract(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<CrudContract> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(CrudContract.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<CrudContract> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(CrudContract.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<CrudContract> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(CrudContract.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<CrudContract> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(CrudContract.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static class Data extends DynamicStruct {
        public BigInteger id;

        public String name;

        public Data(BigInteger id, String name) {
            super(new org.web3j.abi.datatypes.generated.Uint256(id), 
                    new org.web3j.abi.datatypes.Utf8String(name));
            this.id = id;
            this.name = name;
        }

        public Data(Uint256 id, Utf8String name) {
            super(id, name);
            this.id = id.getValue();
            this.name = name.getValue();
        }
    }
}
