package com.java.springbootfizzbuzz.api.controller;

import com.java.springbootfizzbuzz.api.exception.ServiceException;
import com.java.springbootfizzbuzz.api.service.FizzBuzzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FizzBuzzController {

    private FizzBuzzService fizzBuzzService;

    @Autowired
    public FizzBuzzController(FizzBuzzService fizzBuzzService) {
        this.fizzBuzzService = fizzBuzzService;
    }

    @GetMapping("/fizzBuzz")
    //Use response entity to control HTTP response including error message
    public ResponseEntity<List<String>> getFizzBuzz(@RequestParam("limit") int limit) {
        try {
            return ResponseEntity.ok(fizzBuzzService.getFizzBuzz(limit));
        } catch (ServiceException e) {
            return ResponseEntity.badRequest().body(List.of(e.getMessage()));
        }
    }
}
