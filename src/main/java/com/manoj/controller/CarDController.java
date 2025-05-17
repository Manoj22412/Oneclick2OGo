package com.manoj.controller;

import com.manoj.dto.CarDDto;
import com.manoj.model.CarD;
import com.manoj.model.User;
import com.manoj.service.CarDService;
import com.manoj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carD")
public class CarDController {
    @Autowired
    private CarDService carDService;

    @Autowired
    private UserService userService;

    @GetMapping("/search")
    public ResponseEntity<List<CarD>> searchCarD(
            @RequestHeader("Authorization") String jwt,
            @RequestParam String keyword
    ) throws Exception {
        User user = userService.findUserBYJwtToken(jwt);

        List<CarD> carD = carDService.searchCarD(keyword);
        return new ResponseEntity<>(carD, HttpStatus.OK);
    }
    @GetMapping()
    public ResponseEntity<List<CarD>> getAllCarD(
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        User user = userService.findUserBYJwtToken(jwt);

        List<CarD> carD = carDService.getAllCarD();
        return new ResponseEntity<>(carD, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<CarD> findCarDById(
            @RequestHeader("Authorization") String jwt,
            @PathVariable long id
    ) throws Exception {
        User user = userService.findUserBYJwtToken(jwt);

        CarD carD = carDService.findCarDById(id);
        return new ResponseEntity<>(carD, HttpStatus.OK);
    }

    @PutMapping("/{id}/add-favorites")
    public ResponseEntity<CarDDto> addToFavorites(
            @RequestHeader("Authorization") String jwt,
            @PathVariable long id
    ) throws Exception {
        User user = userService.findUserBYJwtToken(jwt);

        CarDDto carD = carDService.addToFavorites(id,user);

        return new ResponseEntity<>(carD, HttpStatus.OK);
    }

}
