package com.example.Dodo.service;

import com.example.Dodo.base.BaseService;
import com.example.Dodo.model.DTO.AccountDTO;
import com.example.Dodo.model.entity.Account;
import com.example.Dodo.model.request.AuthRequest;
import com.example.Dodo.model.request.ValidateEmailRequest;

public interface AccountService extends BaseService<AccountDTO> {

    String authAccount(AuthRequest request, Integer languageOrdinal);
    AccountDTO findByEmail(String email);
    String generateRandomPassword(int length);
    String validateAccount(ValidateEmailRequest request, Integer languageOrdinal);
}
