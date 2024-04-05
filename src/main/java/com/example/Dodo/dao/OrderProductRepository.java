package com.example.Dodo.dao;

import com.example.Dodo.base.BaseRepository;
import com.example.Dodo.model.entity.OrderProduct;
import com.example.Dodo.model.request.ProductListForRepeat;
import com.example.Dodo.model.request.ProductOrderList;
import com.example.Dodo.model.response.OrderHistoryResponse;
import com.example.Dodo.model.response.OrderListResponse;
import com.example.Dodo.model.response.ProductListResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderProductRepository extends BaseRepository<OrderProduct> {

    @Query(value = "SELECT op.id, op.created_date, op.status, op.updated_date, op.order_id, op.product_size_id " +
            "FROM tb_order_product op " +
            "INNER JOIN tb_order o ON o.id = op.order_id " +
            "WHERE o.id = :orderId", nativeQuery = true)

    List<OrderProduct> findByOrderId(Long orderId);

    @Query(value = "SELECT p.name as name, p.id as productId, s.name as size FROM tb_order_product op\n" +
            "            JOIN tb_product_size ps ON ps.id = op.product_size_id\n" +
            "            JOIN tb_product p ON p.id = ps.product_id\n" +
            "            JOIN tb_size s ON s.id = ps.size_id\n" +
            "            WHERE op.order_id =:orderId", nativeQuery = true)
    List<ProductListResponse> historyOfProductsByOrder(Long orderId);

    @Query(value = "SELECT o.id AS id, o.order_date AS orderDate, o.price AS price, CONCAT(a.street, ',', a.num) as address,\n" +
            "            p.id as productId, p.name as name, s.name as size\n" +
            "            FROM tb_order_product op\n" +
            "            JOIN tb_order o ON o.id = op.order_id\n" +
            "            JOIN tb_address a ON o.address_id = a.id\n" +
            "            JOIN tb_product_size ps ON op.product_size_id = ps.id\n" +
            "            JOIN tb_product p ON ps.product_id = p.id\n" +
            "            JOIN tb_size s ON ps.size_id = s.id\n" +
            "            WHERE o.user_id = :userId " +
            "ORDER BY o.id DESC;",
            nativeQuery = true)
    List<OrderListResponse> historyOfOrders(Long userId);

    @Query(value = "SELECT ps.id as id, ps.price as price, o.payment_type as paymentType, o.order_date as orderDate," +
            "a.id as addressId " +
            "FROM tb_order_product op " +
            "JOIN tb_product_size ps ON ps.id = op.product_size_id " +
            "JOIN tb_order o ON op.order_id = o.id " +
            "JOIN tb_address a ON a.id = o.address_id " +
            "WHERE op.order_id =:orderId", nativeQuery = true)
    List<ProductListForRepeat> findProductsForRepeat(Long orderId);
}
