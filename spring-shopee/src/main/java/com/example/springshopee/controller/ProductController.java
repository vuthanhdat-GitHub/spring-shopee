package com.example.springshopee.controller;

import com.example.springshopee.builder.ProductBuilder;
import com.example.springshopee.dto.GetAllProductDto;
import com.example.springshopee.entity.Product;
import com.example.springshopee.exception.ApiException;
import com.example.springshopee.servive.AccountService;
import com.example.springshopee.servive.ProductService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping("/getAllProduct")
    public List<Product> getAllProduct(@RequestHeader String token
            , @RequestParam("display") String display){

//        if(!AccountService.ListLogin.containsKey(token)){
//            return null;
//        }
        ProductBuilder builder = initBuilder(display);
        return productService.getAllProduct(builder);
    }

    @GetMapping("/getAllProductTypeSort")
    public List<Product> getAllProductTypeSort(@RequestParam String type, @RequestParam int sort){
        return productService.getAllProductTypeSort(type, sort);
    }

    @GetMapping("/getAllProductLimitOffset")
    public List<Product> getAllProductLimitOffset(@RequestParam(required = false) Integer size, @RequestParam(required = false) Integer page){
        if(size == null){
            size = 10;
        }
        if(page == null){
            page = 1;
        }
        return productService.getAllProductLimitOffset(size, page);
    }

    @GetMapping("/getAllProductById")
    public Product getAllProductById(@RequestParam String id){
        return productService.getAllProductById(id);
    }

    @GetMapping("/countProduct")
    public Integer countProduct(){
        return productService.countProduct();
    }

    @GetMapping("/addProduct")
    public Boolean addProduct(@RequestBody Product product){
        return productService.addProduct(product);
    }

    @PutMapping("/updateProduct/{id}")
    public Boolean updateProduct(@PathVariable("id") String id, @RequestBody Product product){
        return productService.updateProduct(product);
    }

    @DeleteMapping("/deleteProduct")
    public Boolean deleteProduct(@PathVariable("id") String id){
        return productService.deletedProduct(id);
    }


    @GetMapping("/getAllProductDto")
    public GetAllProductDto getAllProductDto(HttpServletRequest request){
        String token = request.getHeader("token");
//      System.out.println(token);
        System.out.println(productService.getAllProductDto(token));

        return productService.getAllProductDto(token);
    }

    @GetMapping("/getAllProductDto1")
    public GetAllProductDto getAllProductDto(@RequestParam String token, HttpServletRequest request) throws ApiException {
        return productService.getAllProductDto1(token);
    }
    @GetMapping("/test")
    public String test(@RequestParam String str){
        return str;
    }

    private ProductBuilder initBuilder(String display){
        return new ProductBuilder.builder()
                .setDisplay(display)
                .builder();
    }
}
