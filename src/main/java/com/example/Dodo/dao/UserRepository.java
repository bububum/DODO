package com.example.Dodo.dao;

import com.example.Dodo.base.BaseRepository;
import com.example.Dodo.model.DTO.AccountDTO;
import com.example.Dodo.model.entity.Account;
import com.example.Dodo.model.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseRepository<User> {

    User findByAccount(Account account);
}
