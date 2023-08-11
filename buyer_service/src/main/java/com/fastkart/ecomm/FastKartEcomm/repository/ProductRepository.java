package com.fastkart.ecomm.FastKartEcomm.repository;

import com.fastkart.ecomm.FastKartEcomm.entity.Product;
import com.fastkart.ecomm.FastKartEcomm.projection.ProductWithBids;
import com.fastkart.ecomm.FastKartEcomm.projection.ProductWithMaxBid;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends CrudRepository<Product, Integer> {
    @Query(
            value = "WITH product_details AS ( SELECT p.id AS id, p.name as name, p.description as description, p.minimum_bid_amount AS minimumBidAmount, CONCAT(u.first_name, \" \", u.last_name) AS sellerName, cat.category_name AS categoryName FROM fastkart.product AS p INNER JOIN fastkart.user AS u ON p.seller_id = u.id INNER JOIN fastkart.category AS cat ON cat.category_id = p.category_id WHERE p.id = ?1 ), bid_details AS ( SELECT p.id AS b_product_id, b.amount AS bidAmount, b.created_at AS bidCreatedAt, CONCAT(u.first_name, \" \", u.last_name) AS bidderName FROM fastkart.product AS p INNER JOIN fastkart.bid AS b ON p.id = b.product_id INNER JOIN fastkart.user AS u ON u.id = b.buyer_id WHERE p.id = ?1 ) SELECT pd.id, pd.name, pd.description, pd.minimumBidAmount, pd.sellerName, pd.categoryName, COALESCE(bd.bidAmount, 0) AS bidAmount, COALESCE(bd.bidCreatedAt, 0) AS bidCreatedAt, COALESCE(bd.bidderName, 'Unknown') AS bidderName FROM product_details AS pd LEFT JOIN bid_details AS bd ON pd.id = bd.b_product_id",
            nativeQuery = true)
    public List<ProductWithBids> getProductsBySeller(int sellerId);

    @Query(
            value = "SELECT p.id as id, p.name as prdoct, p.created_at as createdAt, cat.category_name as categoryName, MAX(b.amount) as maxBidAmount FROM fastkart.product as p INNER JOIN fastkart.category as cat on cat.category_id = p.category_id INNER JOIN fastkart.bid as b on b.product_id = p.id GROUP BY p.id",
            nativeQuery = true)
    List<ProductWithMaxBid> findAllProductWithMaxBidAmount();


    Optional<Product> findById(Integer id);
}
