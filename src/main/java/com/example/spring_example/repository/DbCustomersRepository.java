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
        return jdbcTemplate.query(
                sql,
                new RowMapper<CustomerEntity>() {
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
        );
    }

    @Override
    public CustomerEntity create(CustomerEntity customerEntity) {
        String sql = "INSERT INTO customer(email, created_at, is_active) values(?,?,?)";
        jdbcTemplate.update(sql,
                new Object[]{
                        customerEntity.getEmail(),
                        customerEntity.getRegisterDate(),
                        customerEntity.isActive()
                }
        );

        try {
            sql = "select * from customer where email=?";
            CustomerEntity newCustomer = jdbcTemplate.queryForObject(
                    sql,
                    new RowMapper<CustomerEntity>() {
                        @Override
                        public CustomerEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
                            CustomerEntity customerEntity = new CustomerEntity();
                            customerEntity.setId(rs.getInt("id"));
                            customerEntity.setEmail(rs.getString("email"));
                            customerEntity.setActive(rs.getBoolean("is_active"));
                            customerEntity.setRegisterDate(rs.getObject("created_at", LocalDateTime.class));
                            return customerEntity;
                        }
                    },
                    customerEntity.getEmail());
            return newCustomer;
        } catch (IncorrectResultSetColumnCountException e) {
            return null;
        }
    }

}
