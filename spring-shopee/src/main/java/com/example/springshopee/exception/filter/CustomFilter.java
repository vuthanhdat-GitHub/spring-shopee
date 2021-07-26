//package com.example.springshopee.exception.filter;
//
//import com.example.springshopee.dto.InvalidTokenDto;
//import com.example.springshopee.helper.jwtdecode.JwtUtil;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import io.jsonwebtoken.Claims;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.*;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.OutputStream;
//
//@Component
//@Order(1)
//public class CustomFilter implements Filter {
//
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        System.out.println("sdfdfsf");
//        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        HttpServletResponse response = (HttpServletResponse) servletResponse;
//
//        String token = request.getHeader("token");
//        System.out.println(token);
//        try {
//            Claims claims = JwtUtil.verifyToken(token);
//            String role = (String) claims.get("role");
//            String userId = (String) claims.get("userId");
//            if (role.equals("ADMIN")) {
//                System.out.println("user: " + userId);
//                request.setAttribute("userId", userId);
//                System.out.println(request.getAttribute("userId"));
//                filterChain.doFilter(request, response);
//            } else {
//                System.out.println("bug bug bug");
//            }
//        } catch (Exception e) {
//            System.out.println("----------------------------");
//            response.setStatus(401);
//            response.setContentType("application/json");
//            InvalidTokenDto invalidTokenDTO = new InvalidTokenDto("Invalid token");
//            OutputStream out = response.getOutputStream();
//            ObjectMapper mapper = new ObjectMapper();
//            out.flush();
//        }
//    }
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        System.out.println("Default filter init");
//    }
//
//    @Override
//    public void destroy() {
//        System.out.println("Default filter destroy");
//    }
//}
