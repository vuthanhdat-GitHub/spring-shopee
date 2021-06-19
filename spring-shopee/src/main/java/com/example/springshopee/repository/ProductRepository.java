package com.example.springshopee.repository;

import com.example.springshopee.entity.Product;
import com.example.springshopee.helper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public class ProductRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Product> getAllProduct(){
        String sql = "Select * from Product where deleted = 0;";
        List<Product> list = jdbcTemplate.query(sql, new ProductMapper());
        return list;
    }
    
    public List<Product> getAllProductTypeSort(String type, int sort){
        switch (sort){
            case 0:
                String sql = "Select * from Product Order by " + type + "desc;";
                List<Product> list = jdbcTemplate.query(sql, new ProductMapper());
                return list;
            case 1:
                String sql1 = "Select * from Product Order by " + type + "asc;";
                List<Product> list1 = jdbcTemplate.query(sql1, new ProductMapper());
                return list1;
            default:
                return null;
        }
    }

    public List<Product> getAllProductLimitOffset(Integer limit, Integer offset){
        String sql = "Select * from Product limit ? offset ?;";
        List<Product> list = jdbcTemplate.query(sql, new ProductMapper(), new Object[]{limit, offset});
        return list;
    }

    public Product getAllProductById(String id){
        String sql = "Select * from Product where productID = ? deleted = 0;";
        Product product = (Product) jdbcTemplate.queryForObject(sql, new ProductMapper(), new Object[]{id});
        return product;
    }

    public Integer countProduct(){
        String sql = "Select count(ProductId) from Product;";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    public Boolean addProduct(Product product){
        String sql = "Insert into Product (productID, display, priceIn, priceOut, priceSale, amount, shipday, description, images) Values (?, ?, ?, ?, ?, ?, ?, ?, ?);";
        Object params[] = new Object[9];
        params[0] = product.getProductId();
        params[1] = product.getDisplay();
        params[2] = product.getPriceIn();
        params[3] = product.getPriceOut();
        params[4] = product.getPriceSale();
        params[5] = product.getAmount();
        params[6] = product.getShipDay();
        params[7] = product.getDescription();
        params[8] = product.getImages();
        jdbcTemplate.update(sql, params);
        return true;
    }

    public Boolean updateProduct(Product product){
        String sql = "Update Product set display = ?, priceIn = ?, priceOut = ?, priceSale = ?, amount = ?, shipday = ?, description = ?, images = ?, productId = ? and deleted = 0;";
        Object params[] = new Object[9];
        params[0] = product.getProductId();
        params[1] = product.getDisplay();
        params[2] = product.getPriceIn();
        params[3] = product.getPriceOut();
        params[4] = product.getPriceSale();
        params[5] = product.getAmount();
        params[6] = product.getShipDay();
        params[7] = product.getDescription();
        params[8] = product.getImages();
        jdbcTemplate.update(sql, params);
        return true;
    }

    public Boolean deletedProduct(String id){
        String sql = "Deleted Product where ProductID = ? and deleted = 0;";
        jdbcTemplate.update(sql, new Object[]{id});
        return true;
    }
}
