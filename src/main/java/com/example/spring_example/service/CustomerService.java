package com.example.spring_example.service;

import com.example.spring_example.dto.CustomerCreateRequest;
import com.example.spring_example.dto.CustomerResponse;
import com.example.spring_example.entity.CustomerEntity;
import com.example.spring_example.repository.CustomersRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    private final CustomersRepository customersRepository;

    public CustomerService(@Qualifier("dbCustomersRepository") CustomersRepository customersRepository) {
        this.customersRepository = customersRepository;
    }

    public List<CustomerResponse> findAll() {
        return customersRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    private CustomerResponse toResponse(CustomerEntity entity) {
        return new CustomerResponse(
                entity.getId(),
                entity.getEmail(),
                entity.getRegisterDate(),
                entity.isActive()
        );
    }

    public CustomerResponse createUser(CustomerCreateRequest request) {
        return toResponse(
                customersRepository.create(toEntity(request))
        );
    }

    private CustomerEntity toEntity(CustomerCreateRequest request) {
        return new CustomerEntity(
                request.getEmail(),
                LocalDateTime.now(),
                true
        );
    }
}
