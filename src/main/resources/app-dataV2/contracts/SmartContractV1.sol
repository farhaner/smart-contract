// SPDX-License-Identifier: MIT
pragma solidity ^0.8.0;

contract SmartContractV1 {
    struct Item {
        string requestId;
        string submissionDate;
        string channelId;
        string name;
        string nik;
    }

    event ItemCreated (
        string requestId,
        string submissionDate,
        string channelId,
        string name,
        string nik
    );

    mapping(string => Item) private items;
    string[] private requestIdList;

    function createItem(
        string memory requestId,
        string memory submissionDate,
        string memory channelId,
        string memory name,
        string memory nik
    ) public {
        items[requestId] = Item(requestId, submissionDate, channelId, name, nik);
        requestIdList.push(requestId);
        emit ItemCreated(requestId, submissionDate, channelId, name, nik);
    }

    function updateItem(
        string memory requestId,
        string memory submissionDate,
        string memory channelId,
        string memory name,
        string memory nik
    ) public {
        Item storage item = items[requestId];
        item.submissionDate = submissionDate;
        item.channelId = channelId;
        item.name = name;
        item.nik = nik;
    }

    function getAllRequestIds() public view returns (string[] memory) {
        return requestIdList;
    }

    function getData(uint index
    ) public view returns (string memory name, string memory name) {
        return (data[index].name, data[index].age);
    }
}
