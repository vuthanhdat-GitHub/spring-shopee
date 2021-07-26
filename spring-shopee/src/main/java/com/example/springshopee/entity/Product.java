package com.example.springshopee.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product implements Serializable {
    @Id
    private String productId;
    private String display;
    private Integer priceIn;
    private Integer priceOut;
    private Integer priceSale;
    private Integer amount;
    private Integer shipDay;
    private String description;
    private String images;
}
