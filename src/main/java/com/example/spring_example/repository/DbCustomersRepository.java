package com.example.spring_example.repository;

import com.example.spring_example.entity.CustomerEntity;
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
    private final DataSource dataSource;

    public DbCustomersRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<CustomerEntity> findAll() {
        try(Connection cnn = dataSource.getConnection()) {
            String sql = "select * from customer";
            PreparedStatement ps = cnn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            List<CustomerEntity> entities = new LinkedList<>();
            while (rs.next()) {
                entities.add(toEntiy(rs));
            }
            return entities;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private CustomerEntity toEntiy(ResultSet row) throws SQLException {
        return new CustomerEntity(
                row.getInt("id"),
                row.getString("email"),
                row.getObject("created_at", LocalDateTime.class),
                row.getBoolean("is_active")
        );
    }

    @Override
    public CustomerEntity create(CustomerEntity customerEntity) {
        Connection cnn = null;
        try {
            cnn = dataSource.getConnection();
            String sql = "INSERT INTO customer(email, created_at, is_active) values(?,?,?)";
            PreparedStatement ps = cnn.prepareStatement(sql);
            ps.setString(1, customerEntity.getEmail());
            ps.setObject(2, customerEntity.getRegisterDate());
            ps.setBoolean(3, customerEntity.isActive());

            ps.executeUpdate();

            String select = "select * from customer where email = ?";
            PreparedStatement psSelect = cnn.prepareStatement(select);
            psSelect.setString(1, customerEntity.getEmail());
            ResultSet rs = psSelect.executeQuery();
            rs.next();
            return toEntiy(rs);

        } catch (SQLException e)
        {
            try {
                if (cnn != null)
                    cnn.rollback();
            } catch (Exception ignore) {}
            throw new RuntimeException(e);
        }
    }

}
