package com.fastkart.ecomm.FastKartEcomm.service;

import com.fastkart.ecomm.FastKartEcomm.entity.Product;
import com.fastkart.ecomm.FastKartEcomm.entity.ProductWithBids;
import com.fastkart.ecomm.FastKartEcomm.repository.ProductBidDetails;
import com.fastkart.ecomm.FastKartEcomm.repository.ProductDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductDetailsRepository productDetailsRepository;

    @Autowired
    private ProductBidDetails productBidDetails;
    @Override
    public List<Product> getAllProducts() {
        List<Product> productList = productDetailsRepository.findAll();
        return productList;
    }

    @Override
    public List<ProductWithBids> getProductWithBids(Integer productId) {
        List<ProductWithBids> productWithBidsList = productBidDetails.getProductsBySeller(productId);
        return productWithBidsList;
    }
}
