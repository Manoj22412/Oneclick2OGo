package com.manoj.controller;

import com.manoj.model.CarD;
import com.manoj.model.User;
import com.manoj.request.CreateCarDRequest;
import com.manoj.response.MassageResponse;
import com.manoj.service.CarDService;
import com.manoj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/CarDs")
public class AdminCarDController {
    @Autowired
    private CarDService carDService;

    @Autowired
    private UserService userService;

    @PostMapping()
    public ResponseEntity<CarD> createCard(
            @RequestBody CreateCarDRequest req,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        User user = userService.findUserBYJwtToken(jwt);

        CarD card = carDService.CreateCarD(req, user);
        return new ResponseEntity<>(card, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarD> updateCard(
            @RequestBody CreateCarDRequest req,
            @RequestHeader("Authorization") String jwt,
            @PathVariable Long id
    ) throws Exception {
        User user = userService.findUserBYJwtToken(jwt);

        CarD card = carDService.updateCarD(id, req);
        return new ResponseEntity<>(card, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MassageResponse> deleteCard(
            @RequestHeader("Authorization") String jwt,
            @PathVariable Long id
    ) throws Exception {
        User user = userService.findUserBYJwtToken(jwt);

        carDService.deleteCarD(id);
        MassageResponse res = new MassageResponse();
        res.setMassage("CarD delete successfully");
        return new ResponseEntity<>(res, HttpStatus.OK);


    }
    @PutMapping("/{id}/status")
    public ResponseEntity<CarD> updateCarDStatus(
            @RequestHeader("Authorization") String jwt,
            @PathVariable Long id
    ) throws Exception {
        User user = userService.findUserBYJwtToken(jwt);

        CarD carD = carDService.updateCarDStatus(id);

        return new ResponseEntity<>(carD, HttpStatus.OK);
    }
    @GetMapping("/user")
    public ResponseEntity<CarD> findCarDByUserId(
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        User user = userService.findUserBYJwtToken(jwt);

        CarD card = carDService.getCarDByUserId(user.getId());
        return new ResponseEntity<>(card, HttpStatus.OK);
    }
}
