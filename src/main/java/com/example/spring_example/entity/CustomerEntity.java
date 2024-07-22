package com.example.spring_example.entity;

import java.time.LocalDateTime;

public class CustomerEntity {
    private Integer id;
    private String email;
    private LocalDateTime registerDate;
    private boolean isActive;

    public CustomerEntity(Integer id, String email, LocalDateTime registerDate, boolean isActive) {
        this(email, registerDate, isActive);
        this.id = id;
    }

    public CustomerEntity(String email, LocalDateTime registerDate, boolean isActive) {
        this.email = email;
        this.registerDate = registerDate;
        this.isActive = isActive;
    }

    public Integer getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getRegisterDate() {
        return registerDate;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
