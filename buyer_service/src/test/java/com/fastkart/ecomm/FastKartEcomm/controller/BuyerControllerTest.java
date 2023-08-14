package com.fastkart.ecomm.FastKartEcomm.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fastkart.ecomm.FastKartEcomm.dto.BidRequest;
import com.fastkart.ecomm.FastKartEcomm.entity.Bid;
import com.fastkart.ecomm.FastKartEcomm.entity.Category;
import com.fastkart.ecomm.FastKartEcomm.entity.Product;
import com.fastkart.ecomm.FastKartEcomm.projection.ProductWithBids;
import com.fastkart.ecomm.FastKartEcomm.projection.ProductWithBidsImpl;
import com.fastkart.ecomm.FastKartEcomm.projection.ProductWithMaxBid;
import com.fastkart.ecomm.FastKartEcomm.projection.ProductWithMaxBidImpl;
import com.fastkart.ecomm.FastKartEcomm.service.BidService;
import com.fastkart.ecomm.FastKartEcomm.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
class BuyerControllerTest {
    @MockBean
    private BidService bidService;
    @MockBean
    private ProductService productService;

    @Autowired
    private BuyerController buyerController;

    private static ObjectMapper mapper = new ObjectMapper();
    @Test
    void getAllProductsSuccess_Test() throws Exception {

        ProductWithMaxBid productWithMaxBid = ProductWithMaxBidImpl
                .builder()
                .maxBidAmount(100f)
                .createdAt(System.currentTimeMillis())
                .categoryName("Category")
                .name("Car")
                .id(1)
                .build();

        ProductWithMaxBid productWithMaxBid1 = ProductWithMaxBidImpl
                .builder()
                .maxBidAmount(100f)
                .createdAt(System.currentTimeMillis())
                .categoryName("Category")
                .name("Car")
                .id(1)
                .build();

        List<ProductWithMaxBid> products = new ArrayList<>();
        products.add(productWithMaxBid);
        products.add(productWithMaxBid1);

        when(productService.getAllProducts()).thenReturn(products);
        assertNotNull(buyerController.getAllProducts());
    }
    @Test
    void submitBid() {
        BidRequest request = BidRequest
                .builder()
                .productId(2)
                .bidAmount(10f)
                .build();

        when(bidService.submitBit(request)).thenReturn("Bid has been submitted.");
        assertEquals("Bid has been submitted.", buyerController.submitBid(request).getBody());
    }

    @Test
    void getProductWithBids() {
        ProductWithBids productWithBids = ProductWithBidsImpl
                .builder()
                .bidAmount(10f)
                .bidderName("Buyer")
                .minimumBidAmount(1f)
                .sellerName("Seller")
                .categoryName("Category")
                .id(1)
                .name("Car")
                .build();

        ProductWithBids productWithBids1 = ProductWithBidsImpl
                .builder()
                .bidAmount(10f)
                .bidderName("Buyer")
                .minimumBidAmount(1f)
                .sellerName("Seller")
                .categoryName("Category")
                .id(1)
                .name("Car")
                .build();

        List<ProductWithBids> products = new ArrayList<>();
        products.add(productWithBids);
        products.add(productWithBids1);


        when(productService.getProductWithBids(2)).thenReturn(products);
        assertEquals(2, buyerController.getProductWithBids(2).getBody().size());
    }
}