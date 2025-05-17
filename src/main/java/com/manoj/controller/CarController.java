package com.manoj.controller;

import com.manoj.Repository.CarRepository;
import com.manoj.model.Car;
import com.manoj.model.CarD;
import com.manoj.model.User;
import com.manoj.request.CreateCarRequest;
import com.manoj.service.CarDService;
import com.manoj.service.CarService;
import com.manoj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/car")
public class CarController {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private CarDService carDService;

    @Autowired
    private CarService carService;


    @GetMapping("/search")
    public ResponseEntity<List<Car>> searchCar
            (@RequestParam String name,
                                         @RequestHeader("Authorization")String jwt) throws Exception{
        User user=userService.findUserBYJwtToken(jwt);

        List<Car> cars=carService.searchCar(name);

        return new  ResponseEntity<>(cars, HttpStatus.CREATED);
    }

    @GetMapping("/car/{carId}")
    public ResponseEntity<List<Car>> getCarDCar(
            @RequestParam boolean petrol,
            @RequestParam boolean disel,
            @RequestParam boolean eV,
            @RequestParam boolean  sport,
            @RequestParam(required = false) String car_category,
            @PathVariable Long CarDId,
            @RequestHeader("Authorization") String jwt)throws Exception{
        User user=userService.findUserBYJwtToken(jwt);

        List<Car> cars=carService.getCarDCar(CarDId,petrol,disel,eV,sport,car_category);

        return new  ResponseEntity<>(cars, HttpStatus.OK);
    }

}
