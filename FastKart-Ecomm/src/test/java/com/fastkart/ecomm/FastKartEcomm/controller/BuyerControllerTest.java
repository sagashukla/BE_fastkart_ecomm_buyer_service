package com.fastkart.ecomm.FastKartEcomm.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fastkart.ecomm.FastKartEcomm.dto.AuthenticationRequest;
import com.fastkart.ecomm.FastKartEcomm.dto.BidRequest;
import com.fastkart.ecomm.FastKartEcomm.entity.Product;
import com.fastkart.ecomm.FastKartEcomm.entity.ProductWithBids;
import com.fastkart.ecomm.FastKartEcomm.service.BidService;
import com.fastkart.ecomm.FastKartEcomm.service.ProductService;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
        Product product = Product
                .builder()
                .id(1)
                .minBidAmount(5f)
                .name("iPhone 8")
                .category("Mobile")
                .description("Mobile")
                .sellerId(2)
                .build();

        Product product2 = Product
                .builder()
                .id(2)
                .minBidAmount(5f)
                .name("iPhone 8")
                .category("Mobile")
                .description("Mobile")
                .sellerId(2)
                .build();

        Product product3 = Product
                .builder()
                .id(3)
                .minBidAmount(5f)
                .name("iPhone 8")
                .category("Mobile")
                .description("Mobile")
                .sellerId(2)
                .build();

        List<Product> productList = new ArrayList<>();
        productList.add(product);
        productList.add(product2);
        productList.add(product3);

        when(productService.getAllProducts()).thenReturn(productList);
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
        ProductWithBids productWithBids = ProductWithBids
                .builder()
                .bidId(1)
                .productId(1)
                .minimumBidAmount(10f)
                .category("mobile")
                .amount(10f)
                .name("iphone 8")
                .build();

        ProductWithBids productWithBids1 = ProductWithBids
                .builder()
                .bidId(1)
                .productId(1)
                .minimumBidAmount(10f)
                .category("mobile")
                .amount(10f)
                .name("iphone 8")
                .build();

        ProductWithBids productWithBids2 = ProductWithBids
                .builder()
                .bidId(1)
                .productId(1)
                .minimumBidAmount(10f)
                .category("mobile")
                .amount(10f)
                .name("iphone 8")
                .build();

        List<ProductWithBids> productWithBidsList = new ArrayList<>();
        productWithBidsList.add(productWithBids);
        productWithBidsList.add(productWithBids1);
        productWithBidsList.add(productWithBids2);

        when(productService.getProductWithBids(2)).thenReturn(productWithBidsList);
        assertEquals(3, buyerController.getProductWithBids(2).getBody().size());
    }
}