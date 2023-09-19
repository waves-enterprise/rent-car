package com.wavesenertprise.controller;

import com.wavesenertprise.entity.*;
import com.wavesenertprise.service.*;
import com.wavesenterprise.contract.domain.*;
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
    public String init() {
        return fabCarService.initFab();
    }

    @PostMapping("/car/{carNumber}/query/{contractId}")
    public String queryCar(
            @PathVariable String contractId,
            @PathVariable String carNumber
    ) {
        return fabCarService.queryCar(carNumber, contractId);
    }

    @PostMapping("/car/{contractId}/create")
    public String createCar(
            @PathVariable String contractId,
            @RequestBody Car car
    ) {
        return fabCarService.createCar(car, contractId);
    }

    @PostMapping("/car/{contractId}/{carNumber}/new-owner/{carOwner}")
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
