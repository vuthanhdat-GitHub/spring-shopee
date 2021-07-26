package com.example.springshopee.controller;

import com.example.springshopee.dto.login.LoginRequestDto;
import com.example.springshopee.dto.login.LoginResponseDto;
import com.example.springshopee.entity.Account;
import com.example.springshopee.exception.ApiException;
import com.example.springshopee.helper.jwtdecode.JwtUtil;
import com.example.springshopee.servive.AccountService;
import com.example.springshopee.servive.SessionService;
import com.example.springshopee.servive.redisService.RedisServiceProduct;
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
    @Autowired
    RedisServiceProduct redisServiceProduct;

    @PostMapping("/login")
    public String loginResponseDto(@RequestBody LoginRequestDto loginRequestDto) throws ApiException{
        Account account = accountService.getAllAccountByEmailAndPasswordAccount(loginRequestDto.getEmail(), loginRequestDto.getPassword());
        if(account == null){
            throw new ApiException("Sai tai khoan hoac mat khau");
        }
        String accountId = account.getAccountID();
        String token = JwtUtil.generateToken(accountId, account.getRole());
//        try{
//            redisServiceProduct.scheduleFixedRateTaskAsync();
//        }catch (Exception ex){
//            ex.printStackTrace();
//        }
        System.out.println(token);
        return token;
    }
}
