package com.example.Dodo.service.impl;

import com.example.Dodo.base.BaseServiceImpl;
import com.example.Dodo.dao.SizeRepository;
import com.example.Dodo.exception.IncorrectRequest;
import com.example.Dodo.mapper.SizeMapper;
import com.example.Dodo.model.DTO.SizeDTO;
import com.example.Dodo.model.entity.Size;
import com.example.Dodo.model.request.SizeCreateRequest;
import com.example.Dodo.service.SizeService;
import com.example.Dodo.util.Language;
import com.example.Dodo.util.ResourceBundleLanguage;
import org.springframework.stereotype.Service;

@Service
public class SizeServiceImpl extends BaseServiceImpl<Size, SizeRepository, SizeDTO, SizeMapper> implements SizeService {
    public SizeServiceImpl(SizeRepository rep, SizeMapper mapper) {
        super(rep, mapper);
    }

    @Override
    public String create(SizeCreateRequest request, Integer languageOrdinal) {

        Language language = Language.getLanguage(languageOrdinal);

        if (request != null) {

            SizeDTO sizeDTO = new SizeDTO();
            sizeDTO.setName(request.getName());

            save(sizeDTO);

            return "Success";
        } else {
            throw new IncorrectRequest(ResourceBundleLanguage.periodMessage(language, "incorrectRequest"));
        }
    }
}
