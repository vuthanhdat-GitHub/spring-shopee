package com.example.springshopee.controller;

import com.example.springshopee.dto.login.LoginRequestDto;
import com.example.springshopee.dto.login.LoginResponseDto;
import com.example.springshopee.entity.Account;
import com.example.springshopee.exception.ApiException;
import com.example.springshopee.servive.AccountService;
import com.example.springshopee.servive.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AccountService accountService;
    @Autowired
    SessionService sessionService;

    @PostMapping("/login")
    public LoginResponseDto loginResponseDto(@RequestBody LoginRequestDto loginRequestDto) throws ApiException{
        Account account = accountService.getAllAccountByEmailAndPasswordAccount(loginRequestDto.getEmail(), loginRequestDto.getPassword());
        if(account == null){
            throw new ApiException("Sai tai khoan hoac mat khau");
        }
        String accountId = account.getAccountID();
        String token = UUID.randomUUID().toString();
        Boolean createSessionResult = sessionService.createSession(accountId, token);
        if(createSessionResult){
            return new LoginResponseDto(token);
        }else{
            throw new ApiException("Dang nhap that bai, thu lai sau!");
        }
    }
}
