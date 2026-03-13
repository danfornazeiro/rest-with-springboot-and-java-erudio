package com.frnz7.restSpring.helpers;

import com.frnz7.restSpring.exception.UnsupportedMathOperationException;

public class ConvertToDouble {

    public static double convertNumberToDouble(String strNumber){
        if(strNumber == null || strNumber.isEmpty()){
            throw new UnsupportedMathOperationException("Please set a numeric value");
        }
        String number = strNumber.replace(",", ".");
        return Double.parseDouble(number);
    }

}
