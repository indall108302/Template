package com.example.spring_example.controller;

import com.example.spring_example.dto.CustomerCreateRequest;
import com.example.spring_example.dto.CustomerResponse;
import com.example.spring_example.service.CustomerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomersController {
    private final CustomerService customerService;

    public CustomersController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customers")
    public List<CustomerResponse> allUsers() {
        return customerService.findAll();
    }

    @PostMapping("/customers")
    public CustomerResponse createUser(@RequestBody CustomerCreateRequest request) {
        return customerService.createUser(request);
    }
}
