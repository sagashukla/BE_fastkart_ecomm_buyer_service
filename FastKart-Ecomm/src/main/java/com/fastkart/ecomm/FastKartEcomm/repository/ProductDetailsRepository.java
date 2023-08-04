package com.fastkart.ecomm.FastKartEcomm.repository;

import com.fastkart.ecomm.FastKartEcomm.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDetailsRepository extends JpaRepository<Product, Integer> {
}
