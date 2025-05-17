package com.manoj.service;

import com.manoj.model.Car;
import com.manoj.model.CarD;
import com.manoj.request.CreateCarRequest;

import java.util.List;

public interface CarService {

    public Car createCar(CreateCarRequest req, String category, CarD carD);

    void deleteCar(Long carId) throws Exception;

    public List<Car> getCarDCar(Long carDId, boolean iseV,
                                boolean isDiesel,
                                boolean isPetrol,
                                boolean isSpotrCar,
                                String carCategory
    );

    public List<Car>searchCar(String keyword);
    public Car findCarById(Long carId) throws Exception;
    public Car updateAvailibilityStatus(Long carId) throws Exception;


}

