package com.example.springshopee.repository;

import com.example.springshopee.builder.ProductBuilder;
import com.example.springshopee.entity.Product;
import com.example.springshopee.helper.ProductMapper;
import com.example.springshopee.repository.utils.BuildMapUtils;
import com.example.springshopee.repository.utils.RepositoryCustomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Repository
public class ProductRepositoryCustom extends RepositoryCustomUtils<Product> {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    private BuildMapUtils buildMapUtils;

    public List<Product> getAllProduct(){
        String sql = "SELECT * FROM Product WHERE deleted = 0;";
        List<Product> list = jdbcTemplate.query(sql, new ProductMapper());
        return list;
    }

    public List<Product> getAllProductBuilder(ProductBuilder productBuilder){
        Map<String, Object> map = buildMapUtils.buildMapSearch(productBuilder);
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ");
        sql.append("    * ");
        sql.append("FROM product p ");
        sql.append("WHERE 1=1 ");
        if (productBuilder.getDisplay() != null) {
            sql.append(" AND p.display LIKE :display ");
        }
        return this.getResultList(sql.toString(), map , Product.class);
    }
    
    public List<Product> getAllProductTypeSort(String type, int sort){
        switch (sort){
            case 0:
                String sql = "SELECT * FROM Product Order by " + type + "desc;";
                List<Product> list = jdbcTemplate.query(sql, new ProductMapper());
                return list;
            case 1:
                String sql1 = "SELECT * FROM Product Order by " + type + "asc;";
                List<Product> list1 = jdbcTemplate.query(sql1, new ProductMapper());
                return list1;
            default:
                return null;
        }
    }

    public List<Product> getAllProductLimitOffset(Integer limit, Integer offset){
        String sql = "SELECT * FROM Product limit ? offset ?;";
        List<Product> list = jdbcTemplate.query(sql, new ProductMapper(), new Object[]{limit, offset});
        return list;
    }

    public Product getAllProductById(String id){
        String sql = "SELECT * FROM Product where productID = ? deleted = 0;";
        Product product = (Product) jdbcTemplate.queryForObject(sql, new ProductMapper(), new Object[]{id});
        return product;
    }

    public Integer countProduct(){
        String sql = "SELECT count(ProductId) FROM Product;";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    public Boolean addProduct(Product product){
        String sql = "INSERT INTO Product (productID, display, priceIn, priceOut, priceSale, amount, shipday, description, images) Values (?, ?, ?, ?, ?, ?, ?, ?, ?);";
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
        String sql = "Delete Product where ProductID = ? and deleted = 0;";
        jdbcTemplate.update(sql, new Object[]{id});
        return true;
    }
}
