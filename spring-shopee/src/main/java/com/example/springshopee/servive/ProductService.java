package com.example.springshopee.servive;

import com.example.springshopee.dto.GetAllProductDto;
import com.example.springshopee.entity.Account;
import com.example.springshopee.entity.Product;
import com.example.springshopee.exception.ApiException;
import com.example.springshopee.helper.jwtdecode.JwtUtil;
import com.example.springshopee.model.Session;
import com.example.springshopee.repository.AccountRepository;
import com.example.springshopee.repository.ProductRepository;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    SessionService sessionService;

    public List<Product> getAllProduct(){
        try{
            return productRepository.getAllProduct();
        }catch (Exception ex){
            return null;
        }
    }

    public List<Product> getAllProductTypeSort(String type, int sort){
        try{
            return productRepository.getAllProductTypeSort(type, sort);
        }catch (Exception ex){
            return null;
        }
    }

    public List<Product> getAllProductLimitOffset(Integer size, Integer page){
        try{
            Integer limit = size;
            Integer offset = (page - 1) * size;
            return productRepository.getAllProductLimitOffset(limit, offset);
        }catch (Exception ex){
            return null;
        }
    }

    public Product getAllProductById(@RequestParam String id){
        try{
            return productRepository.getAllProductById(id);
        }catch (Exception ex){
            return null;
        }
    }

    public Integer countProduct(){
        try{
            return productRepository.countProduct();
        }catch (Exception ex){
            return null;
        }
    }

    public Boolean addProduct(Product product){
        try{
            return productRepository.addProduct(product);
        }catch (Exception ex){
            return false;
        }
    }

    public Boolean updateProduct(Product product){
        try{
            return productRepository.updateProduct(product);
        }catch (Exception ex){
            return false;
        }
    }

    public Boolean deletedProduct(String id){
        try{
            return productRepository.deletedProduct(id);
        }catch (Exception ex){
            return false;
        }
    }

    public GetAllProductDto getAllProductDto(String token){
        Claims userId = JwtUtil.verifyToken(token);
        Account account = accountRepository.getAccountById((String) userId.get("userId"));
        List<Product> products = productRepository.getAllProduct();
        return new GetAllProductDto(products, account);
    }

    public GetAllProductDto getAllProductDto1(String token) throws ApiException{
        Session session = sessionService.getSessionByToken(token);
        if(session == null){
            throw new ApiException("Token khong hop le!!");
        }
        String userId = session.getUserId();
        Account account = accountRepository.getAccountById(userId);
        List<Product> products = productRepository.getAllProduct();
        return new GetAllProductDto(products, account);
    }
}
