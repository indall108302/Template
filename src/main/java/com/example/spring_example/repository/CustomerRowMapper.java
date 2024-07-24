package com.example.spring_example.repository;

import com.example.spring_example.entity.CustomerEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class CustomerRowMapper implements RowMapper<CustomerEntity> {
    @Override
    public CustomerEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setId(rs.getInt("id"));
        customerEntity.setEmail(rs.getString("email"));
        customerEntity.setActive(rs.getBoolean("is_active"));
        customerEntity.setRegisterDate(rs.getObject("created_at", LocalDateTime.class));
        return customerEntity;
    }
}
