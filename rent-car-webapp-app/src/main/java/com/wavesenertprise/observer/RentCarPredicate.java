package com.wavesenertprise.observer;

import com.wavesenterprise.sdk.node.client.blocking.contract.*;
import com.wavesenterprise.sdk.node.domain.tx.*;
import com.wavesenterprise.we.tx.observer.api.tx.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

@Service
public class RentCarPredicate implements TxEnqueuePredicate {

    private ContractService contractService;

    @Value("${contracts.config.rentCarContract.image}")
    private String rentCarContractImage;

    @Autowired
    public RentCarPredicate(ContractService contractService) {
        this.contractService = contractService;
    }


    @Override
    public boolean isEnqueued(Tx tx) {
        return switch (tx) {
            case ExecutedContractTx executedContractTx -> rentCarContractImage.equals(getImage(executedContractTx));
            default -> false;
        };
    }

    private String getImage(ExecutedContractTx executedContractTx) {
        return switch (executedContractTx.getTx()) {
            case CallContractTx callContractTx ->
                    contractService.getContractInfo(callContractTx.getContractId()).get().getImage().getValue();
            case CreateContractTx createContractTx -> createContractTx.getImage().getValue();
            case UpdateContractTx updateContractTx -> updateContractTx.getImage().getValue();
            default -> null;
        };
    }
}
