// contracts/CrudContract.sol
// SPDX-License-Identifier: MIT
pragma solidity ^0.8.0;

contract CrudContract {
    uint public nextId = 1;

    struct Files {
        string fileType;
        string cid;
    }

    struct Data {
        uint id;
        string requestId;
        string channelId;
        string submissionDate;
        string nik;
        string name;
        Files[] files;
    }

    mapping(uint => Data) public dataStore;

    function create(uint id,
        string memory requestId,
        string memory channelId,
        string memory submissionDate,
        string memory nik,
        string memory name,
        Files[] memory[] files
    ) public {
        dataStore[nextId] = Data(id,
            requestId,
            channelId,
            submissionDate,
            nik,
            name,
            files);
        nextId++;
    }

    function read(uint id) public view returns (string memory) {
        return dataStore[id].name;
    }

    function update(uint id, string memory name) public {
        require(bytes(dataStore[id].name).length != 0, "Data not found");
        dataStore[id].name = name;
    }

    function remove(uint id) public {
        require(bytes(dataStore[id].name).length != 0, "Data not found");
        delete dataStore[id];
    }

    function getAll(uint offset, uint limit) public view returns (Data[] memory) {
        Data[] memory result = new Data[](limit);
        uint counter = 0;
        for (uint i = offset + 1; i <= nextId && counter < limit; i++) {
            if (bytes(dataStore[i].name).length != 0) {
                result[counter] = dataStore[i];
                counter++;
            }
        }
        return result;
    }
}
