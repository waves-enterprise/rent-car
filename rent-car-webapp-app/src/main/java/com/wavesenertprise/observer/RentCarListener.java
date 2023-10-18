package com.wavesenertprise.observer;

import com.wavesenertprise.entity.*;
import com.wavesenertprise.repository.*;
import com.wavesenterprise.contract.domain.*;
import com.wavesenterprise.we.tx.observer.api.key.*;
import com.wavesenterprise.we.tx.observer.api.tx.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

@Service
public class RentCarListener {

    private CarRepository carRepository;

    @Autowired
    public RentCarListener(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    Logger log = LoggerFactory.getLogger(RentCarListener.class);

    @TxListener
    public void onCallRentCar(
            @KeyFilter(keyPrefix = "CARS_") KeyEvent<Car> keyEvent
    ) {
        Car car = keyEvent.getPayload();
        log.info("Received 104 tx [ id = {} ]", keyEvent.getTx().getId().asBase58String());
        carRepository.save(mapToEntity(car));
    }

    private CarEntity mapToEntity(Car car) {
        return new CarEntity(car.getNumber(), car.getName(), car.getRenter());
    }
}
