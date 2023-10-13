package com.wavesenertprise.controller;

import com.wavesenertprise.entity.*;
import com.wavesenertprise.service.*;
import com.wavesenterprise.contract.domain.*;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import java.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fab")
public class FabCarController {
    private FabCarService fabCarService;

    @Autowired
    public void setFabCarService(FabCarService fabCarService) {
        this.fabCarService = fabCarService;
    }

    @PostMapping("/init")
    @Parameter(in = ParameterIn.HEADER,
            name = "X-Tx-Sender",
            required = true,
            example = "3M3xGmJGmxBv2aZ4UFmn93rHxVXTJDKSAnh"
    )
    public String init() {
        return fabCarService.initFab();
    }

    @PostMapping("/car/{carNumber}/query/{contractId}")
    @Parameter(in = ParameterIn.HEADER,
            name = "X-Tx-Sender",
            required = true,
            example = "3M3xGmJGmxBv2aZ4UFmn93rHxVXTJDKSAnh"
    )
    public String queryCar(
            @PathVariable String contractId,
            @PathVariable String carNumber
    ) {
        return fabCarService.queryCar(carNumber, contractId);
    }

    @PostMapping("/car/{contractId}/create")
    @Parameter(in = ParameterIn.HEADER,
            name = "X-Tx-Sender",
            required = true,
            example = "3M3xGmJGmxBv2aZ4UFmn93rHxVXTJDKSAnh"
    )
    public String createCar(
            @PathVariable String contractId,
            @RequestBody Car car
    ) {
        return fabCarService.createCar(car, contractId);
    }

    @PostMapping("/car/{contractId}/{carNumber}/new-owner/{carOwner}")
    @Parameter(in = ParameterIn.HEADER,
            name = "X-Tx-Sender",
            required = true,
            example = "3M3xGmJGmxBv2aZ4UFmn93rHxVXTJDKSAnh"
    )
    public String changeCarOwner(
            @PathVariable String contractId,
            @PathVariable String carNumber,
            @PathVariable String carOwner
    ) {
        return fabCarService.changeCarOwner(carNumber, carOwner, contractId);
    }

    @GetMapping("/car/get-all")
    public List<CarEntity> getAll() {
        return fabCarService.getAll();
    }
}
