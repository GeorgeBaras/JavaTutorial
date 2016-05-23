package com.apakgroup.training.tutorial.rest;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javassist.NotFoundException;

@Controller
public class ExceptionHandlingController {

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "bad input") // 400
    @ExceptionHandler({ IllegalArgumentException.class })
    public String invalidArgument(String message) {
        return "message";
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "bad input") // 404
    @ExceptionHandler({ NotFoundException.class })
    public String notFound(String message) {
        return "message";
    }

}
