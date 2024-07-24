package com.example.spring_example.repository;

import com.example.spring_example.entity.CustomerEntity;

import java.util.List;

public interface CustomersRepository {
    List<CustomerEntity> findAll();
    CustomerEntity create(CustomerEntity customerEntity);
    CustomerEntity changeCustomerEmail(CustomerEntity entity);
}
