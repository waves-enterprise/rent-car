package com.wavesenertprise.config;

import com.fasterxml.jackson.datatype.jsr310.*;
import com.fasterxml.jackson.module.kotlin.*;
import com.wavesenterprise.contract.api.*;
import com.wavesenterprise.contract.app.*;
import com.wavesenterprise.sdk.node.client.blocking.credentials.*;
import com.wavesenterprise.sdk.spring.autoconfigure.contract.annotation.*;
import org.springframework.context.annotation.*;

@Configuration
@EnableContracts(
        contracts = {
                @Contract(
                        api = FabCarContract.class,
                        impl = FabCarContractImpl.class,
                        name = "fabCarContract"
                )
        }
)
public class FabCarAppConfiguration {

    @Bean
    JavaTimeModule javaTimeModule() {
        return new JavaTimeModule();
    };


    @Bean
    SenderAddressProvider senderAddressProvider() {
        return new HttpSenderAddressProvider();
    }
}
