package com.example.Dodo.service;

import com.example.Dodo.base.BaseService;
import com.example.Dodo.model.DTO.AccountDTO;
import com.example.Dodo.model.DTO.UserDTO;
import com.example.Dodo.model.request.AuthRequest;

public interface UserService extends BaseService<UserDTO> {
    UserDTO findByAccount(Long accountId);
    String sendAd(Long userId);
}
