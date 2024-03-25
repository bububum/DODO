package com.example.Dodo.service.impl;

import com.example.Dodo.base.BaseServiceImpl;
import com.example.Dodo.dao.UserRepository;
import com.example.Dodo.exception.IncorrectRequest;
import com.example.Dodo.mapper.UserMapper;
import com.example.Dodo.model.DTO.UserDTO;
import com.example.Dodo.model.entity.User;
import com.example.Dodo.model.request.UserCreateRequest;
import com.example.Dodo.service.UserService;
import com.example.Dodo.util.Language;
import com.example.Dodo.util.ResourceBundleLanguage;
import jakarta.mail.MessagingException;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class UserServiceImpl extends BaseServiceImpl<User, UserRepository, UserDTO, UserMapper> implements UserService {
    public UserServiceImpl(UserRepository rep, UserMapper mapper, MailService mailService) {
        super(rep, mapper);
        this.mailService = mailService;
    }

    private final MailService mailService;

    @Override
    public String create(UserCreateRequest request, Integer languageOrdinal) {

        Language language = Language.getLanguage(languageOrdinal);

            if (mailService.validate(request.getEmail()) && request != null) {

                String emailContent;
                try {
                    emailContent = mailService.loadEmailContent();
                } catch (IOException e) {
                    throw new RuntimeException("Ошибка загрузки содержимого HTML-файла", e);
                }

                try {
                    mailService.sendHtmlEmail(request.getEmail(), "Validation", emailContent);
                } catch (MessagingException e) {
                    throw new RuntimeException("Ошибка отправки письма с HTML-контентом", e);
                }

                UserDTO userDTO = new UserDTO();
                userDTO.setName(request.getName());
                userDTO.setEmail(request.getEmail());

                save(userDTO);

            } else {
                throw new IncorrectRequest(ResourceBundleLanguage.periodMessage(language, "incorrectRequest"));
            }
        return "Success";
    }


}

