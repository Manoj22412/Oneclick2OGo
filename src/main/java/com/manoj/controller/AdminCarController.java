package com.manoj.controller;

import com.manoj.Repository.CarRepository;
import com.manoj.model.Car;
import com.manoj.model.CarD;
import com.manoj.model.User;
import com.manoj.request.CreateCarRequest;
import com.manoj.response.MassageResponse;
import com.manoj.service.CarDService;
import com.manoj.service.CarService;
import com.manoj.service.UserService;
import jakarta.persistence.Access;
import org.apache.coyote.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/car")
public class AdminCarController {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private CarDService carDService;

    @Autowired
    private CarService carService;


    @PostMapping
    public ResponseEntity<Car> createCar(@RequestBody CreateCarRequest req,
                                         @RequestHeader("Authorization")String jwt) throws Exception{
        User user=userService.findUserBYJwtToken(jwt);
        CarD carD=carDService.findCarDById(req.getCarDId());
        Car car=carService.createCar(req,req.getCategory(),carD);

        return new  ResponseEntity<>(car,HttpStatus.CREATED);
    }

    @PostMapping("/{id}")
    public ResponseEntity<MassageResponse> deleteCar(@PathVariable Long id,
                                                     @RequestHeader("Authorization")String jwt) throws Exception{
        User user=userService.findUserBYJwtToken(jwt);

        carService.deleteCar(id);

        MassageResponse res = new MassageResponse();
        res.setMassage("car has been deleted");

        return new  ResponseEntity<>(res,HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Car> updateCarAvaibilityStatus(@PathVariable Long id,
                                                     @RequestHeader("Authorization")String jwt) throws Exception{
        User user=userService.findUserBYJwtToken(jwt);

        Car car = carService.updateAvailibilityStatus(id);

        return new ResponseEntity<>(car,HttpStatus.CREATED);
    }

}
