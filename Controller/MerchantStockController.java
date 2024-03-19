package com.example.ecommerce.Controller;

import com.example.ecommerce.Api.ApiResponse;
import com.example.ecommerce.Model.MerchantStock;
import com.example.ecommerce.Service.MerchantStockService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/merchant-stock")
@RequiredArgsConstructor
public class MerchantStockController {

    private final MerchantStockService merchantStockService;

    @GetMapping("/get")
    public ResponseEntity getAllMerchantStocks() {
        return ResponseEntity.ok(merchantStockService.getAllMerchantStocks());
    }

    @PostMapping("/add")
    public ResponseEntity addMerchantStock(@RequestBody @Valid MerchantStock merchantStock, Errors errors) {
        if (errors.hasErrors())
            return ResponseEntity.badRequest().body(errors.getFieldError().getDefaultMessage());

        merchantStockService.addMerchantStock(merchantStock);
        return ResponseEntity.ok(new ApiResponse("Merchant Stock added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateMerchantStock(@PathVariable String id, @RequestBody @Valid MerchantStock merchantStock, Errors errors) {
        if (errors.hasErrors())
            return ResponseEntity.badRequest().body(errors.getFieldError().getDefaultMessage());

        merchantStockService.updateMerchantStock(merchantStock, id);
        return ResponseEntity.ok(new ApiResponse("Merchant Stock updated successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteMerchantStock(@PathVariable String id) {
        merchantStockService.deleteMerchantStock(id);
        return ResponseEntity.ok(new ApiResponse("Merchant Stock deleted successfully"));

    }

    // Endpoint where a user can add more stocks of a product to a merchant Stock
    @PutMapping("/add-stock/{productId}/{merchantId}/{amount}")
    public ResponseEntity addStock(@PathVariable String productId, @PathVariable String merchantId, @PathVariable Integer amount) {
        if (amount <= 0)
            return ResponseEntity.badRequest().body(new ApiResponse("Amount should be greater than 0"));

        merchantStockService.addMoreStocks(productId, merchantId, amount);
        return ResponseEntity.ok(new ApiResponse("Stock added successfully"));
    }
}
