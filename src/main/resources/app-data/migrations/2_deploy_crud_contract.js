// migrations/2_deploy_crud_contract.js
const CrudContract = artifacts.require("CrudContract");

module.exports = function (deployer) {
    deployer.deploy(CrudContract);
};
