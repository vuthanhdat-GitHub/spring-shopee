package com.example.springshopee.servive.redisService;

import com.example.springshopee.entity.Product;
import com.example.springshopee.exception.ApiException;
import com.example.springshopee.repository.RedisRepository;
import com.example.springshopee.servive.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RedisServiceProduct {
    @Autowired
    RedisRepository redisRepository;
    @Autowired
    ProductService productService;
    @Autowired
    RedisServiceProduct redisServiceProduct;

    public Product setProduct(Product product) throws ApiException{
        try {
            return redisRepository.setObjectRedis(product);
        }catch (Exception ex){
            throw new ApiException("bug setProduct");
        }
    }
    static int i = 0;
    @Scheduled(fixedRate =  360000)
    public void scheduleFixedRateTaskAsync() throws ApiException{
        System.out.println(i++);
        List<Product> list = productService.getAllProduct();
        for(Product temp : list) {
            redisServiceProduct.setProduct(temp);
        }
    }
}
