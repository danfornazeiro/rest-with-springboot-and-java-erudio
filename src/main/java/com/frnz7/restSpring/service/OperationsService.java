package com.frnz7.restSpring.service;

import com.frnz7.restSpring.exception.UnsupportedMathOperationException;
import com.frnz7.restSpring.helpers.ConvertToDouble;
import com.frnz7.restSpring.helpers.IsNumeric;
import org.springframework.stereotype.Service;

@Service
public class OperationsService {

    public double sum(String number1, String number2) {
        if(!IsNumeric.isNumeric(number1) || !IsNumeric.isNumeric(number2)) throw new UnsupportedMathOperationException("Please set a numeric value");
        return ConvertToDouble.convertNumberToDouble(number1)+ConvertToDouble.convertNumberToDouble(number2);
    }

    public double subtraction(String number1, String number2) {
        if(!IsNumeric.isNumeric(number1) || !IsNumeric.isNumeric(number2)) throw new UnsupportedMathOperationException("Please set a numeric value");
        return ConvertToDouble.convertNumberToDouble(number1)-ConvertToDouble.convertNumberToDouble(number2);
    }

    public double multiplication(String number1, String number2) {
        if(!IsNumeric.isNumeric(number1) || !IsNumeric.isNumeric(number2)) throw new UnsupportedMathOperationException("Please set a numeric value");
        return ConvertToDouble.convertNumberToDouble(number1)*ConvertToDouble.convertNumberToDouble(number2);
    }

    public double division(String number1, String number2) {
        if(!IsNumeric.isNumeric(number1) || !IsNumeric.isNumeric(number2)) throw new UnsupportedMathOperationException("Please set a numeric value");
        return ConvertToDouble.convertNumberToDouble(number1)/ConvertToDouble.convertNumberToDouble(number2);
    }

    public double mean(String number1, String number2) {
        if(!IsNumeric.isNumeric(number1) || !IsNumeric.isNumeric(number2)) throw new UnsupportedMathOperationException("Please set a numeric value");
        return (ConvertToDouble.convertNumberToDouble(number1) +  ConvertToDouble.convertNumberToDouble(number2)) / 2;
    }

    public double squareRoot(String number){
        if (!IsNumeric.isNumeric(number)) throw new UnsupportedMathOperationException("Please set a numeric value");
        return Math.sqrt(ConvertToDouble.convertNumberToDouble(number));
    }

}
