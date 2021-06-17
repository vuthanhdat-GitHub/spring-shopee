package com.example.springshopee.repository;

import com.example.springshopee.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RedisRepository {
    @Autowired
    RedisTemplate redisTemplate;

    public Product setObjectRedis(Product product){
        redisTemplate.opsForValue().set(product.getProductId(), product);
        return (Product) redisTemplate.opsForValue().get("product");
    }
}
