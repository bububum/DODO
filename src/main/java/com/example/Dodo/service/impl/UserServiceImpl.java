package com.example.Dodo.service.impl;

import com.example.Dodo.base.BaseServiceImpl;
import com.example.Dodo.dao.UserRepository;
import com.example.Dodo.exception.IncorrectRequest;
import com.example.Dodo.mapper.AccountMapper;
import com.example.Dodo.mapper.UserMapper;
import com.example.Dodo.model.DTO.AccountDTO;
import com.example.Dodo.model.DTO.UserDTO;
import com.example.Dodo.model.entity.User;
import com.example.Dodo.model.request.AuthRequest;
import com.example.Dodo.service.UserService;
import com.example.Dodo.util.Language;
import com.example.Dodo.util.ResourceBundleLanguage;
import jakarta.mail.MessagingException;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class UserServiceImpl extends BaseServiceImpl<User, UserRepository, UserDTO, UserMapper> implements UserService {
    public UserServiceImpl(UserRepository rep, UserMapper mapper, MailService mailService, AccountMapper accountMapper) {
        super(rep, mapper);
        this.mailService = mailService;
        this.accountMapper = accountMapper;
    }

    private final MailService mailService;
    private final AccountMapper accountMapper;

    @Override
    public UserDTO findByAccount(AccountDTO accountDTO) {
        return mapper.toDto(rep.findByAccount(accountMapper.toEntity(accountDTO, context)), context);
    }

    @Override
    public String sendAd(Long userId) {

        String emailContent;
        UserDTO user = findById(userId);
        try {
            emailContent = mailService.loadEmailContent();
        } catch (IOException e) {
            throw new RuntimeException("Ошибка загрузки содержимого HTML-файла", e);
        }

        try {
            mailService.sendHtmlEmail(user.getEmail(), "Dodo pizza!!!", emailContent);
        } catch (MessagingException e) {
            throw new RuntimeException("Ошибка отправки письма с HTML-контентом", e);
        }

        return "Success";
    }
}

