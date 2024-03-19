package com.example.ecommerce.Model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MerchantStock {

    @NotEmpty(message = "Id cannot be empty")
    private String id;

    @NotEmpty(message = "Product Id cannot be empty")
    private String productId;

    @NotEmpty(message = "Merchant Id cannot be empty")
    private String merchantId;

    @NotNull(message = "Stock cannot be empty")
    @Min(value = 10, message = "Stock must be at least 10")
    private Integer stock;
}
