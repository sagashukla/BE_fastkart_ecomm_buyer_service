package com.fastkart.ecomm.FastKartEcomm.projection;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class ProductWithMaxBidImpl implements ProductWithMaxBid{
    private Integer id;
    private String name;
    private Long createdAt;
    private String categoryName;
    private float maxBidAmount;
    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Long getCreatedAt() {
        return createdAt;
    }

    @Override
    public String getCategoryName() {
        return categoryName;
    }

    @Override
    public float getMaxBidAmount() {
        return 0;
    }
}
