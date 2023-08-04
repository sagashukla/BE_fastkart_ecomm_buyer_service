package com.fastkart.ecomm.FastKartEcomm.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class BidRequest {
    private Integer productId;
    private Float bidAmount;
}
