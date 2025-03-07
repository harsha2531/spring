package org.example.z_13_springboot_validation.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class CustomerDTO {

    @NotBlank(message = "Name is required")
    @Pattern(regexp = "^[A-Za-z ]+$",message = "Name must contain only letters and spaces")
    @Size(min = 3, max = 200)
    private String name;

    @Email(message = "Invalid email address")
    private String email;

    @Pattern(regexp = "^[0-9]{10}",message = "Phone number must be have 10 digits")
    private String phoneNumber;

    public CustomerDTO() {
    }

    public CustomerDTO(String name, String email, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
