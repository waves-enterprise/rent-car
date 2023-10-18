package com.wavesenterprise.contract.api;

import com.wavesenterprise.contract.domain.*;
import com.wavesenterprise.sdk.contract.api.annotation.*;

public interface RentCarContract {

    @ContractInit
    void initRent();

    @ContractAction
    void rentCar(@InvokeParam(name = "carNumber") String carNumber);

    @ContractAction
    void createCar(@InvokeParam(name = "car") Car car);

    @ContractAction
    void changeCarRenter(
            @InvokeParam(name = "carNumber") String carNumber,
            @InvokeParam(name = "carRenter") String carRenter
    );

    class Keys {
        public static final String CONTRACT_CREATOR = "CONTRACT_CREATOR";
        public static final String CARS_MAPPING_PREFIX = "CARS";
    }
}
