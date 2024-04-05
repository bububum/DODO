package com.example.Dodo.dao;

import com.example.Dodo.base.BaseRepository;
import com.example.Dodo.model.entity.Product;
import com.example.Dodo.model.response.ProductListResponse;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends BaseRepository<Product> {

    Product findByName(String name);

    @Query(value = "SELECT p.name as name, ps.id as productId, s.name as size FROM tb_product p " +
            "JOIN tb_product_size ps ON p.id = ps.product_id " +
            "JOIN tb_size s ON ps.size_id = s.id " +
            "WHERE (:sizeId IS NULL OR ps.size_id = :sizeId) " +
            "AND (:fromPrice IS NULL OR ps.price >= :fromPrice) " +
            "AND (:toPrice IS NULL OR ps.price <= :toPrice) " +
            "AND (:name IS NULL OR p.name LIKE CONCAT('%', :name, '%')) " +
            "AND (:categoryId IS NULL OR p.category_id = :categoryId)",
            nativeQuery = true)
    List<ProductListResponse> findByFilter(Long sizeId, BigDecimal fromPrice, BigDecimal toPrice, String name, Long categoryId);
}

