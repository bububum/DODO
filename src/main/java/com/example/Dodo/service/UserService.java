package com.example.Dodo.service;

import com.example.Dodo.base.BaseService;
import com.example.Dodo.model.DTO.UserDTO;
import com.example.Dodo.model.request.UserCreateRequest;
import org.springframework.stereotype.Service;

public interface UserService extends BaseService<UserDTO> {
    String create(UserCreateRequest request, Integer languageOrdinalq);

}
