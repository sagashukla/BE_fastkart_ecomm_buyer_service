package com.fastkart.ecomm.FastKartEcomm.repository;

import com.fastkart.ecomm.FastKartEcomm.entity.Bid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BidRepository extends JpaRepository<Bid, Integer> {
}
