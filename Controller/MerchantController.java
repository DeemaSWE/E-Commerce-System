package com.example.ecommerce.Controller;

import com.example.ecommerce.Api.ApiResponse;
import com.example.ecommerce.Model.Merchant;
import com.example.ecommerce.Service.MerchantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/merchant")
@RequiredArgsConstructor
public class MerchantController {

    private final MerchantService merchantService;

    @GetMapping("/get")
    public ResponseEntity getAllMerchants() {
        return ResponseEntity.ok(merchantService.getAllMerchants());
    }

    @PostMapping("/add")
    public ResponseEntity addMerchant(@RequestBody @Valid Merchant merchant, Errors errors) {
        if (errors.hasErrors())
            return ResponseEntity.badRequest().body(errors.getFieldError().getDefaultMessage());

        merchantService.addMerchant(merchant);
        return ResponseEntity.ok(new ApiResponse("Merchant added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateMerchant(@PathVariable String id, @RequestBody @Valid Merchant merchant, Errors errors) {
        if (errors.hasErrors())
            return ResponseEntity.badRequest().body(errors.getFieldError().getDefaultMessage());

        merchantService.updateMerchant(merchant, id);
        return ResponseEntity.ok(new ApiResponse("Merchant updated successfully"));

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteMerchant(@PathVariable String id) {
        merchantService.deleteMerchant(id);
        return ResponseEntity.ok(new ApiResponse("Merchant deleted successfully"));
    }
}
