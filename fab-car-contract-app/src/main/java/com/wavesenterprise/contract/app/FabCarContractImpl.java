package com.wavesenterprise.contract.app;

import com.wavesenterprise.contract.api.*;
import com.wavesenterprise.contract.domain.*;
import com.wavesenterprise.sdk.contract.api.annotation.*;
import com.wavesenterprise.sdk.contract.api.domain.*;
import com.wavesenterprise.sdk.contract.api.state.*;
import com.wavesenterprise.sdk.contract.api.state.mapping.*;
import java.util.*;
import static com.wavesenterprise.contract.api.FabCarContract.Keys.CARS_MAPPING_PREFIX;
import static com.wavesenterprise.contract.api.FabCarContract.Keys.CONTRACT_CREATOR;

@ContractHandler
public class FabCarContractImpl implements FabCarContract {

    private final ContractState contractState;
    private final ContractCall call;
    private final Mapping<Car> cars;

    public FabCarContractImpl(ContractState contractState, ContractCall call) {
        this.contractState = contractState;
        this.call = call;
        this.cars = contractState.getMapping(Car.class, CARS_MAPPING_PREFIX);
    }

    @Override
    public void initFab() {
        contractState.put(CONTRACT_CREATOR, call.getCaller());
        cars.put("1", new Car("beatle", null, "1"));
        cars.put("2", new Car("banana", null, "2"));
        cars.put("3", new Car("cat", null, "3"));
    }

    @Override
    public void queryCar(String carNumber) {
        Car car = getCarIfExist(carNumber);
        checkPossibilityQueryCar(car);
        car.setOwner(call.getCaller());
        cars.put(carNumber, car);
    }

    @Override
    public void createCar(Car car) {
        checkContractCreator();
        cars.put(car.getNumber(), car);
    }

    @Override
    public void changeCarOwner(String carNumber, String carOwner) {
        checkContractCreator();
        Car car = getCarIfExist(carNumber);
        car.setOwner(carOwner);
        cars.put(carNumber, car);
    }

    private void checkPossibilityQueryCar(Car car) {
        if (car.getOwner() != null) {
            throw new IllegalStateException("Car with number " + car.getNumber() + " has owner.");
        }
     }

     private void checkContractCreator() {
         String contractCreator = contractState.get(CONTRACT_CREATOR, String.class);
         if (!contractCreator.equals(call.getCaller())) {
             throw new IllegalStateException("Only contract creator can create cars or change car owner.");
         }
     }

     private Car getCarIfExist(String carNumber) {
         Optional<Car> optionalCar = cars.tryGet(carNumber);
         return optionalCar.orElseThrow(() -> new IllegalStateException(""));
     }
}
