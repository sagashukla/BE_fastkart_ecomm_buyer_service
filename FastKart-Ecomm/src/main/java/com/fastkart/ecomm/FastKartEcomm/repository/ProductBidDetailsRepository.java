package com.fastkart.ecomm.FastKartEcomm.repository;

import com.fastkart.ecomm.FastKartEcomm.entity.ProductWithBids;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductBidDetailsRepository extends CrudRepository<ProductWithBids, Integer> {
    @Query(
            value = "SELECT * FROM fastkart.product LEFT JOIN fastkart.bid  ON fastkart.product.id = fastkart.bid.product_id WHERE fastkart.product.id = ?1",
            nativeQuery = true)
    public List<ProductWithBids> getProductsBySeller(int productId);
}
