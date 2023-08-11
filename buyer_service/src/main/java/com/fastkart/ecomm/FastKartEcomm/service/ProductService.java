package com.fastkart.ecomm.FastKartEcomm.service;

import com.fastkart.ecomm.FastKartEcomm.projection.ProductWithBids;
import com.fastkart.ecomm.FastKartEcomm.projection.ProductWithMaxBid;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    public List<ProductWithMaxBid> getAllProducts();

    public List<ProductWithBids> getProductWithBids(Integer productId);
}
