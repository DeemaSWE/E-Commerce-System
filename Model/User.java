package com.example.ecommerce.Model;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class User {

    @NotEmpty(message = "Id cannot be empty")
    private String id;

    @NotEmpty(message = "Username cannot be empty")
    @Size(min = 5, message = "Username must be at least 5 characters")
    private String username;

    @NotEmpty(message = "Password cannot be empty")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,}$",
            message = "Password must be at least 6 characters long and contain both letters and digits")
    private String password;

    @NotEmpty(message = "Email cannot be empty")
    @Email(regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$",
            message = "Invalid email format")
    private String email;


    @NotEmpty(message = "Email cannot be empty")
    @Pattern(regexp = "^(Admin|Customer)$", message = "Role must be either Admin or Customer")
    private String role;

    @NotNull(message = "Balance cannot be empty")
    @Positive(message = "Balance must be a positive number")
    private Double balance;

}
