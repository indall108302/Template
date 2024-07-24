package com.example.spring_example.dto;

public class CustomerCreateRequest {
    private String email;



    public String getEmail() {
        return email;
    }

    public CustomerCreateRequest(String email) {
        this.email = email;
    }

    public CustomerCreateRequest() {

    }
}
