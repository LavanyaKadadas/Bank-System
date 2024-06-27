package com.example.dto;

import jakarta.validation.constraints.NotBlank;

public class BankDTO {

    private Integer code;

    @NotBlank(message = "Bank name is required")
    private String name;

    @NotBlank(message = "Bank address is required")
    private String address;

    // Getters and setters

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
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
}
