package com.manoj.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeContoller {

    @GetMapping
    public ResponseEntity<String> HomeContoller(){
        return new ResponseEntity<>("Welcome to the ONECLICK2GO", HttpStatus.OK);
    }
}
