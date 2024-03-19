package com.example.ecommerce.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Category {

    @NotEmpty(message = "Id cannot be empty")
    private String id;

    @NotEmpty(message = "Name cannot be empty")
    @Size(min = 3, message = "Name must be at least 3 characters")
    private String name;
}
