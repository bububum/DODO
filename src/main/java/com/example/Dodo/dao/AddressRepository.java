package com.example.Dodo.dao;

import com.example.Dodo.base.BaseRepository;
import com.example.Dodo.model.entity.Address;
import com.example.Dodo.model.entity.User;
import com.example.Dodo.model.response.AddressListResponse;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends BaseRepository<Address> {

    @Query("select a.id as id, concat(a.street,',',a.num ) " +
            "as address from Address a where a.user.id=:userId")
    List<AddressListResponse> findAddressList(Long userId);
}
