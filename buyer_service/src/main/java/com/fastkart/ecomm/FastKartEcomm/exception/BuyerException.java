package com.fastkart.ecomm.FastKartEcomm.exception;

public class BuyerException extends RuntimeException{
    public BuyerException(String message) {
        super(message);
    }

    public BuyerException(String message, Throwable cause) {
        super(message, cause);
    }

    public BuyerException(Throwable cause) {
        super(cause);
    }
}
