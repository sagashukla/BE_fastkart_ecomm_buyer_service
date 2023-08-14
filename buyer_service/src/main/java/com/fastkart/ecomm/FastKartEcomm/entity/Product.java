package com.fastkart.ecomm.FastKartEcomm.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
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

    @Column(name = "seller_id")
    private Integer sellerId;

    @Column(name = "created_at")
    private Long createAt;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;
}