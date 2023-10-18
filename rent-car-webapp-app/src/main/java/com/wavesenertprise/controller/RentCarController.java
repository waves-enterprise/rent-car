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
@RequestMapping("/rent")
public class RentCarController {
    private RentCarService rentCarService;

    @Autowired
    public void setRentCarService(RentCarService rentCarService) {
        this.rentCarService = rentCarService;
    }

    @PostMapping("/init")
    @Parameter(in = ParameterIn.HEADER,
            name = "X-Tx-Sender",
            required = true,
            example = "3M3xGmJGmxBv2aZ4UFmn93rHxVXTJDKSAnh"
    )
    public String init() {
        return rentCarService.initRent();
    }

    @PostMapping("/car/{carNumber}/rent/{contractId}")
    @Parameter(in = ParameterIn.HEADER,
            name = "X-Tx-Sender",
            required = true,
            example = "3M3xGmJGmxBv2aZ4UFmn93rHxVXTJDKSAnh"
    )
    public String rentCar(
            @PathVariable String contractId,
            @PathVariable String carNumber
    ) {
        return rentCarService.rentCar(carNumber, contractId);
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
        return rentCarService.createCar(car, contractId);
    }

    @PostMapping("/car/{contractId}/{carNumber}/new-owner/{carRenter}")
    @Parameter(in = ParameterIn.HEADER,
            name = "X-Tx-Sender",
            required = true,
            example = "3M3xGmJGmxBv2aZ4UFmn93rHxVXTJDKSAnh"
    )
    public String changecarRenter(
            @PathVariable String contractId,
            @PathVariable String carNumber,
            @PathVariable String carRenter
    ) {
        return rentCarService.changeCarRenter(carNumber, carRenter, contractId);
    }

    @GetMapping("/car/get-all")
    public List<CarEntity> getAll() {
        return rentCarService.getAll();
    }
}
