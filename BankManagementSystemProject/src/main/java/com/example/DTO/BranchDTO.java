package com.example.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class BranchDTO {

    private Long id;

    @NotNull(message = "Branch name cannot be null")
    @Size(min = 2, message = "Branch name must have at least 2 characters")
    private String name;

    @NotNull(message = "Address cannot be null")
    @Size(min = 5, message = "Address must have at least 5 characters")
    private String address;

    @NotNull(message = "Bank ID cannot be null")
    private Long bankId;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getBankId() {
        return bankId;
    }

    public void setBankId(Long bankId) {
        this.bankId = bankId;
    }
}
