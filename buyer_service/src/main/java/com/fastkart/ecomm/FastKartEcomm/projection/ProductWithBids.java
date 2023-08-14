package com.fastkart.ecomm.FastKartEcomm.projection;

public interface ProductWithBids {
    Integer getId();
    String getName();
    String getDescription();

    String getCategoryName();

    String getSellerName();

    float getMinimumBidAmount();

    float getBidAmount();

    Long getBidCreatedAt();

    String getBidderName();

}
