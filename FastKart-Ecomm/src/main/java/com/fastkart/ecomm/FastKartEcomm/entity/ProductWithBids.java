package com.fastkart.ecomm.FastKartEcomm.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class ProductWithBids {
    @Column(name = "id")
    private Integer id;

    @Column(name = "category")
    private String category;

    @Column(name = "description")
    private String description;

    @Column(name = "minimum_bid_amount")
    private Float minimumBidAmount;

    @Column(name = "name")
    private String name;

    @Id
    @Column(name = "bid_id")
    private Integer bidId;

    @Column(name = "seller_id")
    private Integer sellerId;

    @Column(name = "amount")
    private Float amount;

    @Column(name = "product_id")
    private Integer productId;
}
