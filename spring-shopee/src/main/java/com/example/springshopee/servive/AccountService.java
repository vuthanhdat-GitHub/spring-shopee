package com.example.springshopee.servive;

import com.example.springshopee.entity.Account;
import com.example.springshopee.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;

@Service
public class AccountService {
    public static int index = 0;
    public static HashMap<Integer, Account> ListLogin = new HashMap<>();
    @Autowired
    AccountRepository accountRepository;

    public List<Account> getAllAccount(){
        try{
            return accountRepository.getAllAccount();
        }catch (Exception ex){
            return null;
        }
    }

    public Account getAllAccountById(@RequestParam String id){
        try {
            return accountRepository.getAccountById(id);
        }catch (Exception ex){
            return null;
        }
    }

    public Account getAllAccountByEmailAndPasswordAccount(@RequestParam String email, String password){
        try {
            return accountRepository.getAccountByEmailAndPassword(email, password);
        }catch (Exception ex){
            return null;
        }
    }

    public Boolean addAccount(Account account){
        try{
            return accountRepository.addAccount(account);
        }catch (Exception ex){
            return false;
        }
    }

    public Boolean updateAccount(Account account){
        try{
            return accountRepository.updateAccount(account);
        }catch (Exception ex){
            return false;
        }
    }

    public Boolean deleteAccount(String id){
        try{
            return accountRepository.deleteAccount(id);
        }catch (Exception ex){
            return false;
        }
    }

    public int loginAccount(Account account){
        Account account1 = getAllAccountById(account.getAccountID());
        if(account1.getEmail().equals(account.getEmail()) && account1.getPassword().equals(account.getPassword())){
            ListLogin.put(index, account);
            index ++;
            return index - 1;
        }else{
            System.out.println("Khong dang nhap duoc!!");
        }
        return -1;
    }
}
