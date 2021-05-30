package com.example.springshopee.repository;

import com.example.springshopee.helper.SessionMapper;
import com.example.springshopee.model.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SessionRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public Boolean createSession(String userId, String token){
        String sql = "Insert into Session (userId, token) values (?, ?)";
        Integer result = jdbcTemplate.update(sql, new Object[]{userId, token});
        if(result == 1){
            return true;
        }else{
            return false;
        }
    }

    public Session getSessionByToken(String token){
        String sql = "Select * from Session where token = ? limit 1";
        List<Session> sessions = jdbcTemplate.query(sql, new SessionMapper(), new Object[]{token});
        if(sessions.size() == 0){
            return null;
        }else{
            return sessions.get(0);
        }
    }
}
