package com.example.ecommerce.Controller;

import com.example.ecommerce.Api.ApiResponse;
import com.example.ecommerce.Model.Product;
import com.example.ecommerce.Service.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@RestController
@RequestMapping("/api/v1/product")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/get")
    public ResponseEntity getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @PostMapping("/add")
    public ResponseEntity addProduct(@RequestBody @Valid Product product, Errors errors) {
        if (errors.hasErrors())
            return ResponseEntity.badRequest().body(errors.getFieldError().getDefaultMessage());

        productService.addProduct(product);
        return ResponseEntity.ok(new ApiResponse("Product added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateProduct(@PathVariable String id, @RequestBody @Valid Product product, Errors errors) {
        if (errors.hasErrors())
            return ResponseEntity.badRequest().body(errors.getFieldError().getDefaultMessage());

        productService.updateProduct(product, id);
        return ResponseEntity.ok(new ApiResponse("Product updated successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteProduct(@PathVariable String id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok(new ApiResponse("Product deleted successfully"));

    }

    // Endpoint to get top-selling products based on the number of units sold
    @GetMapping("/top-selling/{limit}")
    public ResponseEntity getTopSellingProducts(@PathVariable int limit) {
        return ResponseEntity.ok(productService.getTopSellingProducts(limit));
    }

    // Endpoint to rate a product
    @PutMapping("/rate/{productId}/{rating}")
    public ResponseEntity rateProduct(@PathVariable String productId, @PathVariable double rating) {
        productService.rateProduct(productId, rating);
        return ResponseEntity.ok(new ApiResponse("Product rated successfully"));
    }

}
