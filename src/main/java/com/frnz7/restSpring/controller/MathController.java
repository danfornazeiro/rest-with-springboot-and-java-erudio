package com.frnz7.restSpring.controller;

import com.frnz7.restSpring.service.OperationsService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/math")
public class MathController {

    private final OperationsService operationsService;
    public MathController(OperationsService operationsService) {
        this.operationsService = operationsService;
    }

    @RequestMapping("/sum/{number1}/{number2}")
    public double sum(@PathVariable String number1, @PathVariable String number2)  {
        return operationsService.sum(number1, number2);
    }

    @RequestMapping("/sub/{number1}/{number2}")
    public double subtraction(@PathVariable String number1, @PathVariable String number2)  {
       return operationsService.subtraction(number1, number2);
    }

    @RequestMapping("/mult/{number1}/{number2}")
    public double multiplication(@PathVariable String number1, @PathVariable String number2){
        return operationsService.multiplication(number1, number2);
    }

    @RequestMapping("/div/{number1}/{number2}")
    public double div(@PathVariable String number1, @PathVariable String number2){
       return operationsService.division(number1, number2);
    }

    @RequestMapping("/mean/{number1}/{number2}")
    public double mean(@PathVariable String number1, @PathVariable String number2){
       return operationsService.mean(number1, number2);
    }

    @RequestMapping("/sqr/{number1}")
    public double squareRoot(@PathVariable String number1){
       return operationsService.squareRoot(number1);
    }

}
