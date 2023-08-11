package com.fastkart.ecomm.FastKartEcomm.projection;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class ProductWithBidsImpl implements ProductWithBids{
    private Integer id;
    private String name;
    private String description;
    private String categoryName;
    private String sellerName;
    private float minimumBidAmount;
    private float bidAmount;
    private Long bidCreatedAt;
    private String bidderName;
    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getCategoryName() {
        return categoryName;
    }

    @Override
    public String getSellerName() {
        return sellerName;
    }

    @Override
    public float getMinimumBidAmount() {
        return minimumBidAmount;
    }

    @Override
    public float getBidAmount() {
        return 0;
    }

    @Override
    public Long getBidCreatedAt() {
        return bidCreatedAt;
    }

    @Override
    public String getBidderName() {
        return bidderName;
    }
}
