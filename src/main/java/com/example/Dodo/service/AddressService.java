package com.example.Dodo.service;

import com.example.Dodo.base.BaseService;
import com.example.Dodo.model.DTO.AddressDTO;
import com.example.Dodo.model.request.AddressCreateRequest;

public interface AddressService extends BaseService<AddressDTO> {

    String create(AddressCreateRequest request, Integer languageOrdinal, String token);
}
