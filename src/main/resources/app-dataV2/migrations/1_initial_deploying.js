const Migrations = artifacts.require("Migrations");
const SmartContractV1 = artifacts.require("SmartContractV1");

module.exports = function(deployer) {
  deployer.deploy(Migrations);
  deployer.deploy(SmartContractV1);
};
