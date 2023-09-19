package com.wavesenterprise.contract.api;

import com.wavesenterprise.contract.domain.*;
import com.wavesenterprise.sdk.contract.api.annotation.*;
import java.util.*;

public interface FabCarContract {

    @ContractInit
    void initFab();

    @ContractAction
    void queryCar(@InvokeParam(name = "carNumber") String carNumber);

    @ContractAction
    void createCar(@InvokeParam(name = "car") Car car);

    @ContractAction
    void changeCarOwner(
            @InvokeParam(name = "carNumber") String carNumber,
            @InvokeParam(name = "carOwner")String carOwner
    );

    class Keys {
        public static final String CONTRACT_CREATOR = "CONTRACT_CREATOR";
        public static final String CARS_MAPPING_PREFIX = "CARS";
    }
}
