package com.fastkart.ecomm.FastKartEcomm.exception;

import com.fastkart.ecomm.FastKartEcomm.dto.BuyerExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BuyerExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<BuyerExceptionResponse> handleException(AuthorizationException exc){
        var errorResponse = BuyerExceptionResponse
                .builder()
                .status(HttpStatus.UNAUTHORIZED.value())
                .message(exc.getMessage())
                .timeStamp(System.currentTimeMillis())
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler
    public ResponseEntity<BuyerExceptionResponse> handleException(BuyerException exc){
        var errorResponse = BuyerExceptionResponse
                .builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message(exc.getMessage())
                .timeStamp(System.currentTimeMillis())
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
