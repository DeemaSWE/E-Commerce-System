package com.example.ecommerce.Exception;

public class MerchantStockNotFoundException extends RuntimeException {
    public MerchantStockNotFoundException(String message) {
        super(message);
    }
}
