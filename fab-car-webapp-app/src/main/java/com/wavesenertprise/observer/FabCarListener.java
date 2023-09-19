package com.wavesenertprise.observer;

import com.wavesenertprise.entity.*;
import com.wavesenertprise.repository.*;
import com.wavesenterprise.contract.domain.*;
import com.wavesenterprise.we.tx.observer.api.key.*;
import com.wavesenterprise.we.tx.observer.api.tx.*;
import java.util.*;
import javax.annotation.*;
import org.jetbrains.annotations.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import static com.wavesenterprise.contract.api.FabCarContract.Keys.CARS_MAPPING_PREFIX;

@Service
public class FabCarListener {

    private CarRepository carRepository;

    @Autowired
    public FabCarListener(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    Logger log = LoggerFactory.getLogger(FabCarListener.class);

    @TxListener
    public void onCallFabCar(
            @KeyFilter(keyPrefix = "CARS_") KeyEvent<Car> keyEvent
    ) {
        Car car = keyEvent.getPayload();
        log.info("Received 104 tx [ id = {} ]", keyEvent.getTx().getId().asBase58String());
        carRepository.save(mapToEntity(car));
    }

    private CarEntity mapToEntity(Car car) {
        return new CarEntity(car.getNumber(), car.getName(), car.getOwner());
    }
}
