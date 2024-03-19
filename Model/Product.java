package com.example.ecommerce.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class Product {

    @NotEmpty(message = "Id cannot be empty")
    private String id;

    @NotEmpty(message = "Name cannot be empty")
    @Size(min = 3, message = "Name must be at least 3 characters")
    private String name;

    @NotNull(message = "Price cannot be empty")
    @Positive(message = "Price must be a positive number")
    private Double price;

    @NotEmpty(message = "Category Id cannot be empty")
    private String categoryId;

    @NotNull(message = "Units sold cannot be empty")
    @PositiveOrZero(message = "Units sold must be a positive number")
    private Integer unitsSold;

    @Min(value = 0, message = "Average rating must be a number between 0 and 5")
    @Max(value = 5, message = "Average rating must be a number between 0 and 5")
    private double averageRating;

    @PositiveOrZero(message = "Rating count must be a positive number")
    private int ratingCount;
}
