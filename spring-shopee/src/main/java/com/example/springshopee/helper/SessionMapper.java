package com.example.springshopee.helper;

import com.example.springshopee.model.Session;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SessionMapper implements RowMapper<Session> {
    @Override
    public Session mapRow(ResultSet resultSet, int i) throws SQLException{
        Session session = new Session();
        session.setUserId(resultSet.getString("userId"));
        session.setToken(resultSet.getString("token"));
        return session;
    }
}
