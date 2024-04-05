package com.example.Dodo.dao;

import com.example.Dodo.base.BaseRepository;
import com.example.Dodo.model.entity.Order;
import com.example.Dodo.model.response.OrderListResponse;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Repository
public interface OrderRepository extends BaseRepository<Order> {

}
