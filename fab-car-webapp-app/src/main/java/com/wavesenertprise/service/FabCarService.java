package com.wavesenertprise.service;

import com.wavesenertprise.entity.*;
import com.wavesenertprise.repository.*;
import com.wavesenterprise.contract.api.*;
import com.wavesenterprise.contract.domain.*;
import com.wavesenterprise.sdk.contract.client.invocation.factory.*;
import com.wavesenterprise.sdk.node.domain.contract.*;
import java.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

@Service
public class FabCarService {
    private ContractBlockingClientFactory<FabCarContract> contractClient;
    private CarRepository carRepository;

    @Autowired
    public void setContractClient(
            ContractBlockingClientFactory<FabCarContract> contractClient,
            CarRepository carRepository
    ) {
        this.contractClient = contractClient;
        this.carRepository = carRepository;
    }

    public String initFab() {
        ExecutionContext executionContext = contractClient.executeContract(
                null, (FabCarContract fabCarContract) -> {
                    fabCarContract.initFab();
                    return null;
                });
        return executionContext.getTx().getId().asBase58String();
    }

    public String queryCar(String carNumber, String contractId) {
        ExecutionContext executionContext = contractClient.executeContract(
                ContractId.fromBase58(contractId), (FabCarContract fabCarContract) -> {
                    fabCarContract.queryCar(carNumber);
                    return null;
                });
        return executionContext.getTx().getId().asBase58String();
    }

    public String createCar(Car car, String contractId) {
        ExecutionContext executionContext = contractClient.executeContract(
                ContractId.fromBase58(contractId), (FabCarContract fabCarContract) -> {
                    fabCarContract.createCar(car);
                    return null;
                });
        return executionContext.getTx().getId().asBase58String();
    }

    public String changeCarOwner(String carNumber, String owner, String contractId) {
        ExecutionContext executionContext = contractClient.executeContract(
                ContractId.fromBase58(contractId), (FabCarContract fabCarContract) -> {
                    fabCarContract.changeCarOwner(carNumber, owner);
                    return null;
                });
        return executionContext.getTx().getId().asBase58String();
    }

    public List<CarEntity> getAll() {
        return (List<CarEntity>) carRepository.findAll();
    }
}
