package com.wavesenterprise.contract;

import com.fasterxml.jackson.databind.*;
import com.wavesenterprise.contract.app.*;
import com.wavesenterprise.sdk.contract.core.dispatch.*;
import com.wavesenterprise.sdk.contract.grpc.*;

public class FabCarContractDispatcher {
    public static void main(String[] args) {
        ContractDispatcher contractDispatcher = GrpcJacksonContractDispatcherBuilder.builder()
                .contractHandlerType(FabCarContractImpl.class)
                .objectMapper(new ObjectMapper())
                .build();

        contractDispatcher.dispatch();
    }
}
