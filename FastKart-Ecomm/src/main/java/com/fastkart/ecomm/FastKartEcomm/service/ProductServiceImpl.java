package com.fastkart.ecomm.FastKartEcomm.service;

import com.fastkart.ecomm.FastKartEcomm.entity.Product;
import com.fastkart.ecomm.FastKartEcomm.entity.ProductWithBids;
import com.fastkart.ecomm.FastKartEcomm.exception.BuyerException;
import com.fastkart.ecomm.FastKartEcomm.repository.ProductBidDetailsRepository;
import com.fastkart.ecomm.FastKartEcomm.repository.ProductDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductDetailsRepository productDetailsRepository;

    @Autowired
    private ProductBidDetailsRepository productBidDetailsRepository;
    @Override
    public List<Product> getAllProducts() {
        List<Product> productList = productDetailsRepository.findAll();
        return productList;
    }

    @Override
    public List<ProductWithBids> getProductWithBids(Integer productId) {
        Optional<Product> product = productDetailsRepository.findById(productId);
        if(product.isEmpty()){
            throw new BuyerException("Product does not exist");
        }
        List<ProductWithBids> productWithBidsList = productBidDetailsRepository.getProductsBySeller(productId);
        return productWithBidsList;
    }
}
