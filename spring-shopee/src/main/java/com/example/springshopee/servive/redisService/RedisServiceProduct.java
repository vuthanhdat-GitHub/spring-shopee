package com.example.springshopee.servive.redisService;

import com.example.springshopee.entity.Product;
import com.example.springshopee.exception.ApiException;
import com.example.springshopee.repository.RedisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RedisServiceProduct {
    @Autowired
    RedisRepository redisRepository;

    public Product setProduct(Product product) throws ApiException{
        try {
            return redisRepository.setObjectRedis(product);
        }catch (Exception ex){
            throw new ApiException("bug setProduct");
        }
    }
}
