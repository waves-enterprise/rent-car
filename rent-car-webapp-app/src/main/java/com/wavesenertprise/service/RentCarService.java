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
public class RentCarService {
    private ContractBlockingClientFactory<RentCarContract> contractClient;
    private CarRepository carRepository;

    @Autowired
    public void setContractClient(
            ContractBlockingClientFactory<RentCarContract> contractClient,
            CarRepository carRepository
    ) {
        this.contractClient = contractClient;
        this.carRepository = carRepository;
    }

    public String initRent() {
        ExecutionContext executionContext = contractClient.executeContract(
                null, (RentCarContract rentCarContract) -> {
                    rentCarContract.initRent();
                    return null;
                });
        return executionContext.getTx().getId().asBase58String();
    }

    public String queryCar(String carNumber, String contractId) {
        ExecutionContext executionContext = contractClient.executeContract(
                ContractId.fromBase58(contractId), (RentCarContract rentCarContract) -> {
                    rentCarContract.rentCar(carNumber);
                    return null;
                });
        return executionContext.getTx().getId().asBase58String();
    }

    public String createCar(Car car, String contractId) {
        ExecutionContext executionContext = contractClient.executeContract(
                ContractId.fromBase58(contractId), (RentCarContract rentCarContract) -> {
                    rentCarContract.createCar(car);
                    return null;
                });
        return executionContext.getTx().getId().asBase58String();
    }

    public String changeCarRenter(String carNumber, String renter, String contractId) {
        ExecutionContext executionContext = contractClient.executeContract(
                ContractId.fromBase58(contractId), (RentCarContract rentCarContract) -> {
                    rentCarContract.changeCarRenter(carNumber, renter);
                    return null;
                });
        return executionContext.getTx().getId().asBase58String();
    }

    public List<CarEntity> getAll() {
        return (List<CarEntity>) carRepository.findAll();
    }
}
