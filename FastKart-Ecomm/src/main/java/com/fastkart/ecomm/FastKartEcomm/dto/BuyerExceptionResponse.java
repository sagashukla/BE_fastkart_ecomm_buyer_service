package com.fastkart.ecomm.FastKartEcomm.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BuyerExceptionResponse {
    private int status;
    private String message;
    private long timeStamp;
}
