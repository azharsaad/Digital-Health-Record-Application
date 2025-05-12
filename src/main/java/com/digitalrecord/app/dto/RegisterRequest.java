package com.digitalrecord.app.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegisterRequest {
    @NotBlank(message = "Name is mandatory")
    private String name;
    @Email(message = "Invalid email format")
    private String email;
    @Size(min = 6, message = "Password must be atleast 6 characters")
    private String password;
    @NotBlank(message = "Role is required")
    private String role;
}
