package com.example.springshopee.dto;

import com.example.springshopee.entity.Account;
import com.example.springshopee.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllProductDto {
    List<Product> products;
    Account account;
}
