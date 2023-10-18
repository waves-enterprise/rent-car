package com.wavesenterprise.contract;

import com.fasterxml.jackson.databind.*;
import com.wavesenterprise.contract.app.*;
import com.wavesenterprise.sdk.contract.core.dispatch.*;
import com.wavesenterprise.sdk.contract.grpc.*;

public class RentCarContractDispatcher {
    public static void main(String[] args) {
        ContractDispatcher contractDispatcher = GrpcJacksonContractDispatcherBuilder.builder()
                .contractHandlerType(RentCarContractImpl.class)
                .objectMapper(new ObjectMapper())
                .build();

        contractDispatcher.dispatch();
    }
}
