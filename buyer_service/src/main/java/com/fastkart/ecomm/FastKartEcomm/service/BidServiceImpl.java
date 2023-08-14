package com.fastkart.ecomm.FastKartEcomm.service;

import com.fastkart.ecomm.FastKartEcomm.dto.BidRequest;
import com.fastkart.ecomm.FastKartEcomm.entity.Bid;
import com.fastkart.ecomm.FastKartEcomm.entity.Product;
import com.fastkart.ecomm.FastKartEcomm.exception.BuyerException;
import com.fastkart.ecomm.FastKartEcomm.repository.BidRepository;
import com.fastkart.ecomm.FastKartEcomm.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class BidServiceImpl implements BidService{

    @Autowired
    private BidRepository bidRepository;

    @Autowired
    private ProductRepository productRepository;
    @Override
    public String submitBit(BidRequest request) {
        log.info("Inside BidServiceImpl");
        log.info("Inside submitBit()");
        if(request.getProductId() < 0){
            log.info("Not a valid product id");
            throw new BuyerException("Not a valid product id");
        }
        Optional<Product> product = productRepository.findById(request.getProductId());
        log.info("Fetch product {}", product.get());
        if(product.isEmpty()){
            log.info("Product does not exist");
            throw new BuyerException("Product does not exist");
        }

        if(product.get().getMinBidAmount() > request.getBidAmount()){
            log.info("Min bid amount is " + product.get().getMinBidAmount());
            throw new BuyerException("Min bid amount is " + product.get().getMinBidAmount());
        }
        Bid bid = Bid.builder()
                        .bidCreatedAt(System.currentTimeMillis())
                                .productId(request.getProductId())
                                        .amount(request.getBidAmount())
                .buyerId(request.getBuyerId())
                                                .build();
        bidRepository.save(bid);
        log.info("Bid has been submitted.");
        return "Bid has been submitted.";
    }
}
