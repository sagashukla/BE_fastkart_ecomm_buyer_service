package com.fastkart.ecomm.FastKartEcomm.projection;

public interface ProductWithMaxBid {
    Integer getId();
    String getName();
    Long getCreatedAt();
    String getCategoryName();
    float getMaxBidAmount();
}
