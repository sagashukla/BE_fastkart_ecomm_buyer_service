package com.fastkart.ecomm.FastKartEcomm.service;

import com.fastkart.ecomm.FastKartEcomm.dto.BidRequest;
import com.fastkart.ecomm.FastKartEcomm.entity.Bid;
import com.fastkart.ecomm.FastKartEcomm.entity.Product;
import com.fastkart.ecomm.FastKartEcomm.exception.BuyerException;
import com.fastkart.ecomm.FastKartEcomm.repository.BidRepository;
import com.fastkart.ecomm.FastKartEcomm.repository.ProductDetailsRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class BidServiceImplTest {

    @MockBean
    private BidRepository bidRepository;

    @MockBean
    private ProductDetailsRepository productDetailsRepository;

    @Autowired
    private BidServiceImpl bidService;

    @Test
    void submitBidSuccess_Test() {
        int productId = 2;
        BidRequest request = BidRequest
                .builder()
                .productId(2)
                .bidAmount(2F)
                .build();

        Product product = Product
                .builder()
                .id(productId)
                .name("Test")
                .category("Test")
                .description("Test")
                .sellerId(2)
                .build();

        Bid bid = Bid
                .builder()
                .productId(productId)
                .amount(2f)
                .build();

        when(productDetailsRepository.findById(productId)).thenReturn(Optional.ofNullable(product));
        when(bidRepository.save(bid)).thenReturn(bid);

        assertNotNull(bidService.submitBit(request));

    }

    @Test
    void submitBitFailNegativeBidAmount_Test() {
        int productId = 2;
        BidRequest request = BidRequest
                .builder()
                .productId(2)
                .bidAmount(-2F)
                .build();

        Product product = Product
                .builder()
                .id(productId)
                .name("iPhone 8")
                .category("Mobile")
                .description("Mobile")
                .sellerId(2)
                .build();

        Bid bid = Bid
                .builder()
                .productId(productId)
                .amount(-2f)
                .build();

        when(productDetailsRepository.findById(productId)).thenReturn(Optional.ofNullable(product));
        when(bidRepository.save(bid)).thenReturn(bid);

        Throwable exception = assertThrows(BuyerException.class, () -> bidService.submitBit(request));
        assertEquals("Not a valid bid amount", exception.getMessage());
    }

    @Test
    void submitBitFailNegativeProductId_Test() {
        int productId = -2;
        BidRequest request = BidRequest
                .builder()
                .productId(productId)
                .bidAmount(2F)
                .build();

        Product product = Product
                .builder()
                .id(productId)
                .name("iPhone 8")
                .category("Mobile")
                .description("Mobile")
                .sellerId(2)
                .build();

        Bid bid = Bid
                .builder()
                .productId(productId)
                .amount(2f)
                .build();

        when(productDetailsRepository.findById(productId)).thenReturn(Optional.ofNullable(product));
        when(bidRepository.save(bid)).thenReturn(bid);

        Throwable exception = assertThrows(BuyerException.class, () -> bidService.submitBit(request));
        assertEquals("Not a valid product id", exception.getMessage());
    }

    @Test
    void submitBitFailProductIdDoestNotExist_Test() {
        int productId = 2;
        BidRequest request = BidRequest
                .builder()
                .productId(productId)
                .bidAmount(2F)
                .build();

        Product product = Product
                .builder().build();

        Bid bid = Bid
                .builder()
                .productId(productId)
                .amount(2f)
                .build();

        when(productDetailsRepository.findById(productId)).thenReturn(Optional.empty());
        when(bidRepository.save(bid)).thenReturn(bid);

        Throwable exception = assertThrows(BuyerException.class, () -> bidService.submitBit(request));
        assertEquals("Product does not exist", exception.getMessage());
    }

    @Test
    void submitBitFailGivenBidAmountIsLessThanProductMinBidAmount_Test() {
        int productId = 2;
        BidRequest request = BidRequest
                .builder()
                .productId(productId)
                .bidAmount(2F)
                .build();

        Product product = Product
                .builder()
                .id(productId)
                .minBidAmount(5f)
                .name("iPhone 8")
                .category("Mobile")
                .description("Mobile")
                .sellerId(2)
                .build();

        Bid bid = Bid
                .builder()
                .productId(productId)
                .amount(2f)
                .build();

        when(productDetailsRepository.findById(productId)).thenReturn(Optional.ofNullable(product));
        when(bidRepository.save(bid)).thenReturn(bid);

        Throwable exception = assertThrows(BuyerException.class, () -> bidService.submitBit(request));
        assertEquals("Product min bid amount is 5.0", exception.getMessage());
    }
}