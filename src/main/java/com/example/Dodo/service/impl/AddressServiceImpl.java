package com.example.Dodo.service.impl;

import com.example.Dodo.base.BaseServiceImpl;
import com.example.Dodo.dao.AddressRepository;
import com.example.Dodo.exception.IncorrectRequest;
import com.example.Dodo.mapper.AddressMapper;
import com.example.Dodo.model.DTO.AddressDTO;
import com.example.Dodo.model.DTO.UserDTO;
import com.example.Dodo.model.entity.Address;
import com.example.Dodo.model.request.AddressCreateRequest;
import com.example.Dodo.service.AddressService;
import com.example.Dodo.service.UserService;
import com.example.Dodo.util.JwtProvider;
import com.example.Dodo.util.Language;
import com.example.Dodo.util.ResourceBundleLanguage;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.JwtException;



@Service
public class AddressServiceImpl extends BaseServiceImpl<Address, AddressRepository, AddressDTO, AddressMapper> implements AddressService {
    public AddressServiceImpl(AddressRepository rep, AddressMapper mapper, JwtProvider jwtProvider, UserService userService) {
        super(rep, mapper);
        this.jwtProvider = jwtProvider;
        this.userService = userService;
    }

    private final JwtProvider jwtProvider;
    private final UserService userService;

    @Override
    public String create(AddressCreateRequest request, Integer languageOrdinal, String token) {

        Language language = Language.getLanguage(languageOrdinal);

        Long userId;
        try {
            userId = jwtProvider.validateToken(token);
        } catch (JwtException e) {
            throw new IncorrectRequest(ResourceBundleLanguage.periodMessage(language, "invalidToken"));
        }

            if (request != null) {

                AddressDTO addressDTO = new AddressDTO();
                addressDTO.setComment(request.getComment());
                addressDTO.setNum(request.getNum());
                addressDTO.setStreet(request.getStreet());
                UserDTO userDTO = userService.findById(userId);
                addressDTO.setUser(userDTO);

                save(addressDTO);

            } else {
                throw new IncorrectRequest(ResourceBundleLanguage.periodMessage(language, "incorrectRequest"));
            }
            return "Success";
    }
}
