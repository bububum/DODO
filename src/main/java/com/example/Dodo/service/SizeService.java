package com.example.Dodo.service;

import com.example.Dodo.base.BaseService;
import com.example.Dodo.model.DTO.SizeDTO;
import com.example.Dodo.model.request.SizeCreateRequest;
import org.springframework.stereotype.Service;

public interface SizeService extends BaseService<SizeDTO> {

    String create(SizeCreateRequest request, Integer languageOrdinal);
}
