package com.example.spring_example.dto;

import java.time.LocalDateTime;

public class CustomerResponse {
    private int id;
    public String email;
    private LocalDateTime registrationDate;
    private boolean isActive;

    public CustomerResponse() {

    }

    public CustomerResponse(int id, String email, LocalDateTime registrationDate, boolean isActive) {
        this.id = id;
        this.email = email;
        this.registrationDate = registrationDate;
        this.isActive = isActive;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public boolean isActive() {
        return isActive;
    }
}
