package com.example.springshopee.controller;

import com.example.springshopee.entity.Account;
import com.example.springshopee.repository.AccountRepository;
import com.example.springshopee.servive.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    AccountService accountService;

    @GetMapping("/getAllAccount")
    public List<Account> getAllAccount(){
        return accountService.getAllAccount();
    }

    @GetMapping("/getAllAccountById")
    public Account getAllAccountById(String id){
        return accountService.getAllAccountById(id);
    }

    @PostMapping("/addAccount")
    public Boolean addAccount(@RequestBody Account account){
        return accountService.addAccount(account);
    }

    @PutMapping("/updateAccount/{id}")
    public Boolean updateAccount(@PathVariable("id") String id, Account account){
        return accountService.updateAccount(account);
    }

    @DeleteMapping("/deleteAccount/{id}")
    public Boolean deleteAccount(@PathVariable("id") String id){
        return accountService.deleteAccount(id);
    }

}
