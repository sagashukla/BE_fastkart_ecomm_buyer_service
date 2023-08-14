package com.fastkart.ecomm.FastKartEcomm.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Bid")
public class Bid {
    @Id
    @GeneratedValue
    @Column(name = "bid_id")
    private Integer id;

    @Column(name = "amount")
    private Float amount;

    @Column(name = "product_id")
    private Integer productId;

    @Column(name = "buyer_id")
    private Integer buyerId;

    @Column(name = "created_at")
    private Long bidCreatedAt;
}
