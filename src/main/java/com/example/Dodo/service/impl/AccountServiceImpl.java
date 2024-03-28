package com.example.Dodo.service.impl;

import com.example.Dodo.base.BaseServiceImpl;
import com.example.Dodo.dao.AccountRepository;
import com.example.Dodo.exception.IncorrectRequest;
import com.example.Dodo.exception.RegistrationError;
import com.example.Dodo.mapper.AccountMapper;
import com.example.Dodo.model.DTO.AccountDTO;
import com.example.Dodo.model.DTO.UserDTO;
import com.example.Dodo.model.entity.Account;
import com.example.Dodo.model.enums.AccountStatus;
import com.example.Dodo.model.request.AuthRequest;
import com.example.Dodo.model.request.ValidateEmailRequest;
import com.example.Dodo.service.AccountService;
import com.example.Dodo.service.UserService;
import com.example.Dodo.util.JwtProvider;
import com.example.Dodo.util.Language;
import com.example.Dodo.util.ResourceBundleLanguage;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Random;

@Service
public class AccountServiceImpl extends BaseServiceImpl<Account, AccountRepository, AccountDTO, AccountMapper> implements AccountService {
    public AccountServiceImpl(AccountRepository rep, AccountMapper mapper, JwtProvider jwtProvider, MailService mailService, UserService userService) {
        super(rep, mapper);
        this.jwtProvider = jwtProvider;
        this.mailService = mailService;
        this.userService = userService;
    }

    private final JwtProvider jwtProvider;
    private final MailService mailService;
    private final UserService userService;
    private static final String DIGITS = "0123456789";
    private static final Random RANDOM = new Random();

    @Override
    public AccountDTO findByEmail(String email) {
        return mapper.toDto(rep.findByEmail(email), context);
    }

    @Override
    public String generateRandomPassword(int length) {

            StringBuilder password = new StringBuilder();
            for (int i = 0; i < length; i++) {
                int index = RANDOM.nextInt(DIGITS.length());
                password.append(DIGITS.charAt(index));
            }
            return password.toString();
    }

    @Override
    public String authAccount(AuthRequest request, Integer languageOrdinal) {

        Language language = Language.getLanguage(languageOrdinal);

        AccountDTO account = findByEmail(request.getEmail());
        String tempPassword = generateRandomPassword(6);

        if(mailService.validate(request.getEmail())) {
            mailService.send(request.getEmail(), "Validate", "Your temp password: " + tempPassword);
        } else {
            throw new IncorrectRequest(ResourceBundleLanguage.periodMessage(language, "incorrectRequest"));
        }

        if(account == null) {

            AccountDTO accountNew = new AccountDTO();

            accountNew.setTempPassword(tempPassword);
            accountNew.setEmail(request.getEmail());
            accountNew.setAccountStatus(AccountStatus.NEW);
            save(accountNew);

            UserDTO userDTO = new UserDTO();
            userDTO.setName(request.getName());
            userDTO.setEmail(request.getEmail());
            userDTO.setAccountDTO(accountNew);

            userService.save(userDTO);

        } else if (account != null){

            account.setTempPassword(tempPassword);
            update(account);

        } else {
            throw new RegistrationError(ResourceBundleLanguage.periodMessage(language, "registrationError"));
        }

        return "Success";
    }

    @Override
    public String validateAccount(ValidateEmailRequest request, Integer languageOrdinal) {

        Language language = Language.getLanguage(languageOrdinal);
        AccountDTO account = new AccountDTO();
        String accessToken = null;

        try {
            account = findByEmail(request.getEmail());
        }catch (RuntimeException e) {
            throw new IncorrectRequest(ResourceBundleLanguage.periodMessage(language, "incorrectRequest"));
        }

        Long minutesPassed = Duration.between(account.getUpdatedDate(), LocalDateTime.now()).toMinutes();

        if (account.getTempPassword().equals(request.getPassword()) && minutesPassed <= 10) {
            account.setAccountStatus(AccountStatus.APPROVED);
            update(account);
            UserDTO user = userService.findByAccount(account);

            accessToken = jwtProvider.generateAccessToken(user.getId());
        } else {
            throw new IncorrectRequest(ResourceBundleLanguage.periodMessage(language, "incorrectPassword"));
        }

        return accessToken;
    }

    @Override
    public Account mappingAccountDTO(AccountDTO accountDTO) {
        return mapper.toEntity(accountDTO, context);
    }
}
