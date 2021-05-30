package com.example.springshopee.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    private String accountID;
    private String email;
    private String display;
    private String password;
    private String role;
    private String avatar;
}
