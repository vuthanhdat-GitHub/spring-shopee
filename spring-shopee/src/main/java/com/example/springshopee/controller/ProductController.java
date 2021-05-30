package com.example.springshopee.controller;

import com.example.springshopee.entity.Product;
import com.example.springshopee.servive.AccountService;
import com.example.springshopee.servive.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @RequestMapping("/getAllProduct")
    public List<Product> getAllProduct(@RequestHeader int token){
        if(!AccountService.ListLogin.containsKey(token)){
            return null;
        }
        return productService.getAllProduct();
    }

    @RequestMapping("/getAllProductTypeSort")
    public List<Product> getAllProductTypeSort(@RequestParam String type, @RequestParam int sort){
        return productService.getAllProductTypeSort(type, sort);
    }

    @RequestMapping("/getAllProductLimitOffset")
    public List<Product> getAllProductLimitOffset(@RequestParam(required = false) Integer size, @RequestParam(required = false) Integer page){
        if(size == null){
            size = 10;
        }
        if(page == null){
            page = 1;
        }
        return productService.getAllProductLimitOffset(size, page);
    }

    @RequestMapping("/getAllProductById")
    public Product getAllProductById(@RequestParam String id){
        return productService.getAllProductById(id);
    }

    @RequestMapping("/countProduct")
    public Integer countProduct(){
        return productService.countProduct();
    }

    @RequestMapping("/addProduct")
    public Boolean addProduct(@RequestBody Product product){
        return productService.addProduct(product);
    }

    @RequestMapping("/updateProduct/{id}")
    public Boolean updateProduct(@PathVariable("id") String id, @RequestBody Product product){
        return productService.updateProduct(product);
    }

    @RequestMapping("/deleteProduct")
    public Boolean deleteProduct(@PathVariable("id") String id){
        return productService.deletedProduct(id);
    }
}
