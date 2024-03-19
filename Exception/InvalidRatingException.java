package com.example.ecommerce.Exception;

public class InvalidRatingException extends RuntimeException{
    public InvalidRatingException(String message) {
        super(message);
    }

}
