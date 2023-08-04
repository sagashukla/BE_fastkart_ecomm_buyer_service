package com.fastkart.ecomm.FastKartEcomm.service;

import com.fastkart.ecomm.FastKartEcomm.entity.Product;
import com.fastkart.ecomm.FastKartEcomm.entity.ProductWithBids;
import com.fastkart.ecomm.FastKartEcomm.exception.BuyerException;
import com.fastkart.ecomm.FastKartEcomm.repository.ProductBidDetailsRepository;
import com.fastkart.ecomm.FastKartEcomm.repository.ProductDetailsRepository;
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
    private ProductDetailsRepository productDetailsRepository;

    @MockBean
    private ProductBidDetailsRepository productBidDetailsRepository;

    @Autowired
    private ProductService productService;
    @Test
    void getAllProductsSuccess_Test() {

        Product product = Product
                .builder()
                .id(1)
                .name("Jordan 555")
                .description("Jordan 555")
                .minBidAmount(10f)
                .category("Shoes")
                .sellerId(1)
                .build();

        Product product2 = Product
                .builder()
                .id(2)
                .name("Jordan 555")
                .description("Jordan 555")
                .minBidAmount(11f)
                .category("Shoes")
                .sellerId(1)
                .build();

        List<Product> productList = new ArrayList<>();
        productList.add(product);
        productList.add(product2);

        when(productDetailsRepository.findAll()).thenReturn(productList);
        assertEquals(2, productService.getAllProducts().size());
    }

    @Test
    void getProductWithBidsSuccess_Test() {
        int productId = 1;
        Product product = Product
                .builder()
                .id(productId)
                .minBidAmount(5f)
                .name("iPhone 8")
                .category("Mobile")
                .description("Mobile")
                .sellerId(2)
                .build();
        ProductWithBids productWithBids = ProductWithBids
                .builder()
                .id(203)
                .category("Mobiles")
                .description("iPhone 8")
                .minimumBidAmount(100f)
                .name("iPhone 8")
                .bidId(104)
                .sellerId(20)
                .amount(600f)
                .productId(203)
                .build();

        ProductWithBids productWithBids1 = ProductWithBids
                .builder()
                .id(203)
                .category("Mobiles")
                .description("iPhone 8")
                .minimumBidAmount(100f)
                .name("iPhone 8")
                .bidId(104)
                .sellerId(20)
                .amount(700f)
                .productId(203)
                .build();

        List<ProductWithBids> productWithBidsList = new ArrayList<>();
        productWithBidsList.add(productWithBids);
        productWithBidsList.add(productWithBids1);

        when(productDetailsRepository.findById(productId)).thenReturn(Optional.ofNullable(product));
        when(productBidDetailsRepository.getProductsBySeller(productId)).thenReturn(productWithBidsList);

        assertNotNull(productService.getProductWithBids(productId));
    }

    @Test
    void getProductWithBidsFailProductIdDoesNotExist_Test() {
        int productId = 1;
        Product product = Product
                .builder()
                .id(productId)
                .minBidAmount(5f)
                .name("iPhone 8")
                .category("Mobile")
                .description("Mobile")
                .sellerId(2)
                .build();
        ProductWithBids productWithBids = ProductWithBids
                .builder()
                .id(203)
                .category("Mobiles")
                .description("iPhone 8")
                .minimumBidAmount(100f)
                .name("iPhone 8")
                .bidId(104)
                .sellerId(20)
                .amount(600f)
                .productId(203)
                .build();

        ProductWithBids productWithBids1 = ProductWithBids
                .builder()
                .id(203)
                .category("Mobiles")
                .description("iPhone 8")
                .minimumBidAmount(100f)
                .name("iPhone 8")
                .bidId(104)
                .sellerId(20)
                .amount(700f)
                .productId(203)
                .build();

        List<ProductWithBids> productWithBidsList = new ArrayList<>();
        productWithBidsList.add(productWithBids);
        productWithBidsList.add(productWithBids1);

        when(productDetailsRepository.findById(productId)).thenReturn(Optional.empty());
        when(productBidDetailsRepository.getProductsBySeller(productId)).thenReturn(productWithBidsList);

        Throwable exception = assertThrows(BuyerException.class, () -> productService.getProductWithBids(productId));
        assertEquals("Product does not exist", exception.getMessage());
    }
}