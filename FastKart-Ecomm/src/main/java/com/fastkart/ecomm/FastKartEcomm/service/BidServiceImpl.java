package com.fastkart.ecomm.FastKartEcomm.service;

import com.fastkart.ecomm.FastKartEcomm.dto.BidRequest;
import com.fastkart.ecomm.FastKartEcomm.entity.Bid;
import com.fastkart.ecomm.FastKartEcomm.entity.Product;
import com.fastkart.ecomm.FastKartEcomm.exception.BuyerException;
import com.fastkart.ecomm.FastKartEcomm.repository.BidRepository;
import com.fastkart.ecomm.FastKartEcomm.repository.ProductBidDetails;
import com.fastkart.ecomm.FastKartEcomm.repository.ProductDetailsRepository;
import com.fastkart.ecomm.FastKartEcomm.utils.UtilsVerifyNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BidServiceImpl implements BidService{

    @Autowired
    private BidRepository bidRepository;

    @Autowired
    private ProductDetailsRepository productDetailsRepository;

    @Override
    public String submitBit(BidRequest request) {

        Integer productId = request.getProductId();
        Float bidAmount = request.getBidAmount();

        if(UtilsVerifyNumber.validateAmount(bidAmount)){
            throw new BuyerException("Not a valid bid amount");
        }

        if(UtilsVerifyNumber.validateId(productId)){
            throw new BuyerException("Not a valid product id");
        }

        Optional<Product> product = productDetailsRepository.findById(productId);
        if(product.isEmpty()){
            throw new BuyerException("Product does not exist");
        }

        Float productMinBidAmount = product.get().getMinBidAmount();

        if(bidAmount < productMinBidAmount){
            throw new BuyerException("Product min bid amount is " + productMinBidAmount);
        }

        Bid bid = Bid
                .builder()
                .productId(productId)
                .amount(bidAmount)
                .build();

        bidRepository.save(bid);

        return "Bid has been submitted.";
    }
}
