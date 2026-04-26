package com.frnz7.restSpring.utils;

import com.frnz7.restSpring.exception.FileNotFoundException;

public class NumberUtils {

    public static double convertNumberToDouble(String strNumber){
        if(strNumber == null || strNumber.isEmpty()){
            throw new FileNotFoundException("Please set a numeric value");
        }
        String number = strNumber.replace(",", ".");
        return Double.parseDouble(number);
    }

    public static boolean isNumeric(String strNumber){
        if(strNumber == null || strNumber.isEmpty()){
            return false;
        }
        String number = strNumber.replace(",",".");
        return number.matches("[-+]?[0-9]*\\.?[0-9]+");
    }

}
