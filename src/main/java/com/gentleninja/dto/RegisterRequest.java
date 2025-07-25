package com.gentleninja.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterRequest {
    @NotBlank
    private String Name;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String password;
}
