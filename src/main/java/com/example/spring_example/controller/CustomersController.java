package com.example.spring_example.controller;

import com.example.spring_example.dto.CustomerCreateRequest;
import com.example.spring_example.dto.CustomerResponse;
import com.example.spring_example.service.CustomerService;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/customer/{email}")
    public CustomerResponse changeCustomerEmail(@PathVariable("email") String email,
                                                @RequestBody CustomerCreateRequest request){
        return customerService.changeCustomerEmail(request);
    }
}
