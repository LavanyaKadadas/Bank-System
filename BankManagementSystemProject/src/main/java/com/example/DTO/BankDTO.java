package com.example.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class BankDTO {

    private Long id;

    @NotNull(message = "Bank name cannot be null")
    @Size(min = 2, message = "Bank name must have at least 2 characters")
    private String name;

    @NotNull(message = "Bank code cannot be null")
    @Size(min = 2, message = "Bank code must have at least 2 characters")
    private String code;

    @NotNull(message = "Bank address cannot be null")
    @Size(min = 5, message = "Bank address must have at least 5 characters")
    private String address;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
