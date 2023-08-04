package com.fastkart.ecomm.FastKartEcomm.controller;

import com.fastkart.ecomm.FastKartEcomm.dto.BidRequest;
import com.fastkart.ecomm.FastKartEcomm.entity.Product;
import com.fastkart.ecomm.FastKartEcomm.entity.ProductWithBids;
import com.fastkart.ecomm.FastKartEcomm.repository.ProductBidDetails;
import com.fastkart.ecomm.FastKartEcomm.service.BidService;
import com.fastkart.ecomm.FastKartEcomm.service.ProductService;
import com.fastkart.ecomm.FastKartEcomm.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Component
@RequestMapping("/api/v1/buyer")
public class BuyerController {

    @Autowired
    private BidService bidService;

    @Autowired
    private ProductService productService;
    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts(){
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @PostMapping("product/bid")
    public ResponseEntity<String> submitBid(@RequestBody BidRequest request){
        return new ResponseEntity<>(bidService.submitBit(request), HttpStatus.OK);
    }

    @GetMapping("/product")
    public ResponseEntity<List<ProductWithBids>> getProductWithBids(@RequestParam("productId") Integer productId){
        return new ResponseEntity<>(productService.getProductWithBids(productId), HttpStatus.OK);

    }
}
