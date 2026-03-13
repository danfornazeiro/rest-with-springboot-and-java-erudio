package com.frnz7.restSpring.controller;

import com.frnz7.restSpring.exception.UnsupportedMathOperationException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/math")
public class MathController {

    @RequestMapping("/sum/{number1}/{number2}")
    public double sum(@PathVariable String number1, @PathVariable String number2)  {

        if(!isNumeric(number1) || !isNumeric(number2)) {
            throw new UnsupportedMathOperationException("Please set a numeric value.");
        }
        return convertToDouble(number1) + convertToDouble(number2);
    }

    @RequestMapping("/sub/{number1}/{number2}")
    public double subtraction(@PathVariable String number1, @PathVariable String number2)  {
        if(!isNumeric(number1) || !isNumeric(number2)) {
            throw new UnsupportedMathOperationException("Please set a numeric value.");
        }
        return convertToDouble(number1) - convertToDouble(number2);
    }

    @RequestMapping("mult")

    //private methods
    private double convertToDouble(String strNumber) throws IllegalArgumentException {
        if(strNumber == null || strNumber.isEmpty()){
            throw new UnsupportedMathOperationException("Please set a numeric value.");
        }
        String number = strNumber.replace(",", ".");
       return Double.parseDouble(number);
    }

    public boolean isNumeric(String strNumber){
        if(strNumber == null || strNumber.isEmpty()){
            return false;
        }
        String number = strNumber.replace(",", ".");
        return number.matches("[-+]?[0-9]*\\.?[0-9]+");
    }

}
