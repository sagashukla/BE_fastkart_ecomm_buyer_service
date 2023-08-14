package com.fastkart.ecomm.FastKartEcomm.service;

import com.fastkart.ecomm.FastKartEcomm.entity.Bid;
import com.fastkart.ecomm.FastKartEcomm.entity.Category;
import com.fastkart.ecomm.FastKartEcomm.entity.Product;
import com.fastkart.ecomm.FastKartEcomm.exception.BuyerException;
import com.fastkart.ecomm.FastKartEcomm.exception.ProductException;
import com.fastkart.ecomm.FastKartEcomm.projection.ProductWithBids;
import com.fastkart.ecomm.FastKartEcomm.projection.ProductWithBidsImpl;
import com.fastkart.ecomm.FastKartEcomm.projection.ProductWithMaxBid;
import com.fastkart.ecomm.FastKartEcomm.projection.ProductWithMaxBidImpl;
import com.fastkart.ecomm.FastKartEcomm.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductServiceImplTest {
    @MockBean
    private ProductRepository productRepository;

    @MockBean
    private BidService bidService;

    @Autowired
    private ProductService productService;

    @Test
    void getAllProductsSuccess_Test() {

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

        when(productRepository.findAllProductWithMaxBidAmount()).thenReturn(products);
        assertEquals(2, productService.getAllProducts().size());
    }

    @Test
    void getProductWithBidsSuccess_Test() {

        ProductWithBids productWithBids = ProductWithBidsImpl
                .builder()
                .bidAmount(10f)
                .bidderName("Buyer")
                .minimumBidAmount(1f)
                .sellerName("Seller")
                .bidCreatedAt(System.currentTimeMillis())
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
                .bidCreatedAt(System.currentTimeMillis())
                .id(1)
                .name("Car")
                .build();

        List<ProductWithBids> products = new ArrayList<>();
        products.add(productWithBids);
        products.add(productWithBids1);

        when(productRepository.getProductsBySeller(1)).thenReturn(products);

        assertEquals(2, productService.getProductWithBids(1).size());
    }

    @Test
    void getProductWithBidsFailProductIdDoesNotExist_Test() {
        int productId = -1;

        Throwable exception = assertThrows(BuyerException.class, () -> productService.getProductWithBids(productId));
        assertEquals("Wrong product id", exception.getMessage());
    }
}