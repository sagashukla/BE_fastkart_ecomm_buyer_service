package com.fastkart.ecomm.FastKartEcomm.repository;

import com.fastkart.ecomm.FastKartEcomm.entity.Bid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BidRepository extends CrudRepository<Bid, Integer> {
    @Query(
            value = "SELECT * FROM fastkart.bid where product_id = ?1",
            nativeQuery = true)
    public List<Bid> getBids(int sellerId);
}

