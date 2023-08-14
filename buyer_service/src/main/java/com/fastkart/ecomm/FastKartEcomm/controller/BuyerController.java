package com.fastkart.ecomm.FastKartEcomm.controller;

import com.fastkart.ecomm.FastKartEcomm.dto.BidRequest;
import com.fastkart.ecomm.FastKartEcomm.projection.ProductWithBids;
import com.fastkart.ecomm.FastKartEcomm.projection.ProductWithMaxBid;
import com.fastkart.ecomm.FastKartEcomm.service.BidService;
import com.fastkart.ecomm.FastKartEcomm.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Component
@RequestMapping("/api/v1/buyer")
@Slf4j
public class BuyerController {

    @Autowired
    private BidService bidService;

    @Autowired
    private ProductService productService;
    @GetMapping("/products")
    public ResponseEntity<List<ProductWithMaxBid>> getAllProducts(){
        log.info("Inside buyer controller");
        log.info("Endpoint called: /products");
        log.info("Verb: GET");
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @PostMapping("product/bid")
    public ResponseEntity<String> submitBid(@RequestBody BidRequest request){
        log.info("Inside buyer controller");
        log.info("Endpoint called: /products");
        log.info("Verb: POST");
        log.info("Request: {}", request);
        return new ResponseEntity<>(bidService.submitBit(request), HttpStatus.OK);
    }

    @GetMapping("/product")
    public ResponseEntity<List<ProductWithBids>> getProductWithBids(@RequestParam("productId") Integer productId){
        log.info("Inside buyer controller");
        log.info("Endpoint called: /product");
        log.info("Verb: GET");
        log.info("productId: {}", productId);
        return new ResponseEntity<>(productService.getProductWithBids(productId), HttpStatus.OK);
    }
}
