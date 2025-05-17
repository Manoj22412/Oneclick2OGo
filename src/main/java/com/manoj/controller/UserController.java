package com.manoj.controller;

import com.manoj.model.User;
import com.manoj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    public ResponseEntity<User>findByJwtToken(@RequestHeader("Authorization")String jwt) throws Exception {
        User user=userService.findUserBYJwtToken(jwt);
    return  new ResponseEntity<>(user, HttpStatus.OK);
    }
}
