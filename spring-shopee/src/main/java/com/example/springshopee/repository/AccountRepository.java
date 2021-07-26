package com.example.springshopee.repository;

import com.example.springshopee.entity.Account;
import com.example.springshopee.helper.AccountMapper;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AccountRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Account> getAllAccount(){
        String sql = "SELECT  * FROM Account WHERE deleted = 0;";
        List<Account> list = jdbcTemplate.query(sql, new AccountMapper());
        return list;
    }

    public Account getAccountById(String id){
        String sql = "SELECT  * FROM Account WHERE AccountID = ? and deleted = 0;";
        Account account = (Account) jdbcTemplate.queryForObject(sql, new AccountMapper(), new Object[]{id});
        return account;
    }

    public Account getAccountByEmailAndPassword(String email, String password){
        String sql = "SELECT  * FROM Account WHERE email = ? and password = ? limit 1;";
        Account account = (Account) jdbcTemplate.queryForObject(sql, new AccountMapper(), new Object[]{email, password});
        return account;
    }

    public Boolean addAccount(Account account){
        String sql = "Insert into Account (accountID, email, display, password, role, avatar) values (?, ?, ?, ?, ?, ?);";
        String hash = BCrypt.hashpw(account.getPassword(), BCrypt.gensalt(12));
        Object values[] = new Object[6];
        values[0] = account.getAccountID();
        values[1] = account.getEmail();
        values[2] = account.getDisplay();
        values[3] = hash;
        values[4] = account.getRole();
        values[5] = account.getAvatar();
        jdbcTemplate.update(sql, values);
        return true;
    }

    public Boolean updateAccount(Account account){
        String sql = "Update Account set email = ?, display = ?, password = ?, role = ?, avatar = ? where Account ID = ? and deleted = 0;";
        Object values[] = new Object[6];
        values[1] = account.getEmail();
        values[2] = account.getDisplay();
        values[3] = account.getPassword();
        values[4] = account.getRole();
        values[5] = account.getAvatar();
        values[6] = account.getAccountID();
        jdbcTemplate.update(sql, values);
        return true;
    }

    public Boolean deleteAccount(String id){
        String sql = "Delete Account where accountID = ? and deleted = 0;";
        jdbcTemplate.update(sql, new Object[]{id});
        return true;
    }

}
