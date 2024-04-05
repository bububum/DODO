package com.example.Dodo.dao;

import com.example.Dodo.base.BaseRepository;
import com.example.Dodo.model.entity.ProductSize;
import com.example.Dodo.model.response.ProductListResponse;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductSizeRepository extends BaseRepository<ProductSize> {


}
