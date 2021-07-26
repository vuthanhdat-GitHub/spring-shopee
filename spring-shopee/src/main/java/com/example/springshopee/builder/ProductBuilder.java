package com.example.springshopee.builder;

public class ProductBuilder {
    private String productId;
    private String display;
    private int priceIn;
    private int priceOut;
    private int priceSale;
    private int amount;
    private int shipDay;
    private String description;
    private String images;

    public ProductBuilder(builder builder){
        this.productId = builder.productId;
        this.display = builder.display;
        this.priceIn = builder.priceIn;
        this.priceOut = builder.priceOut;
        this.priceSale = builder.priceSale;
        this.amount = builder.amount;
        this.shipDay = builder.shipDay;
        this.description = builder.description;
        this.images = builder.images;
    }
    public String getProductId(){
        return productId;
    }
    public String getDisplay(){
        return display;
    }
    public int getPriceIn(){
        return priceIn;
    }
    public int getPriceOut(){
        return priceOut;
    }
    public int getPriceSale(){
        return priceSale;
    }
    public int getAmount(){
        return amount;
    }
    public int getShipDay(){
        return shipDay;
    }
    public String getDescription(){
        return description;
    }
    public String getImages(){
        return images;
    }

    public static class builder{
        private String productId;
        private String display;
        private int priceIn;
        private int priceOut;
        private int priceSale;
        private int amount;
        private int shipDay;
        private String description;
        private String images;

        public builder(){

        }
        public builder setProductId(String productId){
            this.productId = productId;
            return this;
        }
        public builder setDisplay(String display){
            this.display = display;
            return this;
        }
        public builder setPriceIn(int priceIn){
            this.priceIn = priceIn;
            return this;
        }
        public builder setPriceOut(int priceOut){
            this.priceOut = priceOut;
            return this;
        }
        public builder setPriceSale(int priceSale){
            this.priceSale = priceSale;
            return this;
        }
        public builder setAmount(int amount){
            this.amount = amount;
            return this;
        }
        public builder setShipDay(int shipDay){
            this.shipDay = shipDay;
            return this;
        }
        public builder setDescription(String description){
            this.description = description;
            return this;
        }
        public builder setImages(String images){
            this.images = images;
            return this;
        }

        public ProductBuilder builder() {
            return new ProductBuilder(this);
        }
    }
}
