package com.fastkart.ecomm.FastKartEcomm.utils;

public class UtilsVerifyNumber {
    public static boolean validateAmount(float amount){
        if(amount == 0 || amount < 0){
            return true;
        }
        return false;
    }

    public static boolean validateId(int id){
        if(id == 0 || id < 0){
            return true;
        }
        return false;
    }
}
