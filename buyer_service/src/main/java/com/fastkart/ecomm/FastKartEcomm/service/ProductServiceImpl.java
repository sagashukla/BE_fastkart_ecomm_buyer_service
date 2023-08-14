package com.fastkart.ecomm.FastKartEcomm.service;

import com.fastkart.ecomm.FastKartEcomm.exception.BuyerException;
import com.fastkart.ecomm.FastKartEcomm.projection.ProductWithBids;
import com.fastkart.ecomm.FastKartEcomm.projection.ProductWithMaxBid;
import com.fastkart.ecomm.FastKartEcomm.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<ProductWithMaxBid> getAllProducts() {
        log.info("Inside ProductServiceImpl");
        log.info("Inside getAllProducts()");
        List<ProductWithMaxBid> productList = productRepository.findAllProductWithMaxBidAmount();
        log.info("Product list {}", productList);
        Collections.sort(productList, (product1, product2) -> product2.getCreatedAt().compareTo(product1.getCreatedAt()));
        return productList;
    }

    @Override
    public List<ProductWithBids> getProductWithBids(Integer productId) {
        log.info("Inside ProductServiceImpl");
        log.info("Inside getProductWithBids()");
        if(productId <= 0){
            log.info("Wrong product id");
            throw new BuyerException("Wrong product id");
        }
        List<ProductWithBids> productWithBids = productRepository.getProductsBySeller(productId);
        log.info("Product with bids list {}", productWithBids);
        Collections.sort(productWithBids, (product1, product2) -> product2.getBidCreatedAt().compareTo(product1.getBidCreatedAt()));
        return productWithBids;
    }
}
