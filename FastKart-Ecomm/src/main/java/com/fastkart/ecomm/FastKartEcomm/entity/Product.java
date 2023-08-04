package com.fastkart.ecomm.FastKartEcomm.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Product")
public class Product {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "minimum_bid_amount")
    private float minBidAmount;

    @Column(name = "category")
    private String category;

    @Column(name = "seller_id")
    private Integer sellerId;
}