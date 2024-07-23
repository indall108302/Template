package com.example.spring_example.repository;

import com.example.spring_example.entity.CustomerEntity;
import org.springframework.jdbc.IncorrectResultSetColumnCountException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Repository
public class DbCustomersRepository implements CustomersRepository {
    private JdbcTemplate jdbcTemplate;

    public DbCustomersRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<CustomerEntity> findAll() {
        String sql = "select * from customer";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(CustomerEntity.class));
    }

    @Override
    public CustomerEntity create(CustomerEntity customerEntity) {
        String sql = "INSERT INTO customer(email, created_at, is_active) values(?,?,?)";
        jdbcTemplate.update(sql, new CustomerEntity(
                customerEntity.getEmail(), customerEntity.getRegisterDate(), customerEntity.isActive())
        );

        try {
            sql = "select * from customer where email = ?";
            return jdbcTemplate.queryForObject(sql, new Object[]{customerEntity.getEmail()}, new BeanPropertyRowMapper<>(CustomerEntity.class));
        } catch (IncorrectResultSetColumnCountException e) {
            return null;
        }
    }

}
