package com.example.springshopee.helper;

import com.example.springshopee.entity.Account;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountMapper implements RowMapper {
    @Override
    public Account mapRow(ResultSet resultSet, int i) throws SQLException{
        Account account = new Account();
        account.setAccountID(resultSet.getString("accountID"));
        account.setEmail(resultSet.getString("email"));
        account.setDisplay(resultSet.getString("display"));
        account.setPassword(resultSet.getString("password"));
        account.setRole(resultSet.getString("role"));
        account.setAvatar(resultSet.getString("avatar"));
        return account;
    }
}
