package com.upgrad.bookmyconsultation.controller;

import com.upgrad.bookmyconsultation.entity.Rating;
import com.upgrad.bookmyconsultation.exception.InvalidInputException;
import com.upgrad.bookmyconsultation.service.RatingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class RatingsController {

    @Autowired
    private RatingsService ratingsService;

    @PostMapping("/ratings")
    public ResponseEntity submitRatings(@RequestBody Rating rating) throws InvalidInputException {
        ratingsService.submitRatings(rating);
        return new ResponseEntity("", HttpStatus.OK);
    }
}
