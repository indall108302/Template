package com.example.spring_example.repository;

import com.example.spring_example.entity.CustomerEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class InMemoryCustomerRepository implements CustomersRepository {
    private final List<CustomerEntity> db = new ArrayList<>();

    @Override
    public List<CustomerEntity> findAll() {
        return db;
    }

    @Override
    public CustomerEntity create(CustomerEntity customerEntity) {
        customerEntity.setId(db.size());
        db.add(customerEntity);
        return customerEntity;
    }

    @Override
    public CustomerEntity changeCustomerEmail(CustomerEntity customerEntity) {
        int id = customerEntity.getId();
        db.set(id, customerEntity);
        return customerEntity;
    }
}
