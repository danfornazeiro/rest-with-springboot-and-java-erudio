package com.frnz7.restSpring.helpers;

public class IsNumeric {

    public static boolean isNumeric(String strNumber){
        if(strNumber == null || strNumber.isEmpty()){
            return false;
        }
        String number = strNumber.replace(",",".");
        return number.matches("[-+]?[0-9]*\\.?[0-9]+");
    }

}
