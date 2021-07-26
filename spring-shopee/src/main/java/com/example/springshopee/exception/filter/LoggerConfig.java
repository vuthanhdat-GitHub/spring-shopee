//package com.example.springshopee.exception.filter;
//
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class LoggerConfig {
//    @Bean
//    public FilterRegistrationBean<CustomFilter> loggingFilter(){
//        FilterRegistrationBean<CustomFilter> registrationBean = new FilterRegistrationBean<>();
//        registrationBean.setFilter(new CustomFilter() );
//        registrationBean.setOrder(1);
//        registrationBean.addUrlPatterns("/product/getAllProduct");
//        registrationBean.addUrlPatterns("/product/updateProduct/1");
//        registrationBean.addUrlPatterns("/product/getAllProductDto");
//        return  registrationBean;
//    }
//}
